package com.focus.irs.pv.prototype;

import java.math.BigDecimal;
import java.util.List;

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
        this.correctionsMade.add(correction);
    }

    public void addProcessingError(ProcessingError<?> error) {
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
}
