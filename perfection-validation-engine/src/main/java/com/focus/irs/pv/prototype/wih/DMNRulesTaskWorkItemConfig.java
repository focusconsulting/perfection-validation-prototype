package com.focus.irs.pv.prototype.wih;

import org.kie.kogito.process.impl.DefaultWorkItemHandlerConfig;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
public class DMNRulesTaskWorkItemConfig extends DefaultWorkItemHandlerConfig {
    {
        register("DMNRulesTask", new DMNRulesTaskWorkItemHandler());
    }

}
