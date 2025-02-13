package com.focus.irs.pv.prototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "org.kie.kogito.dmn.**", "org.kie.kogito.app.**", "http**", "com.focus.irs" })
public class PerfectionAndValidationPrototype {
    public static void main(String[] args) {
        SpringApplication.run(PerfectionAndValidationPrototype.class, args);
    }
}
