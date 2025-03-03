package com.focus.irs.pv.prototype;

import java.math.BigDecimal;
import java.util.List;

public class Form1040ProcessingOutput {

    private BigDecimal taxesOwed;
    private List<Correction<?>> correctionsMade;
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
