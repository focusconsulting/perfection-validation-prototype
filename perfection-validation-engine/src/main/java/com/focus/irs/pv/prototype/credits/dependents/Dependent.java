package com.focus.irs.pv.prototype.credits.dependents;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model for a dependent. Used to evaluate which credits are applicable
 * 
 * @see com.focus.irs.pv.prototype.credits.dependents.DependentInformation
 */
public class Dependent {

    private Integer age;
    private Boolean isClaimedOnAnotherReturn;

    // Default constructor for Jackson
    public Dependent() {
    }

    @JsonCreator
    public Dependent(
            @JsonProperty("age") Integer age,
            @JsonProperty("isClaimedOnAnotherReturn") Boolean isClaimedOnAnotherReturn) {
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
