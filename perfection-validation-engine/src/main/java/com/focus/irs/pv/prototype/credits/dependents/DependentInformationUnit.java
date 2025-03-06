package com.focus.irs.pv.prototype.credits.dependents;

import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;

/**
 * Rule unit that evaluates a dependent information to see what credits its
 * eligible for
 */
public class DependentInformationUnit implements RuleUnitData {

    private DataStore<DependentInformation> dependentInformation;

    public DataStore<DependentInformation> getDependentInformation() {
        return dependentInformation;
    }

    public DependentInformationUnit() {
        this(DataSource.createStore());
    }

    public DependentInformationUnit(DataStore<DependentInformation> dependentInformation) {
        this.dependentInformation = dependentInformation;
    }

}
