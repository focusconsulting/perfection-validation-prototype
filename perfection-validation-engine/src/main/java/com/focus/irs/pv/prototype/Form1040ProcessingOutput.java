package com.focus.irs.pv.prototype;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This model is initialized at the start of the process and serves to record
 * actions that were performed during process, like calculating the standard and
 * itemized deduction.
 * 
 */
public class Form1040ProcessingOutput {

    private BigDecimal taxesOwed;
    private Integer standardDeduction;
    private Integer itemizedDeduction;
    private Form1040Data input;
    private List<String> errors;

    // Default constructor for Jackson
    public Form1040ProcessingOutput() {
        this.errors = new ArrayList<>();
    }

    @JsonCreator
    public Form1040ProcessingOutput(
            @JsonProperty("taxesOwed") BigDecimal taxesOwed,
            @JsonProperty("standardDeduction") Integer standardDeduction,
            @JsonProperty("itemizedDeduction") Integer itemizedDeduction,
            @JsonProperty("input") Form1040Data input,
            @JsonProperty("errors") List<String> errors) {
        this.taxesOwed = taxesOwed;
        this.standardDeduction = standardDeduction;
        this.itemizedDeduction = itemizedDeduction;
        this.input = input;
        this.errors = errors != null ? errors : new ArrayList<>();
    }

    public Integer getStandardDeduction() {
        return standardDeduction;
    }

    public void setStandardDeduction(Integer standardDeduction) {
        this.standardDeduction = standardDeduction;
    }

    public Integer getItemizedDeduction() {
        return itemizedDeduction;
    }

    public void setItemizedDeduction(Integer itemizedDeduction) {
        this.itemizedDeduction = itemizedDeduction;
    }

    public BigDecimal getTaxesOwed() {
        return taxesOwed;
    }

    public void setTaxesOwed(BigDecimal taxesOwed) {
        this.taxesOwed = taxesOwed;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        if(error != null && !error.isEmpty()) {
            this.errors.add(error);
        }
    }

    public Boolean isValid() {
        return this.errors.isEmpty();
    }

    public Form1040Data getInput() {
        return input;
    }

    public void setInput(Form1040Data input) {
        this.input = input;
    }
}
