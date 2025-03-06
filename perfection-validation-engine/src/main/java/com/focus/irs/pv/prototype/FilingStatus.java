package com.focus.irs.pv.prototype;

/**
 * Enum containing the list of filing statuses. Currently, only captures
 * two for the purposes of the prototype
 */
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
