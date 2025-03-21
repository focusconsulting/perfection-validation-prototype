# ADR 04: Kafka Integration for Event-Driven Process Execution

## Status

Accepted

## Context

The Perfection/Validation Engine needs to process tax form data in an asynchronous, scalable manner. We needed a mechanism to:

1. Trigger business processes when new form data is available
2. Return processing results to downstream systems
3. Decouple the form submission from the validation/perfection processing
4. Support high throughput during peak tax filing periods

## Decision

We have integrated Apache Kafka as the messaging backbone for our event-driven architecture with the following approach:

1. **Process Triggering**: 
   - Dedicated Kafka topics for each form type (e.g., `form1040`, `form943`)
   - Messages published to these topics automatically trigger the corresponding BPMN process
   - Apache KIE's Kogito framework provides built-in Kafka integration via annotations

2. **Result Publishing**:
   - Upon process completion, results are automatically published to output topics
   - Separate topics for different downstream systems (e.g., `tams`, `ers`)
   - CloudEvents format is used for message standardization

3. **Technical Implementation**:
   - Spring Kafka is used for the underlying Kafka client implementation
   - Configuration is managed through application properties
   - Process definitions include Kafka-specific metadata to map topics to process start/end events

## Implementation Details

### Process Trigger Configuration

The BPMN processes are configured with Kafka trigger annotations:

```java
@ProcessName("Form1040Process")
@KafkaListener(topics = "${kogito.addon.cloudevents.kafka.kogito_incoming_stream.form1040}")
public class Form1040Process extends AbstractProcess<Form1040Data> {
    // Process implementation
}
```

### Result Publishing Configuration

Process completion events are automatically published to configured topics:

```properties
kogito.addon.cloudevents.kafka.kogito_outgoing_stream=tams
kogito.addon.cloudevents.kafka.kogito_outgoing_stream.ers=ers
```

### Message Format

All messages use the CloudEvents format with form-specific data in the payload:

```json
{
  "specversion": "1.0",
  "id": "21627e26-31eb-43e7-8343-92a696fd96b1",
  "source": "",
  "type": "form1040", 
  "time": "2025-04-21T13:25:16Z",
  "data": {
    "messageId": "SINGLE_FILER",
    "dataBlob": "objectStorage://single_filer"
  }
}
```

## Consequences

### Positive

- **Decoupling**: Form submission systems can operate independently from processing systems
- **Scalability**: Multiple instances of the validation engine can process messages in parallel
- **Resilience**: Messages are persisted in Kafka, allowing for recovery from processing failures
- **Monitoring**: Kafka UI provides visibility into message flow and processing status
- **Extensibility**: New form types can be added by configuring new topics and processes

### Negative

- **Complexity**: Introduces additional infrastructure components to manage
- **Latency**: Asynchronous processing means results are not immediately available
- **Debugging**: Distributed processing can make debugging more challenging

### Mitigations

- Docker Compose configuration provides a simple local development environment
- Kafka UI is included for monitoring and debugging message flow
- Comprehensive logging throughout the process execution
