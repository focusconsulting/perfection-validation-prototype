package com.focus.irs.pv.prototype;

import java.math.BigDecimal;

public class Deduction {

    private BigDecimal amount;
    private String name;

    public Deduction(BigDecimal amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
