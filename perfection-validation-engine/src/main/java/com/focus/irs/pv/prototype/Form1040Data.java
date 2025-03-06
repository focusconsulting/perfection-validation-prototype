package com.focus.irs.pv.prototype;

import com.focus.irs.pv.prototype.credits.dependents.DependentInformation;

/**
 * This is the input data for the 1040 form process. It contains a small subset
 * of the
 * data that is sufficient for evaluating the set of rules and corrections
 * implemented.
 * 
 * In a larger implementation, this would likely be broken down further into
 * smaller models
 * that would be inputs.
 * 
 */
public class Form1040Data {

    private Boolean isBlind;

    private Boolean isOver65;

    private FilingStatus filingStatus;

    private ItemizedDeductions deductions;

    private DependentInformation dependentInformation;

    public Form1040Data(Boolean isBlind, Boolean isOver65, FilingStatus filingStatus, ItemizedDeductions deductions,
            DependentInformation dependentInformation) {
        this.isBlind = isBlind;
        this.isOver65 = isOver65;
        this.filingStatus = filingStatus;
        this.deductions = deductions;
        this.dependentInformation = dependentInformation;
    }

    public Boolean getIsBlind() {
        return isBlind;
    }

    public Boolean getIsOver65() {
        return isOver65;
    }

    public FilingStatus getFilingStatus() {
        return filingStatus;
    }

    public ItemizedDeductions getDeductions() {
        return deductions;
    }

    public DependentInformation getDependentInformation() {
        return dependentInformation;
    }

}
