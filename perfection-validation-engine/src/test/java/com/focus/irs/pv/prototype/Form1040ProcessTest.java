package com.focus.irs.pv.prototype;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kie.kogito.Model;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.focus.irs.pv.prototype.messages.Process1040Message;

@SpringBootTest(classes = PerfectionAndValidationPrototype.class)
public class Form1040ProcessTest {

    // The starting point is an a Kafka message, but in order to unit test we're manually setting the message
    // and then starting from the next node
    private static final String FETCH_DATA_STARTING_NODE = "_A66E8BD2-B59A-41F1-9432-36B2D2CF4C20";

    @Autowired
    @Qualifier("form1040processor")
    Process<? extends Model> form1040Process;

    @Test
    public void testProcess1040FormSingle() {
        assertNotNull(form1040Process);

        Model model = form1040Process.createModel();
        Map<String, Object> parameters = new HashMap<>();
        Process1040Message message = new Process1040Message("SINGLE_FILER", "objectstore:123");
        parameters.put("message", message);
        model.fromMap(parameters);
        ProcessInstance<?> processInstance = form1040Process.createInstance(model);
        processInstance.startFrom(FETCH_DATA_STARTING_NODE);

        assertEquals(ProcessInstance.STATE_COMPLETED, processInstance.status());
        Model result = (Model) processInstance.variables();

        Form1040ProcessingOutput output = (Form1040ProcessingOutput) result.toMap().get("output");
        // Assert that AGI was correctly calculated
        Float expectedAgi = 50000F;
        assertEquals(result.toMap().get("agi"), expectedAgi);
        // Confirm that taxes owed is correct
        assertEquals(result.toMap().get("totalTaxes"), 3540);
        // Validate that the teacher expense deduction amount was correctly modified and
        // that the error code was set
        Form1040Data form1040Data = output.getInput();
        assertEquals(form1040Data.getDeductions().getSubmittedDeductions().get(0).getCorrectedAmount(),
                BigDecimal.valueOf(300));
        assertEquals(form1040Data.getDeductions().getSubmittedDeductions().get(0).getErrorCodes().size(), 1);
        assertEquals(form1040Data.getDeductions().getSubmittedDeductions().get(0).getErrorCodes().get(0),
                ErrorCode.CORRECT_DEDUCTION);
        // Confirm that the form is not eligible for the child tax credit since the only
        // dependent is older than 18
        assertFalse(form1040Data.getDependentInformation().getIsChildTaxCreditAllowed());
    }

    @Test
    public void testProcess1040FormMFJ() {
        assertNotNull(form1040Process);

        Model model = form1040Process.createModel();
        Map<String, Object> parameters = new HashMap<>();
        Process1040Message message = new Process1040Message("MARRIED_FILER", "objectstore:123");
        parameters.put("message", message);
        model.fromMap(parameters);
        ProcessInstance<?> processInstance = form1040Process.createInstance(model);
        processInstance.startFrom(FETCH_DATA_STARTING_NODE);
        assertEquals(ProcessInstance.STATE_COMPLETED, processInstance.status());
        Model result = (Model) processInstance.variables();

        Form1040ProcessingOutput output = (Form1040ProcessingOutput) result.toMap().get("output");
        Float expectedAgi = 50000F;
        assertEquals(result.toMap().get("agi"), expectedAgi);
        // Confirm that taxes owed is correct
        assertEquals(result.toMap().get("totalTaxes"), 2080);
        // Confirm that the form is eligible for the child tax credit since the only
        // dependent is under 18
        assertTrue(output.getInput().getDependentInformation().getIsChildTaxCreditAllowed());
    }

}
