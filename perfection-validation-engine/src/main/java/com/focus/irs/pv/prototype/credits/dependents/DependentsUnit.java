package com.focus.irs.pv.prototype.credits.dependents;

import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;

public class DependentsUnit implements RuleUnitData {

    private Boolean isChildTaxCreditAllowed;
    private Boolean isOtherDepedentsAllowed;

    private DataStore<Dependent> dependents;

    public Boolean getIsChildTaxCreditAllowed() {
        return isChildTaxCreditAllowed;
    }

    public void setIsChildTaxCreditAllowed(Boolean isChildTaxCreditAllowed) {
        this.isChildTaxCreditAllowed = isChildTaxCreditAllowed;
    }

    public void setIsOtherDepedentsAllowed(Boolean isOtherDepedentsAllowed) {
        this.isOtherDepedentsAllowed = isOtherDepedentsAllowed;
    }

    public void setDependents(DataStore<Dependent> dependents) {
        this.dependents = dependents;
    }

    public Boolean getIsOtherDepedentsAllowed() {
        return isOtherDepedentsAllowed;
    }

    public DataStore<Dependent> getDependents() {
        return dependents;
    }

    public DependentsUnit() {
        this(DataSource.createStore());
    }

    public DependentsUnit(DataStore<Dependent> dependents) {
        this.dependents = dependents;
    }

}
