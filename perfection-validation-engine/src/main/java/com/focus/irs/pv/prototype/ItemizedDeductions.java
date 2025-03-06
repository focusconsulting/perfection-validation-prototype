package com.focus.irs.pv.prototype;

import java.util.List;

/**
 * This class holds all the submitted deductions for a 1040.
 */
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

}
