# kompleksowe-dbanie-o-jakosc-oprogramowania

## Clean Code and Testing Practice

This repository is for practicing writing clean code and tests in various languages.

## Prerequisites

- Java 11+
- Maven
- Node.js 14+
- npm

## Running Java Tests

1. Navigate to the root directory of the project where the `pom.xml` file is located.

2. Run the tests using Maven:

    ```sh
    mvn test
    ```

## Running JavaScript Tests

1. Install dependencies:

    ```sh
    npm install
    ```

2. Run all tests:

    ```sh
    npm test
    ```

3. Run specific test file:

    ```sh
    npm test invoice.test.js
    ```

## Project Structure

```
src/
├── main/
│   └── java/
│       └── org/
│           └── kdojo/
│               └── module1/
│                   ├── cleanCode/
│                   └── noCleanCode/
└── test/
    ├── java/
    │   └── org/
    │       └── kdojo/
    │           └── module1/
    │               ├── cleanCode/
    │               └── noCleanCode/
    └── javascript/
        └── invoice.test.js
```
