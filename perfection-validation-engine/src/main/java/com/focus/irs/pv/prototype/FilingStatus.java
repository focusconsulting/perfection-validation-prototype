package com.focus.irs.pv.prototype;

public enum FilingStatus {

    S("single"),
    MFJ("Married File Jointly");

    private String name;

    FilingStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
