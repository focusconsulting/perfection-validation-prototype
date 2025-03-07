package com.focus.irs.pv.prototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "org.kie.kogito.**", "http**", "com.focus.irs.**", "pv**" })
public class PerfectionAndValidationPrototype {
    public static void main(String[] args) {
        SpringApplication.run(PerfectionAndValidationPrototype.class, args);
    }
}
