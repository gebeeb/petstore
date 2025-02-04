# Test Automation Project

This repository contains test code written in **Java** with **Cucumber** and **RestAssured** for API testing. The tests are configured to run with **Maven**.

## Table of Contents

- [Installation Steps](#installation-steps)
- [Execution Steps](#execution-steps)
- [Folder Structure](#folder-structure)
- [Dependencies](#dependencies)

---

## Installation Steps

To set up the project on your local machine, follow these steps:

### Prerequisites

Ensure the following are installed on your machine:

- **Java 8 or later**: [Download Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **Maven**: [Download Maven](https://maven.apache.org/download.cgi)
- **Git**: [Download Git](https://git-scm.com/downloads)
- **IDE (Optional)**: IntelliJ IDEA, Eclipse, or your preferred IDE that supports Java, Maven, and Cucumber.

### Step-by-Step Installation

1. **Clone the Repository**

   Clone the repository to your local machine using Git:

   ```bash
   git clone https://github.com/gebeeb/petstore.git
   cd your-repository-folder
   
 2. **Set up Maven Project**
 
	mvn clean install

## Execution Steps	
1. Running Tests with Cucumber TAgs

	mvn test -Dcucumber.options="--tags @test"

## Folder Structure
	├── src
│   ├── main
│   │   └── java
│   │       └── (Your Java code here)
│   ├── test
│   │   ├── java
│   │   │   └── (Test code here, including step definitions)
│   │   └── resources
│   │       └── features
│   │           └── (Cucumber feature files here)
├── pom.xml
└── README.md

## Dependencies
<dependencies>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>YOUR_CUCUMBER_VERSION</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>YOUR_RESTASSURED_VERSION</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>YOUR_JUNIT_VERSION</version>
        <scope>test</scope>
    </dependency>
    <!-- Additional dependencies -->
</dependencies>