# Rest Assured API Automation Framework

A Java-based API automation framework built using **Rest Assured, TestNG, Maven, Log4j2, and Extent Reports**.

This framework automates REST API CRUD operations and provides reusable utilities for configuration, logging, reporting, retry handling, and test execution.

---

## Tech Stack

- Java 21
- Rest Assured 5.5.6
- TestNG 7.11.0
- Maven
- Log4j2
- Extent Reports
- JSON Path
- Git / GitHub

---

## API Under Test

This framework uses the **Restful Booker API** for demonstration and automation practice.

Base URL:

`https://restful-booker.herokuapp.com`

---

## API Test Coverage

The framework covers the following operations:

### Authentication

- Create authentication token

### Booking APIs

- Create Booking
- Get All Bookings
- Get Booking By ID
- Update Booking using PUT
- Update Booking using PATCH
- Delete Booking

---

## Framework Features

### 1. Rest Assured

Used for REST API request creation and response validation.

### 2. TestNG

Used for:

- Test execution
- Test dependencies
- Test ordering
- Assertions
- Test listeners
- Retry mechanism

### 3. Maven

Used for:

- Dependency management
- Project build
- Test execution

Run the complete test suite using:

```bash
mvn clean test"# RestAssuredFramework" 
