package com.focus.irs.pv.prototype;

import java.math.BigDecimal;
import java.util.List;

public class ItemizedDeductions {

    private List<Deduction> deductions;

    public List<Deduction> getDeductions() {
        return deductions;
    }

    public BigDecimal calculateItemizedDeduction() {
        return this.deductions.stream().map(deduction -> deduction.getAmount()).reduce(BigDecimal.ZERO,
                BigDecimal::add);
    }

}
