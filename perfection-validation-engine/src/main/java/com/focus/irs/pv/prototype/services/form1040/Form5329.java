package com.focus.irs.pv.prototype.services.form1040;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Form5329 {

    private String exemptionCode;
    private BigDecimal exemptionAmount;
    
    // Default constructor for Jackson
    public Form5329() {
    }
    
    @JsonCreator
    public Form5329(
            @JsonProperty("exemptionCode") String exemptionCode,
            @JsonProperty("exemptionAmount") BigDecimal exemptionAmount) {
        this.exemptionCode = exemptionCode;
        this.exemptionAmount = exemptionAmount;
    }
    
    public String getExemptionCode() {
        return exemptionCode;
    }
    
    public void setExemptionCode(String exemptionCode) {
        this.exemptionCode = exemptionCode;
    }
    
    public BigDecimal getExemptionAmount() {
        return exemptionAmount;
    }
    
    public void setExemptionAmount(BigDecimal exemptionAmount) {
        this.exemptionAmount = exemptionAmount;
    }
}
