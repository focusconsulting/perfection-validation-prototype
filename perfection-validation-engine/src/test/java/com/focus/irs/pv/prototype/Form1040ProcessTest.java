package com.focus.irs.pv.prototype;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kie.kogito.Model;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PerfectionAndValidationPrototype.class)
public class Form1040ProcessTest {

    @Autowired
    @Qualifier("pv.form1040processor")
    Process<? extends Model> form1040Process;

    @Test
    public void testProcess1040FormSingle() {
        assertNotNull(form1040Process);

        Model model = form1040Process.createModel();
        Map<String, Object> parameters = new HashMap<>();
        Form1040ProcessingOutput output = new Form1040ProcessingOutput();
        Deduction teacherExpenseDeduction = new Deduction(BigDecimal.valueOf(800), "teacher-expense");
        assertNull(teacherExpenseDeduction.getCorrectedAmount());
        ItemizedDeductions itemizedDeductions = new ItemizedDeductions(Arrays.asList(teacherExpenseDeduction));
        Form1040Data data = new Form1040Data(false, false, FilingStatus.S, itemizedDeductions);
        parameters.put("Form1040", data);
        parameters.put("output", output);
        model.fromMap(parameters);
        ProcessInstance<?> processInstance = form1040Process.createInstance(model);
        processInstance.start();

        assertEquals(ProcessInstance.STATE_COMPLETED, processInstance.status());
        Model result = (Model) processInstance.variables();
        Float expectedAgi = 0.0F;
        assertEquals(result.toMap().get("agi"), expectedAgi);
        assertEquals(output.getTaxesOwed(), BigDecimal.valueOf(-14600.0));
        assertEquals(teacherExpenseDeduction.getCorrectedAmount(), BigDecimal.valueOf(300));
    }

    @Test
    public void testProcess1040FormMFJ() {
        assertNotNull(form1040Process);

        Model model = form1040Process.createModel();
        Map<String, Object> parameters = new HashMap<>();
        ItemizedDeductions itemizedDeductions = new ItemizedDeductions(Arrays.asList());
        Form1040Data data = new Form1040Data(false, false, FilingStatus.MFJ, itemizedDeductions);
        Form1040ProcessingOutput output = new Form1040ProcessingOutput();
        parameters.put("Form1040", data);
        parameters.put("output", output);
        model.fromMap(parameters);
        ProcessInstance<?> processInstance = form1040Process.createInstance(model);
        processInstance.start();
        assertEquals(ProcessInstance.STATE_COMPLETED, processInstance.status());
        Model result = (Model) processInstance.variables();
        Float expectedAgi = 0.0F;
        assertEquals(result.toMap().get("agi"), expectedAgi);
        assertEquals(output.getTaxesOwed(), BigDecimal.valueOf(-29200.0));
    }

}
