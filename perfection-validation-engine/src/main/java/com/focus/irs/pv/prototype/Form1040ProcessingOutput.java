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
    private List<Correction<?>> correctionsMade;
    private Form1040Data input;

    // Default constructor for Jackson
    public Form1040ProcessingOutput() {
        this.correctionsMade = new ArrayList<>();
        this.errors = new ArrayList<>();
    }

    @JsonCreator
    public Form1040ProcessingOutput(
            @JsonProperty("taxesOwed") BigDecimal taxesOwed,
            @JsonProperty("standardDeduction") Integer standardDeduction,
            @JsonProperty("itemizedDeduction") Integer itemizedDeduction,
            @JsonProperty("correctionsMade") List<Correction<?>> correctionsMade,
            @JsonProperty("input") Form1040Data input,
            @JsonProperty("errors") List<ProcessingError<?>> errors) {
        this.taxesOwed = taxesOwed;
        this.standardDeduction = standardDeduction;
        this.itemizedDeduction = itemizedDeduction;
        this.correctionsMade = correctionsMade != null ? correctionsMade : new ArrayList<>();
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

    private List<ProcessingError<?>> errors;

    public void addCorrection(Correction<?> correction) {
        if (this.correctionsMade == null) {
            this.correctionsMade = new ArrayList<>();
        }
        this.correctionsMade.add(correction);
    }

    public void addProcessingError(ProcessingError<?> error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }

    public BigDecimal getTaxesOwed() {
        return taxesOwed;
    }

    public void setTaxesOwed(BigDecimal taxesOwed) {
        this.taxesOwed = taxesOwed;
    }

    public List<Correction<?>> getCorrectionsMade() {
        return correctionsMade;
    }

    public void setCorrectionsMade(List<Correction<?>> correctionsMade) {
        this.correctionsMade = correctionsMade;
    }

    public List<ProcessingError<?>> getErrors() {
        return errors;
    }

    public void setErrors(List<ProcessingError<?>> errors) {
        this.errors = errors;
    }

    public Form1040Data getInput() {
        return input;
    }

    public void setInput(Form1040Data input) {
        this.input = input;
    }
}
