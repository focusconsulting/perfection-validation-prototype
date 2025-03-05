package com.focus.irs.pv.prototype.credits.dependents;

import java.util.List;

public class DependentInformation {

    private List<Dependent> dependents;
    private Boolean isChildTaxCreditAllowed;
    private Boolean isOtherDependentsCreditAllowed;

    public DependentInformation(List<Dependent> dependents) {
        this.dependents = dependents;
    }

    public Boolean getIsChildTaxCreditAllowed() {
        return isChildTaxCreditAllowed;
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public void setIsChildTaxCreditAllowed(Boolean isChildTaxCreditAllowed) {
        this.isChildTaxCreditAllowed = isChildTaxCreditAllowed;
    }

    public void setIsOtherDependentsCreditAllowed(Boolean isOtherDependentsCreditAllowed) {
        this.isOtherDependentsCreditAllowed = isOtherDependentsCreditAllowed;
    }

    public Boolean getIsOtherDependentsCreditAllowed() {
        return isOtherDependentsCreditAllowed;
    }

}
