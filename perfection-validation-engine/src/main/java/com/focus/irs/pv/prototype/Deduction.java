package com.focus.irs.pv.prototype;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Deduction {

    private BigDecimal amount;
    private BigDecimal correctedAmount;
    private List<ErrorCode> errorCodes = new ArrayList<>();
    private String name;

    // Default constructor for Jackson
    public Deduction() {
    }

    @JsonCreator
    public Deduction(
            @JsonProperty("amount") BigDecimal amount,
            @JsonProperty("name") String name) {
        this.amount = amount;
        this.name = name;
    }

    public BigDecimal getCorrectedAmount() {
        return correctedAmount;
    }

    public void setCorrectedAmount(BigDecimal correctedAmount) {
        this.correctedAmount = correctedAmount;
    }

    public Boolean getIsCorrected() {
        return amount != correctedAmount;
    }

    public List<ErrorCode> getErrorCodes() {
        return errorCodes;
    }

    public void addErrorCode(ErrorCode errorCode) {
        this.errorCodes.add(errorCode);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
