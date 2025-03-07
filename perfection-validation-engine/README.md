# Perfection/Validation Engine

This directory serves as the home for various prototypes focused on building decisions and processes for the Perfection/Validation Engine. These prototypes help explore and validate different approaches before implementing them in the production system.

## Implementation details

The current goal of this prototype was to demonstrate how processing a 1040 Form might be broken down into smaller pieces so that the functionality
could be worked on by a large team.  The rules and corrections are meant to demonstrate that are not exhaustive or necessarily an accurate reflection
of the business requirements.

### Form 1040 Process

This is the entry point for the processing a 1040 and orchestrates the other pieces

### Corrections/Calculate AGI/ Refund, Etc

This is an example of how a Java service can be directly implemented into the BPMN process. The current implementation is hardcoded to return a default value,
but this type of node would be a good use case for any complicated logic that is best written in Java.

### Standard deduction

The standard deduction is calculated via a DMN decision.

- Inputs:
  - Filing Status
  - Is Blind?
  - Is over 65?

Output: what the standard deduction is

### Itemized deductions

This is a re-usable sub process that is contained in `deductions.bpmn2`.  It shows how pieces of functionality can be extracted from the overall process and
isolated in separate editable and testable file.

This sub-process demonstrates how custom BPMN tasks can be implemented to consolidate a re-usable set of logic; in this case, evaluating a deduction.  This task
expects a `Deduction` object and the 1040 data and then uses the name of the deduction to load and evaluate a specific DMN decision.  Implemented in the prototype is
the evaluation of the teacher expenses deduction.

Finally, after looping over all submitted deductions the total amount of itemized deductions is calculated.

### Tax credits

This is another extracted sub-process that determines tax credits.  It demonstrates how DRL rule units (an alternative to DMN) can be used for making business decisions.
The syntax for that is not visual and pseudo-code, but does provide additional flexibility in how the rules can be run.

## Running the prototype

The prototype can be executed by running and starting the application:

`./mvnw spring-boot:run -f perfection-validation-engine/pom.xml`

After the application is running, you can execute the following to execute the 1040 process:

```sh
curl -X POST 'http://localhost:8090/form1040processor' \
-H 'Content-Type: application/json' \
-d '{
  "form1040": {
    "filingStatus": "S",
    "isOver65": false,
    "isBlind": false,
    "deductions": {
      "submittedDeductions": [
        {
          "name": "teacher-expense",
          "amount": 400
        }
      ]
    },
    "dependentInformation": {
      "dependents": [
        {
          "age": 10,
          "isClaimedOnAnotherReturn": false
        }
      ]
    }
  },
  "output": {

  }
}'
```
