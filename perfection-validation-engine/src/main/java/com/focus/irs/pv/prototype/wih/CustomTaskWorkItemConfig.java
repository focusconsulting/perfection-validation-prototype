package com.focus.irs.pv.prototype.wih;

import org.kie.kogito.Application;
import org.kie.kogito.process.impl.DefaultWorkItemHandlerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import jakarta.annotation.PostConstruct;

@ApplicationScope
@Component
public class CustomTaskWorkItemConfig extends DefaultWorkItemHandlerConfig {

    @Autowired
    private Application application;

    @PostConstruct
    public void init() {
        register("DMNRulesTask", new DMNRulesTaskWorkItemHandler());
        register("DeductionTask", new DeductionTaskWorkItemHandler(application));
    }
}
