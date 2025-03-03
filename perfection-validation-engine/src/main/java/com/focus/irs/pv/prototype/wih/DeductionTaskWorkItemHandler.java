package com.focus.irs.pv.prototype.wih;

import java.util.Map;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;
import org.kie.kogito.Application;
import org.kie.kogito.decision.DecisionModel;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;

import com.focus.irs.pv.prototype.Deduction;
import com.focus.irs.pv.prototype.Form1040Data;

public class DeductionTaskWorkItemHandler implements KogitoWorkItemHandler {

    private Application application;

    public DeductionTaskWorkItemHandler(Application application) {
        this.application = application;
    }

    @Override
    public void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {

        Deduction deduction = (Deduction) workItem.getParameter("Deduction");
        DecisionModel model = application.get(org.kie.kogito.decision.DecisionModels.class)
                .getDecisionModel("deductions", deduction.getName());
        Form1040Data form1040Data = (Form1040Data) workItem.getParameter("form1040Data");

        DMNContext dmnContext = model.newContext(Map.of());

        // Set the deduction as input
        dmnContext.set("Deduction", deduction);
        dmnContext.set("Form 1040 Data", form1040Data);

        // Execute the decision
        DMNResult dmnResult = model.evaluateAll(dmnContext);

        // Complete the work item with the result
        manager.completeWorkItem(workItem.getStringId(), dmnResult.getContext().getAll());
    }

    @Override
    public void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        // No special cleanup needed
        manager.abortWorkItem(workItem.getStringId());
    }
}
