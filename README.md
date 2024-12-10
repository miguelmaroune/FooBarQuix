
# Foobarquix Kata

The Foobarquix Kata is a simple algorithm challenge involving transforming a given number into a string based on specific rules. The rules are:

If the number is divisible by 3 or contains the digit 3, the output should be "FOO".
If the number is divisible by 5 or contains the digit 5, the output should be "BAR".
If the number contains the digit 7, the output should be "QUIX".
Divisibility rules take precedence over containing rules.
The evaluation proceeds from left to right.
If none of the rules apply, the number should be returned as a string.
Example Transformations
Input: 1
Output: "1"
(No rule applies)

Input: 3
Output: "FOO"
(Divisible by 3)

Input: 5
Output: "BAR"
(Divisible by 5)

Input: 7
Output: "QUIX"
(Contains the digit 7)

## Table of Contents

1. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Setup Instructions](#setup-instructions)
5. [API Documentation](#api-documentation)
6. [Testing](#testing)
7. [Dockerization](#dockerization)

---
## Feature
Foobarquix Transformation Rules:

Divisibility by 3 or containing 3 results in "FOO".
Divisibility by 5 or containing 5 results in "BAR".
Contains the digit 7 results in "QUIX".
Prioritization of divisibility rules over containing rules.
API for Foobarquix Transformation:

 1. **A RESTful API** is available for transforming numbers into their respective Foobarquix strings.
The API is accessible via the endpoint /api/transform/{number}.
Example transformations:
/api/transform/3 returns FOO
/api/transform/5 returns BAR
/api/transform/15 returns FOOBAR
/api/transform/73 returns FOOQUIX
Input Validation:

 2. **spring batch FooBarQuix Processor** This Spring Batch application runs every minute, processes an input.txt file, and generates an output.txt file. Each line of input is passed to the FooBarQuix service for processing. The job is scheduled automatically and ensures seamless file-based data processing.
    
 3. The application uses a **custom annotation**, @ValidNumber, to validate input numbers between 0 and 100.
This ensures that only valid inputs are processed by the Foobarquix transformation logic.
Global Exception Handling:

4. The application includes a **global exception handler** that gracefully handles invalid inputs and other runtime errors.
ConstraintViolationException and MethodArgumentTypeMismatchException are managed to return appropriate error messages and HTTP status codes.

---

## Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring Batch**
- **Hibernate/JPA**
- **H2 Database**
- **Maven** (build tool)
- **JUnit** (testing framework)
- **Docker**

---
## Setup Instructions

### Prerequisites
- Java 17+
- Maven 3.8+
- Docker

### Clone the Repository
```bash
git clone https://github.com/miguelmaroune/FooBarQuix.git
cd FooBarQuix
```
### Build the Project
To build the project using Maven, run the following command:
```bash
mvn clean package -DskipTests
```
This will clean any previous builds and package the project into a JAR file (target/data-batch-processor-0.0.1-SNAPSHOT.jar
### Run the Application
To run the application locally, use the following command:

```bash
java -jar target/data-batch-processor-0.0.1-SNAPSHOT.jar
```

---
### API Documentation
| Endpoint                                              | Method | Description                                      | Example Input                                   |
|-------------------------------------------------------|--------|-------------------------------------------------|--------------------------------------------------|
| /api/transform/{number}	| GET   | Transforms the given number into its Foobarquix string representation.| `3` |

### Testing
Run Unit Tests
You can execute the unit tests using:
```bash
mvn test -Dtest=**/*Test
```
### Test Coverage:
- **Service Layer**: Ensures the correctness of business logic.

## Dockerization

The Foobarquix application utilizes Docker for simplified deployment and scalability. Below is a summary of the Docker setup and instructions for running the application locally:

**Docker Compose**

The docker-compose.yml file includes two primary services:

1. ***data-batch-processor:***
  - Description: This service runs the Spring Boot application for Foobarquix transformation.
  - Ports: Maps port 8080 on the host to the internal port of the application container.
  - Environment Variables:
       - SPRING_DATASOURCE_URL, SPRING_DATASOURCE_DRIVER_CLASS_NAME, SPRING_DATASOURCE_USERNAME, SPRING_DATASOURCE_PASSWORD configure the in-memory H2 database.
       - BATCH_INPUT and BATCH_OUTPUT define paths for input and output files used by the Foobarquix logic.
  - Volumes: 
       -  Binds the input.txt file from the host to /input/input.txt within the container for reading.
       -  Maps the output directory on the host to /output for writing results.

**Running Locally**

1. ***Setup:***
 - Clone the repository: git clone https://github.com/miguelmaroune/Foobarquix.git
 - Navigate into the project directory: cd Foobarquix/docker 

2. ***Start Service:***
 - Run docker-compose up --build from the root of the project to start both services defined in the docker-compose.yml.
 - This command builds the necessary Docker images and starts the containers.

3.***Using the Application:***
 - Place your input.txt file in the root directory of the project.
 - The application will automatically use this file as input and output the results to output.txt.

4.***Access:*** 
- The Foobarquix application can be accessed at http://localhost:8080.
- The H2 database console is available at http://localhost:8082/h2-console for managing database interactions.

5. ***Configuration Overrides***
 - Environment variables in docker-compose.yml can be customized via a .env file if needed:
 ``` 
INPUT_FILE_PATH=./input.txt
OUTPUT_DIR_PATH=/path/to/output

 ```
 - This allows for easy adjustments without modifying the Docker Compose file directly.

6. ***Stopping Services:***

```
docker-compose down
```
---

