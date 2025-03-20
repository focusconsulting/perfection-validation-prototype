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
    private Integer age;
    private FilingStatus filingStatus;
    private ItemizedDeductions deductions;
    private DependentInformation dependentInformation;
    private Form5329 form5329;
    private Optional<Integer> dispersement1009RAmount = Optional.empty();
    private Optional<Integer> dispersement1040Amount = Optional.empty();

    // Default constructor for Jackson
    public Form1040Data() {
    }

    public Integer getDispersement1009RAmount() {
        return dispersement1009RAmount.orElse(0);
    }

    public Integer getDispersement1040Amount() {
        return dispersement1040Amount.orElse(0);
    }

    @JsonCreator
    public Form1040Data(
            @JsonProperty("isBlind") Boolean isBlind,
            @JsonProperty("age") Integer age,
            @JsonProperty("filingStatus") FilingStatus filingStatus,
            @JsonProperty("deductions") ItemizedDeductions deductions,
            @JsonProperty("dependentInformation") DependentInformation dependentInformation,
            @JsonProperty("form5329") Form5329 form5329) {
        this.isBlind = isBlind;
        this.age = age;
        this.filingStatus = filingStatus;
        this.deductions = deductions;
        this.dependentInformation = dependentInformation;
        this.form5329 = form5329;
    }
    
    // Constructor without form5329 for backward compatibility
    public Form1040Data(
            Boolean isBlind,
            Integer age,
            FilingStatus filingStatus,
            ItemizedDeductions deductions,
            DependentInformation dependentInformation) {
        this.isBlind = isBlind;
        this.age = age;
        this.filingStatus = filingStatus;
        this.deductions = deductions;
        this.dependentInformation = dependentInformation;
        this.form5329 = null;
    }

    public Boolean getIsBlind() {
        return isBlind;
    }

    public Integer getAge() {
        return age;
    }
    
    public Boolean getIsOver65() {
        return age != null && age >= 65;
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
    
    public Form5329 getForm5329() {
        return form5329;
    }
    
    public void setForm5329(Form5329 form5329) {
        this.form5329 = form5329;
    }
}
