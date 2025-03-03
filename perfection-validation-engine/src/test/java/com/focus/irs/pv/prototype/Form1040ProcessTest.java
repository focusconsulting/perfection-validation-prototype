package com.focus.irs.pv.prototype;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

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

@SpringBootTest(classes = PerfectionAndValidationPrototype.class)
public class Form1040ProcessTest {

    @Autowired
    @Qualifier("form_1040_processor")
    Process<? extends Model> form1040Process;

    @Test
    public void testProcess1040Form() {
        assertNotNull(form1040Process);

        Model model = form1040Process.createModel();
        Map<String, Object> parameters = new HashMap<>();
        Form1040Data data = new Form1040Data(false, false, FilingStatus.S);
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
        assertEquals(output.getTaxesOwed(), BigDecimal.valueOf(-14600.0));
    }

}
