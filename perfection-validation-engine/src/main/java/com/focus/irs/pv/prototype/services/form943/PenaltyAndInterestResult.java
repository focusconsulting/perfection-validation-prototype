package com.focus.irs.pv.prototype.services.form943;

import java.math.BigDecimal;

/**
 * Result class to hold both penalty and interest amounts
 */
public class PenaltyAndInterestResult {
    private BigDecimal penaltyAmount;
    private BigDecimal interestAmount;
    
    public PenaltyAndInterestResult(BigDecimal penaltyAmount, BigDecimal interestAmount) {
        this.penaltyAmount = penaltyAmount;
        this.interestAmount = interestAmount;
    }
    
    public BigDecimal getPenaltyAmount() {
        return penaltyAmount;
    }
    
    public BigDecimal getInterestAmount() {
        return interestAmount;
    }
    
    public BigDecimal getTotalAmount() {
        return penaltyAmount.add(interestAmount);
    }
}
