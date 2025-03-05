package com.focus.irs.pv.prototype;

import java.util.List;

import com.focus.irs.pv.prototype.credits.dependents.Dependent;
import com.focus.irs.pv.prototype.credits.dependents.DependentInformation;

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
