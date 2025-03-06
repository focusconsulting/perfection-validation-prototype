package com.focus.irs.pv.prototype;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * This class holds all the submitted deductions for a 1040.
 */
public class ItemizedDeductions {

    private List<Deduction> submittedDeductions;

    // Default constructor for Jackson
    public ItemizedDeductions() {
    }

    @JsonCreator
    public ItemizedDeductions(@JsonProperty("submittedDeductions") List<Deduction> submittedDeductions) {
        this.submittedDeductions = submittedDeductions;
    }

    public void setSubmittedDeductions(List<Deduction> submittedDeductions) {
        this.submittedDeductions = submittedDeductions;
    }

    public List<Deduction> getSubmittedDeductions() {
        return submittedDeductions;
    }
}
