package com.focus.irs.pv.prototype.wih;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNDecisionResult;
import org.kie.dmn.api.core.DMNResult;
import org.kie.kogito.Application;
import org.kie.kogito.decision.DecisionModel;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;

import com.focus.irs.pv.prototype.Deduction;
import com.focus.irs.pv.prototype.ErrorCode;
import com.focus.irs.pv.prototype.FilingStatus;
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
        Form1040Data form1040Data = (Form1040Data) workItem.getParameter("Form");

        DMNContext dmnContext = model.newContext(Map.of());

        // Set the deduction as input
        assert (form1040Data != null);
        assert (form1040Data.getFilingStatus() == FilingStatus.S);

        // Create a map with string representation of filing status
        Map<String, Object> formDataMap = Map.of(
                "isBlind", form1040Data.getIsBlind(),
                "isOver65", form1040Data.getIsOver65(),
                "filingStatus", form1040Data.getFilingStatus().toString(),
                "deductions", form1040Data.getDeductions());

        dmnContext.set("Form", formDataMap);
        dmnContext.set("Deduction", deduction);

        // Execute the decision
        DMNResult dmnResult = model.evaluateAll(dmnContext);
        DMNDecisionResult decisionResult = dmnResult.getDecisionResultByName("Deduction Result");
        @SuppressWarnings("unchecked")
        Map<String, ?> results = (Map<String, ?>) decisionResult.getResult();
        deduction.setCorrectedAmount((BigDecimal) results.get("amount"));
        if ((String) results.get("code") != "") {
            deduction.addErrorCode(ErrorCode.valueOf((String) results.get("code")));
        }
        manager.completeWorkItem(workItem.getStringId(), Map.of());
    }

    @Override
    public void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        // No special cleanup needed
        manager.abortWorkItem(workItem.getStringId());
    }
}
