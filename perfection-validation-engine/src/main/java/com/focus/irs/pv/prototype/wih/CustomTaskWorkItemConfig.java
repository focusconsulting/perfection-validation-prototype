package com.focus.irs.pv.prototype.wih;

import org.kie.kogito.Application;
import org.kie.kogito.process.impl.DefaultWorkItemHandlerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import jakarta.annotation.PostConstruct;

/**
 * 
 * This class is responsible for registering custom tasks with the kogito
 * application
 * 
 */
@ApplicationScope
@Component
public class CustomTaskWorkItemConfig extends DefaultWorkItemHandlerConfig {

    /**
     * Reference to the kogito application that can be used by task handler to
     * access the runtime
     */
    @Autowired
    private Application application;

    @PostConstruct
    public void init() {
        register("DeductionTask", new DeductionTaskWorkItemHandler(application));
    }
}
