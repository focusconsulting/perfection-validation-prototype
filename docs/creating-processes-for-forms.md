<!-- TODO: explain the various pieces of the form processes and decisions technically -->

# Technical tools available in Apache KIE

## BPMN

<!-- AI! add explanation for how BPMN can capture the processing of a form as well as provide to isolate functionality via embeddable subprocesses -->

### Triggering processes

<!-- AI! indicate that events from a Kafka queue or invocation as a REST endpoint are possible -->

### Task types

## Decisions

<!-- AI! explain that decisions power much of the logic of the BPMN process -->

### DMN

<!-- AI! explain DMN, emphasize that is more visual and friendly to non-technical analysis -->

### DRL

<!-- AI! indicate that its more flexible but is closer to code -->


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