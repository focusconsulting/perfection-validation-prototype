# IRS Validation/Perfection Prototype

A prototype system for validating different approaches for capturing rules and corrections to various IRS forms.

## Structure

The project consists of two main modules:

```
.
├── perfection-validation-engine/    # Core validation engine
│   ├── src/main/
│   │   ├── java/                   # Java source files
│   │   │   └── com/focus/irs/pv/   # Main application code
│   │   └── resources/              # Application resources and configurations
│   └── pom.xml                     # Module-specific Maven configuration
│
├── form-1040-rules/                # Form 1040 validation rules
│   ├── src/main/
│   │   ├── resources/
│   │   │   ├── rules/             # DMN rule definitions
│   │   │   └── META-INF/          # Drools configuration
│   │   └── java/                  # Java source files
│   └── pom.xml                    # Module-specific Maven configuration
│
├── compose.yaml                    # Docker compose configuration
└── pom.xml                        # Parent Maven configuration
```

## Environment Setup

### Prerequisites

1. Java Development Kit (JDK) 17 or later
2. Apache Maven 3.8+
3. Docker and Docker Compose (optional, for containerized deployment)

### Local Development Setup

1. Clone the repository:

   ```bash
   git clone [repository-url]
   cd irs-validation-perfection-prototype
   ```

2. Build the project:

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   # Start Kafka and Kafka UI in Docker containers
   docker compose -f 'compose.yaml' up -d --build 'kafka'
   docker compose -f 'compose.yaml' up -d --build 'kafka-ui' 
   
   # Run the Spring Boot application
   cd perfection-validation-engine
   mvn spring-boot:run
   ```

   This sequence first brings up Kafka and Kafka UI in Docker containers, then runs the Spring Boot application. The application connects to the local Kafka instance for message processing. You can access the Kafka UI at http://localhost:8081 to monitor topics and messages.

<!-- TODO: link to nested readme -->

### DMN File Editing

The DMN (Decision Model and Notation) files in this project can be viewed and edited using two methods:

#### 1. VSCode Kogito Extension

1. Install the "Kogito Bundle" extension in VSCode
2. Open any `.dmn` file in the project
3. The extension will provide a visual editor for the DMN file
4. Start the kie-extended-services using Docker Compose:

   ```bash
   docker compose up kie-extended-services
   ```

![VSCode editor](./vscode-dmn-editor.png)

#### 2. Local KIE Webapp

1. Start both required services using Docker Compose:

   ```bash
   docker compose up kie-extended-services kie-webapp
   ```

2. Access the KIE Webapp at <http://localhost:8080>
3. Create a new project or open an existing one
4. Upload the DMN files from the `form-1040-rules/src/main/resources/rules/` directory
5. Use the web-based visual editor to view and modify the decision models

![KIE Webapp](./kie-webapp-dmn-editor.png)

Note: Both methods require the kie-extended-services to be running for full functionality including validation and testing capabilities. The kie-extended-services runs on port 21345 and provides necessary backend services for DMN editing and validation.

<!-- TODO: clean this up -->
### KIE Resources Registration

This engine demonstrates two methods for registering KIE decisions:

1. Via KJAR (Knowledge JAR):
   - Decisions are packaged in the sibling `form-1040-rules` project
   - The KJAR is built and included as a dependency

2. Direct Resource Storage:
   - Decisions are stored directly in the project's resources directory
   - Loaded directly from the application's classpath

### API Documentation

After starting the Spring Boot application, you can access the OpenAPI documentation at:

```
http://localhost:8090/v3/api-docs
```

This endpoint provides a complete list of available API endpoints and their specifications.

### Docker Setup (Alternative)

To run the application using Docker:

```bash
docker compose up --build
```

## Troubleshooting

## Technology Stack

- **Java**: Core programming language
- **Spring Boot**: Application framework
- **Drools/DMN**: Business rules engine for Form 1040 validation rules
- **Maven**: Dependency management and build tool
- **Docker**: Containerization (optional)
