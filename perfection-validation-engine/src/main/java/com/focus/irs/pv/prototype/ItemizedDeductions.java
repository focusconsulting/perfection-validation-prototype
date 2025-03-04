package com.focus.irs.pv.prototype;

import java.math.BigDecimal;
import java.util.List;

public class ItemizedDeductions {

    public ItemizedDeductions(List<Deduction> submittedDeductions) {
        this.submittedDeductions = submittedDeductions;
    }

    private List<Deduction> submittedDeductions;

    public void setSubmittedDeductions(List<Deduction> submittedDeductions) {
        this.submittedDeductions = submittedDeductions;
    }

    public List<Deduction> getSubmittedDeductions() {
        return submittedDeductions;
    }

    public BigDecimal calculateItemizedDeduction() {
        return this.submittedDeductions.stream().map(deduction -> deduction.getAmount()).reduce(BigDecimal.ZERO,
                BigDecimal::add);
    }

}
