# Perfection/Validation Engine

This directory serves as the home for various prototypes focused on building decisions and processes for the Perfection/Validation Engine. These prototypes help explore and validate different approaches before implementing them in the production system.

## Implementation details

This prototype demonstrates the various technical capabilities of Apache KIE (Kogito, Drools, jBPM) in the context of IRS form processing. It showcases how business rules, decision models, and process workflows can be applied to tax form validation, perfection, and processing. The implementation illustrates:

- Decision Model and Notation (DMN) for tax calculation rules
- Business Process Model and Notation (BPMN) for form processing workflows
- Rule-based validation and correction of form data
- Integration with enterprise messaging systems
- Separation of business rules from application code
- Modular architecture for maintainability and scalability

### Kafka
<!-- AI! this section should also indicate that the process ends by putting a message back to kafka -->

The prototype leverages Apache KIE's seamless integration with Kafka message queues. The BPMN processes are configured to listen to specific Kafka topics, allowing for event-driven workflow execution. This integration enables:

- Asynchronous processing of tax form submissions
- Scalable handling of high-volume form processing
- Reliable message delivery with built-in retry mechanisms
- Decoupled architecture between form submission and processing components

The integration requires minimal configuration, as Apache KIE provides out-of-the-box connectors that map Kafka messages to process variables and automatically trigger process instances when messages arrive on the configured topics.

### Form 1040 Process

This is the entry point for the processing a 1040 and orchestrates the other pieces

### Form 943

<!-- TODO: explain the Kafka integration and how to test it -->

<!-- TODO: move this to a separate doc -->

## Running the prototype

The prototype can be executed by running and starting the application:

`./mvnw spring-boot:run -f perfection-validation-engine/pom.xml`


<!-- TODO: description of running a message with Kafka -->

<!-- TODO: check that this works or update it -->

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

This is an example of the how the decisions can only be independently invoked.

```sh
 curl -X POST 'http://localhost:8090/calculate-total-taxes' -H 'Content-Type: application/json' -d '{"AGI": 121000}'
 ```

### API Documentation

After starting the Spring Boot application, you can access the OpenAPI documentation at:

```
http://localhost:8090/v3/api-docs
```
