# Form 1040 Rules

This module contains the DMN (Decision Model and Notation) rules for validating IRS Form 1040. It is packaged as a KJar (Knowledge JAR) and serves as a dependency for the sibling project `perfection-validation-engine`.

## Overview

The form-1040-rules module is structured as a KJar project, which is a specialized JAR file format used by Drools/jBPM for packaging knowledge assets (like DMN files). This separation of rules into their own module allows for:

- Independent versioning of business rules
- Clear separation of concerns between rules and application logic
- Easy deployment and updates of rules without changing the main application

## Project Structure

```
form-1040-rules/
├── src/
│   ├── main/
│   │   ├── resources/
│   │   │   ├── META-INF/
│   │   │   │   └── kmodule.xml    # KIE module descriptor
│   │   │   └── rules/
│   │   │       └── form-1040-v2.dmn  # DMN rules for Form 1040
│   └── test/
└── pom.xml
```

## Usage

This module is used as a dependency in the `perfection-validation-engine` project. The engine loads these rules at runtime to perform Form 1040 validations.

The rules are packaged into a KJar through Maven, which handles the proper packaging of the DMN files and the required KIE module descriptor (kmodule.xml).

## Integration

To use these rules, the `perfection-validation-engine` includes this module as a dependency in its pom.xml. The rules are then loaded by the KIE container at runtime, allowing the engine to execute the DMN rules defined in this module.
