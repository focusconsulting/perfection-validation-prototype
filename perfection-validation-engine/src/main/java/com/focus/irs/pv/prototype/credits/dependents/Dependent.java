package com.focus.irs.pv.prototype.credits.dependents;

public class Dependent {

    private Integer age;
    private Boolean isClaimedOnAnotherReturn;

    public Dependent(Integer age, Boolean isClaimedOnAnotherReturn) {
        this.age = age;
        this.isClaimedOnAnotherReturn = isClaimedOnAnotherReturn;
    }

    public Boolean getIsClaimedOnAnotherReturn() {
        return isClaimedOnAnotherReturn;
    }

    public Integer getAge() {
        return age;
    }

}
