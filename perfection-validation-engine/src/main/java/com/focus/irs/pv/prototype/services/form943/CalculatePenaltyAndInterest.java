package com.focus.irs.pv.prototype.services.form943;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;

@Component
public class CalculatePenaltyAndInterest {
    
    // Static due date for Form 943 (January 31, 2025)
    private static final LocalDate FORM_943_DUE_DATE = LocalDate.of(2025, Month.JANUARY, 31);

    /**
     * Calculates penalty and interest for late tax payments.
     * 
     * @param filingDate The date when the tax form was filed
     * @param taxAmount The amount of tax owed
     * @param isLate Flag indicating if the filing is considered late
     * @return PenaltyAndInterestResult containing calculated penalty and interest amounts
     */
    public PenaltyAndInterestResult calculate(LocalDate filingDate, BigDecimal taxAmount, Boolean isLate) {
        // If not marked as late, return zero penalty and interest
        if (isLate == null || !isLate) {
            return new PenaltyAndInterestResult(BigDecimal.ZERO, BigDecimal.ZERO);
        }
        
        int daysLate = calculateDaysLate(filingDate, FORM_943_DUE_DATE);
        BigDecimal penaltyAmount = calculatePenalty(daysLate, taxAmount);
        BigDecimal interestAmount = calculateInterest(daysLate, taxAmount);
        
        return new PenaltyAndInterestResult(penaltyAmount, interestAmount);
    }
    
    /**
     * Calculates the number of days late based on filing date and due date.
     * 
     * @param filingDate The date when the tax form was filed
     * @param dueDate The date when the tax form was due
     * @return Number of days late (0 if filed on time or early)
     */
    private int calculateDaysLate(LocalDate filingDate, LocalDate dueDate) {
        if (filingDate.isBefore(dueDate) || filingDate.isEqual(dueDate)) {
            return 0;
        }
        
        return (int) java.time.temporal.ChronoUnit.DAYS.between(dueDate, filingDate);
    }
    
    /**
     * Calculates the penalty amount based on days late.
     * Penalty is 5% of tax amount for each month or part of month late, up to 25%.
     */
    private BigDecimal calculatePenalty(int daysLate, BigDecimal taxAmount) {
        if (daysLate <= 0) {
            return BigDecimal.ZERO;
        }
        
        // Calculate months late (any part of a month counts as a full month)
        int monthsLate = (daysLate / 30) + (daysLate % 30 > 0 ? 1 : 0);
        
        // Cap at 5 months (25% maximum penalty)
        monthsLate = Math.min(monthsLate, 5);
        
        // 5% per month
        BigDecimal penaltyRate = new BigDecimal("0.05").multiply(new BigDecimal(monthsLate));
        
        return taxAmount.multiply(penaltyRate).setScale(2, RoundingMode.HALF_UP);
    }
    
    /**
     * Calculates the interest amount based on days late.
     * Uses a simplified annual rate of 3% for this prototype.
     */
    private BigDecimal calculateInterest(int daysLate, BigDecimal taxAmount) {
        if (daysLate <= 0) {
            return BigDecimal.ZERO;
        }
        
        // Annual interest rate (3% for this prototype)
        BigDecimal annualRate = new BigDecimal("0.03");
        
        // Daily interest rate
        BigDecimal dailyRate = annualRate.divide(new BigDecimal("365"), 10, RoundingMode.HALF_UP);
        
        // Calculate interest: principal * rate * time
        return taxAmount
                .multiply(dailyRate)
                .multiply(new BigDecimal(daysLate))
                .setScale(2, RoundingMode.HALF_UP);
    }
    
    // PenaltyAndInterestResult class has been extracted to its own file
}
