package com.focus.irs.pv.prototype.credits.dependents;

/**
 * Data model for a dependent. Used to evaluate which credits are applicable
 * 
 * @see com.focus.irs.pv.prototype.credits.dependents.DependentInformation
 */
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
