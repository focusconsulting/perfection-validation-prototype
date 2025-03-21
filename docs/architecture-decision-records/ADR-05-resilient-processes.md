# ADR 05: Resilient Process Execution with PostgreSQL Persistence

## Status

Accepted

## Context

The Perfection/Validation Engine processes tax forms that may take significant time to complete and require multiple steps. During processing, several issues could occur:

1. Application crashes or restarts
2. Network failures during external service calls
3. Scheduled maintenance requiring system shutdown
4. Scaling events where pods/containers are terminated and recreated

Without persistence, in-flight processes would be lost during these events, requiring resubmission of tax forms and potentially causing data inconsistencies or duplicate processing.

## Decision

We have implemented process state persistence using PostgreSQL as the backing store for the Kogito process engine. This approach:

1. Persists the complete state of all running processes
2. Enables automatic recovery after system restarts
3. Allows for process migration between application instances
4. Provides an audit trail of process execution

## Implementation Details

### PostgreSQL Configuration

The application is configured to use PostgreSQL for process state persistence:

```properties
# Process persistence configuration
kogito.persistence.type=jdbc
spring.datasource.url=jdbc:postgresql://localhost:5432/irspv
spring.datasource.username=irspv
spring.datasource.password=irspv-pass
```

### Database Schema Management

Flyway is used for database schema management to ensure proper table structure:

```properties
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/{vendor}
```

### Process Instance Storage

The Kogito engine automatically stores process instances in the following tables:

- `process_instances`: Stores the core process instance data
- `process_instance_variables`: Stores process variables
- `correlation_instances`: Maps correlation IDs to process instances
- `tasks`: Stores human task information (when applicable)

### Recovery Mechanism

When the application starts:

1. The Kogito engine queries the database for active process instances
2. Each process instance is restored to its last saved state
3. Process execution continues from the point of interruption
4. Timers and other scheduled activities are re-established

## Consequences

### Positive

- **Resilience**: Process execution can survive application restarts and crashes
- **Scalability**: Multiple application instances can share the process state
- **Auditability**: Complete history of process execution is preserved
- **Operational Flexibility**: Maintenance can be performed without losing in-flight processes

### Negative

- **Performance Impact**: Persistence operations add latency to process execution
- **Complexity**: Additional infrastructure component to manage
- **Data Volume**: Long-running processes with many variables can consume significant database space

### Mitigations

- Connection pooling is configured to minimize database connection overhead
- Process variables are serialized efficiently to minimize storage requirements
- Database indexes are created on frequently queried fields
- Regular database maintenance is scheduled to manage data growth
