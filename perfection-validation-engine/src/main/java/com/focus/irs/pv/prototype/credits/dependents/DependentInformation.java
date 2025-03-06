package com.focus.irs.pv.prototype.credits.dependents;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    // Default constructor for Jackson
    public DependentInformation() {
    }

    @JsonCreator
    public DependentInformation(@JsonProperty("dependents") List<Dependent> dependents) {
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
