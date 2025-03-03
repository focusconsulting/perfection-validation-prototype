package com.focus.irs.pv.prototype;

import java.math.BigDecimal;
import java.util.List;

public class ItemizedDeductions {

    public ItemizedDeductions(List<Deduction> submittedDeductions) {
        this.submittedDeductions = submittedDeductions;
    }

    private List<Deduction> submittedDeductions;

    private List<Deduction> validOrCorrectedDeductions;

    private List<ProcessingError<Deduction>> processingErrors;

    public void setSubmittedDeductions(List<Deduction> submittedDeductions) {
        this.submittedDeductions = submittedDeductions;
    }

    public List<Deduction> getValidOrCorrectedDeductions() {
        return validOrCorrectedDeductions;
    }

    public void setValidOrCorrectedDeductions(List<Deduction> validOrCorrectedDeductions) {
        this.validOrCorrectedDeductions = validOrCorrectedDeductions;
    }

    public List<ProcessingError<Deduction>> getProcessingErrors() {
        return processingErrors;
    }

    public void setProcessingErrors(List<ProcessingError<Deduction>> processingErrors) {
        this.processingErrors = processingErrors;
    }

    public List<Correction<Deduction>> getCorrections() {
        return corrections;
    }

    public void setCorrections(List<Correction<Deduction>> corrections) {
        this.corrections = corrections;
    }

    private List<Correction<Deduction>> corrections;

    public List<Deduction> getSubmittedDeductions() {
        return submittedDeductions;
    }

    public BigDecimal calculateItemizedDeduction() {
        return this.submittedDeductions.stream().map(deduction -> deduction.getAmount()).reduce(BigDecimal.ZERO,
                BigDecimal::add);
    }

}
