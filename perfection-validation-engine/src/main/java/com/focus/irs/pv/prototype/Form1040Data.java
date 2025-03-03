package com.focus.irs.pv.prototype;

public class Form1040Data {

    private Boolean isBlind;

    private Boolean isOver65;

    private FilingStatus filingStatus;

    private ItemizedDeductions deductions;

    public Form1040Data(Boolean isBlind, Boolean isOver65, FilingStatus filingStatus) {
        this.isBlind = isBlind;
        this.isOver65 = isOver65;
        this.filingStatus = filingStatus;
    }

    public Boolean getIsBlind() {
        return isBlind;
    }

    public Boolean getIsOver65() {
        return isOver65;
    }

    public FilingStatus getFilingStatus() {
        return filingStatus;
    }

    public ItemizedDeductions getDeductions() {
        return deductions;
    }

}
