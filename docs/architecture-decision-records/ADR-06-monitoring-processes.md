# ADR 06: Process Monitoring with Prometheus and Grafana


## Context

The Perfection/Validation Engine processes a large number of tax forms, and operational visibility is critical for:

1. Identifying performance bottlenecks
2. Monitoring system health
3. Tracking business metrics (e.g., form processing rates, error rates)
4. Capacity planning
5. Alerting on anomalies

Without proper monitoring, it would be difficult to:

- Detect issues before they impact users
- Understand processing patterns
- Make data-driven decisions about scaling and optimization

## Decision

We have implemented a comprehensive monitoring solution using:

1. **Prometheus**: For metrics collection and storage
2. **Micrometer**: For instrumenting the application
3. **Grafana**: For metrics visualization and dashboarding

This approach provides:

- Real-time visibility into process execution
- Historical data for trend analysis
- Customizable dashboards for different stakeholders
- Alerting capabilities

## Implementation Details

### Metrics Exposure

The application exposes metrics through Spring Boot Actuator:

```properties
management.security.enabled=false
management.endpoints.web.exposure.include=metrics,prometheus
```

### Prometheus Configuration

Prometheus is configured to scrape metrics from the application:

```yaml
scrape_configs:
  - job_name: 'perfection-validation-engine'
    metrics_path: '/actuator/prometheus'
    scrape_interval: 5s
    static_configs:
      - targets: ['host.docker.internal:8090']
```

### Key Metrics Collected

1. **Process Metrics**:
   - Process instance count (total, active, completed, error)
   - Process duration (min, max, average)
   - Process throughput (instances per minute)

2. **Decision Metrics**:
   - Decision evaluation count
   - Decision evaluation time
   - Decision outcome distribution

3. **System Metrics**:
   - JVM memory usage
   - CPU utilization
   - Thread pool statistics
   - Kafka consumer/producer metrics

4. **Business Metrics**:
   - Form processing volume by type
   - Error rate by form type and error code
   - Correction rate for perfected forms

### Grafana Dashboards

Pre-configured Grafana dashboards are provided:

```
./perfection-validation-engine/target/classes/META-INF/resources/monitoring/dashboards
```

These include:
- Process Overview Dashboard
- Form Processing Dashboard
- System Health Dashboard
- Error Analysis Dashboard

## Consequences

### Positive

- **Operational Visibility**: Real-time insight into process execution
- **Proactive Issue Detection**: Early warning of potential problems
- **Performance Optimization**: Data-driven approach to identifying bottlenecks
- **Capacity Planning**: Historical trends to inform scaling decisions
- **Business Intelligence**: Metrics on form processing patterns and error rates

### Negative

- **Resource Overhead**: Additional CPU and memory usage for metrics collection
- **Configuration Complexity**: Requires proper setup of multiple components
- **Maintenance**: Dashboards need to be updated as the application evolves

### Mitigations

- Metrics collection is configured with appropriate sampling to minimize overhead
- Docker Compose provides a pre-configured monitoring stack for development
- Dashboard templates are version-controlled alongside the application code
