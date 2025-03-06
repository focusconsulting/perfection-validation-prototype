package com.focus.irs.pv.prototype.services;

import org.springframework.stereotype.Component;

/**
 * This service calculate adjusted gross income. It currently just
 * serves as a demonstration of how a Java service can integrated into
 * a BPMN process and the return value is hardcoded
 * 
 */
@Component
public class CalculateAGI {

    /**
     * Returns a hard coded value for the AGI
     * 
     * @return the AGI
     */
    public Integer calculate() {
        return 5000;
    }

}
