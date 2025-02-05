# Petstore Automation Framework

This project is an automation framework built using **RestAssured**, **JUnit**, **Cucumber**, **Maven**, and **Test Data** represented as examples in **Cucumber Feature Files**. The framework is designed to automate API testing for the Petstore API.

## Table of Contents

1. [Installation](#installation)
2. [Execution](#execution)
3. [Reporting](#reporting)
4. [Project Structure](#project-structure)
5. [Dependencies](#dependencies)

---

## Installation

### Prerequisites

Make sure you have the following installed on your machine:

1. **Java JDK** 8 or later
2. **Maven** 3.6+ (Maven is used to build the project and manage dependencies)
3. **Git** (for cloning the repository)

### Steps

1. **Clone the repository**:

   Clone this repository to your local machine using the following Git command:
   
   ```bash
   git clone https://github.com/gebeeb/petstore.git
   cd petstore
   ```

2. **Install dependencies**:

   After cloning the repository, navigate to the project folder and run the following Maven command to install the required dependencies:

   ```bash
   mvn install
   ```

3. **Check your Java version**:

   To make sure you're using the correct version of Java, run:

   ```bash
   java -version
   ```

   Ensure that your Java version is 11 or higher. If not, download the appropriate version from [here](https://adoptopenjdk.net/).

---

## Execution

### Running Tests with Maven

This framework uses **Cucumber** and **JUnit** for running tests. Cucumber feature files are located in the `src/test/resources` directory, and the step definitions are in the `src/test/java` directory.

To run the tests using **Maven** and **JUnit**, execute the following command:

```bash
mvn test
```

This will run all Cucumber feature files and execute the associated tests.

### Running Specific Features or Tests

To run specific tests, you can specify the feature file path using the `-Dcucumber.options` property. For example, to run a specific feature file:

```bash
mvn test -Dcucumber.filter.tags="@test"
```

Replace `@test` with the tag of the relevant test you want to run

---

## Reporting

### Cucumber HTML Reports

The framework uses **Cucumber** to generate an HTML report after the tests have been executed.

After running the tests with Maven, you can find the **HTML Report** in the following location:

```bash
target/cucumber/report.html
```

You can open this file in a browser to view the detailed test execution report.

### Additional Reports (Optional)

1. **JSON Reports**: If you want to generate a JSON report (useful for integrations with other tools like Jenkins), you can configure it by editing the `pom.xml` and specifying the JSON report plugin.
   
2. **JUnit Reports**: JUnit reports can also be generated, and you can find them in the `target/surefire-reports` directory.

---

## Project Structure

Here’s a quick overview of the project structure:

```plaintext
petstore
│
├── src
│   ├── main
│   │   └── java
│   └── test
│       ├── java
│       │   └── com
│       │       └── stepDefinition
│       │       └── runner
│       │       └── init
│       │       └── util
│       │       └── pet
│       │       └── store
│       │       └── user
│       ├── resources
│       │   └── features
│       │       └── All Pet Tests.feature
│       │       └── All Store Tests.feature
│       │       └── All User Tests.feature
├── pom.xml
└── README.md
```

- `src/test/java/com/stepDefinition`: Contains step definition classes for Cucumber.
- `src/test/java/com/runner`: Contains test runner classes for Cucumber.
- `src/test/resources/features`: Contains Cucumber feature files with test scenarios and examples.
- `pom.xml`: The Maven configuration file for managing dependencies, plugins, and build settings.

---

## Dependencies

The following dependencies are included in the project:

- **RestAssured**: Used for sending HTTP requests and validating responses.
- **JUnit**: A testing framework used to run the tests.
- **Cucumber**: Used for behavior-driven development (BDD), enabling test case definitions in Gherkin syntax.
- **Maven**: Dependency management and build automation.
  
These dependencies are listed in the `pom.xml` file.

---

## Contributing

Feel free to fork the repository, create a feature branch, and submit pull requests for any changes or improvements. Please ensure that you follow the code style guidelines and add proper tests for your changes.

---

Let me know if you need further adjustments!