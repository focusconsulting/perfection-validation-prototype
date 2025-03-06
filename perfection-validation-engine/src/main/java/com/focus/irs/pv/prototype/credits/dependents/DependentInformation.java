package com.focus.irs.pv.prototype.credits.dependents;

import java.util.List;

/**
 * Data model that contains the list of dependents and whether or not
 * the submitted information is eligible for the child tax credit or other
 * dependents credit
 */
public class DependentInformation {

    private List<Dependent> dependents;
    private Boolean isChildTaxCreditAllowed = false;
    private Boolean isOtherDependentsCreditAllowed = false;

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
