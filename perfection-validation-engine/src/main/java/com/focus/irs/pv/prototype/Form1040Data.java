package com.focus.irs.pv.prototype;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.focus.irs.pv.prototype.credits.dependents.DependentInformation;
import com.focus.irs.pv.prototype.services.form1040.Form5329;

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
    // AI! Include form5329 in constructors and make json serializable
    private Optional<Form5329> form5329 = Optional.empty();

    // Default constructor for Jackson
    public Form1040Data() {
    }

    @JsonCreator
    public Form1040Data(
            @JsonProperty("isBlind") Boolean isBlind,
            @JsonProperty("isOver65") Boolean isOver65,
            @JsonProperty("filingStatus") FilingStatus filingStatus,
            @JsonProperty("deductions") ItemizedDeductions deductions,
            @JsonProperty("dependentInformation") DependentInformation dependentInformation) {
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
