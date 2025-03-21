<!-- TODO: explain the various pieces of the form processes and decisions technically -->

# Technical tools available in Apache KIE

## BPMN

Business Process Model and Notation (BPMN) is a graphical representation for specifying business processes. In the context of form processing, BPMN provides several key advantages:

- **End-to-End Process Visualization**: BPMN diagrams capture the entire lifecycle of form processing, from initial submission to final validation and storage.
- **Separation of Concerns**: Different aspects of form processing (validation, calculation, correction) can be modeled as distinct steps.
- **Reusable Components**: Common functionality can be encapsulated in subprocesses that can be reused across different form types.
- **Embeddable Subprocesses**: Complex operations like itemized deduction processing can be isolated into dedicated subprocesses, making the main process more readable and maintainable.
- **Error Handling**: BPMN provides explicit constructs for handling exceptions and errors during form processing.

For example, in our Form 1040 implementation, we've isolated the itemized deduction processing into a separate subprocess that can be called from the main process, keeping the main flow clean while allowing detailed processing logic to be contained in a dedicated component.

### Triggering processes

Apache KIE provides multiple ways to trigger business processes:

- **Kafka Integration**: Processes can be automatically started when messages arrive on specific Kafka topics. This is configured through annotations in the process definition, allowing for event-driven architectures where form submissions trigger processing asynchronously.
- **REST API Endpoints**: Each process is automatically exposed as a REST endpoint, allowing direct invocation from web applications or other services. This enables synchronous processing scenarios where immediate feedback is required.
- **Timer Events**: Processes can be scheduled to start at specific times or intervals, useful for batch processing of forms.
- **Manual Triggers**: Processes can be started through the KIE API programmatically from Java code.

Our implementation primarily uses Kafka for asynchronous processing, which provides better scalability and resilience, but also exposes REST endpoints for testing and direct integration scenarios.

### Task types

Apache KIE supports various task types in BPMN processes, each serving different purposes:

1. **Script Tasks**: Allow embedding code directly in the process definition
   - Can contain Java, JavaScript, or other scripting languages
   - Useful for simple transformations, calculations, or variable manipulations
   - Example: The "Store deduction info" task in Form 1040 process that updates output variables

2. **Business Rule Tasks**: Connect processes to decision models
   - Link to DMN models for complex decision logic
   - Can reference external rule assets by name, namespace, and model
   - Example: The "Determine Standard Deduction" task that evaluates filing status and age

3. **Service Tasks**: Execute Java service methods
   - Invoke methods on Spring components or other Java classes
   - Support parameter mapping between process variables and service inputs/outputs
   - Example: The "Calculate AGI" task that calls a service to compute Adjusted Gross Income

4. **Custom Tasks**: Implement specialized functionality
   - Can be created by implementing the WorkItemHandler interface
   - Allow for reusable, complex operations with custom UI representation
   - Example: The "DeductionTaskWorkItemHandler" that processes individual deductions

5. **Call Activities**: Invoke subprocesses
   - Enable modular process design by calling other BPMN processes
   - Support data mapping between parent and child processes
   - Example: The "Process itemized deductions" activity that calls a separate process

6. **Human Tasks**: Involve human actors in the process
   - Assign work to users or groups
   - Support forms, deadlines, and notifications
   - Not used in the current implementation but available for interactive scenarios

Each task type is visually distinct in the BPMN diagram and configured through specific properties in the process editor.

## Decisions

Decisions are a core component of form processing logic in Apache KIE. They power the business rules that determine:

- Validation of form data (e.g., checking if deductions exceed allowable limits)
- Calculation of derived values (e.g., computing standard deduction amounts)
- Eligibility determinations (e.g., qualifying for specific tax credits)
- Correction of erroneous data (e.g., adjusting improperly claimed deductions)

These decisions are integrated into BPMN processes through Business Rule Tasks, which connect the process flow to the underlying decision models. This separation allows business rules to be managed independently from process flow, enabling tax experts to focus on rule definition while process designers focus on workflow.

In our implementation, decisions handle complex tax calculations like determining standard deduction amounts based on filing status, age, and other factors, while the BPMN process orchestrates when these decisions are applied during form processing.

### DMN

Decision Model and Notation (DMN) is a standard for modeling and executing decision logic. It offers several advantages for tax form processing:

- **Visual Decision Tables**: Complex conditional logic can be represented in easy-to-understand decision tables that map conditions to outcomes.
- **Business-Friendly Notation**: DMN uses business-friendly expressions that are accessible to non-programmers, allowing tax experts to directly review and validate the logic.
- **Separation of Decision Logic**: DMN separates the decision logic from the implementation details, making it easier to maintain and update tax rules.
- **Formal Input/Output Model**: Each decision has clearly defined inputs and outputs, ensuring consistency in how data flows through the system.
- **Testability**: DMN models can be tested independently of the processes that use them, allowing for comprehensive validation of tax rules.

For example, our standard deduction calculation is implemented as a DMN decision that takes inputs like filing status, age, and blindness status, and produces the appropriate standard deduction amount according to tax regulations.

### DRL

Drools Rule Language (DRL) provides a more programmatic approach to defining business rules. While DMN is preferred for most tax form scenarios due to its visual nature, DRL offers:

- **Greater Flexibility**: DRL can express more complex patterns and conditions that might be difficult to represent in DMN.
- **Procedural Logic**: When rules need to execute specific actions or algorithms, DRL provides more programming-like capabilities.
- **Rule Units**: DRL supports organizing rules into units that can be executed together, useful for complex validation scenarios.
- **Integration with Java**: DRL rules can directly invoke Java methods and access Java objects, allowing for deeper integration with the application code.

In our implementation, DRL is used for specialized scenarios like dependent credit calculations where the rule logic benefits from the additional flexibility of a code-based approach.

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
