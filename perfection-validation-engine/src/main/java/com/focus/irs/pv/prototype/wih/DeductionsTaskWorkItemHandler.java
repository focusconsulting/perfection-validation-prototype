package com.focus.irs.pv.prototype.wih;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;

import com.focus.irs.pv.prototype.Deduction;
import com.focus.irs.pv.prototype.Form1040Data;

public class DeductionsTaskWorkItemHandler implements KogitoWorkItemHandler {
    @Override
    public void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        DMNRuntime dmnRuntime = kieContainer.newKieSession().getKieRuntime(DMNRuntime.class);

        String dmnFile = (String) workItem.getParameter("dmnFile");
        Deduction deduction = (Deduction) workItem.getParameter("deduction");
        Form1040Data form1040Data = (Form1040Data) workItem.getParameter("form1040Data");

        DMNModel dmnModel = dmnRuntime.getModel(dmnFile, dmnFile);
        DMNContext dmnContext = dmnRuntime.newContext();

        // Set the deduction as input
        dmnContext.set("deduction", deduction);
        dmnContext.set("form1040Data", form1040Data);

        // Execute the decision
        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, dmnContext);

        // Complete the work item with the result
        manager.completeWorkItem(workItem.getStringId(), dmnResult.getContext().getAll());
    }

    @Override
    public void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        // No special cleanup needed
        manager.abortWorkItem(workItem.getStringId());
    }
}
