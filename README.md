### Teacher : Mr. Laabidi Raissi
### Student : Yen Nguyen, Bénédicte Rozay, Ali Dolanaby
# Zoo Management API


The Zoo Management API is a Spring Boot application designed to manage various aspects of a zoo, including animals, employees, and enclosures. This API allows users to perform CRUD operations on these entities and link animals to enclosures.

## Features

- Manage animals: Create, read, update, and delete animal records.
- Manage employees: Create, read, update, and delete employee records.
- Manage enclosures: Create, read, update, and delete enclosure records.
- Link animals to enclosures.
- Comprehensive unit tests using Mockito and JUnit.
- API documentation using Swagger.
- Using MockMvc to test the API endpoints.

## Prerequisites

- Java 21 or higher
- Maven 3.6.0 or higher
- A relational database (PostgreSQL), with the following properties:
  - URL: jdbc:postgresql://localhost:5432/postgres
  - username: to be defined
  - password: to be defined


## Getting Started
- Build the Project : mvn clean install
- Run the Tests : mvn test
- Run the Application : mvn spring-boot:run
- Access the API documentation at http://localhost:52001/swagger-ui.html
