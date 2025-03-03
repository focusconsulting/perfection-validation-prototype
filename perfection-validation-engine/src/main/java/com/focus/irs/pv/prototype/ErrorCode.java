package com.focus.irs.pv.prototype;

public enum ErrorCode {

    CORRECT_W2("1", "W2 Value corrected");

    private String code;
    private String humanReadableString;

    private ErrorCode(String code, String humanReadableString) {
        this.code = code;
        this.humanReadableString = humanReadableString;
    }

    public String getCode() {
        return code;
    }

    public String getHumanReadableString() {
        return humanReadableString;
    }

}
