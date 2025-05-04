# kompleksowe-dbanie-o-jakosc-oprogramowania

## Clean Code and Testing Practice

This repository is for practicing writing clean code and tests in various languages.

## Prerequisites

- Java 11+
- Maven
- Node.js 14+
- npm
- Python 3.8+
- pip

## Running Java Tests

1. Navigate to the root directory of the project where the `pom.xml` file is located.

2. Install dependencies:

    ```sh
    mvn clean install
    ```

3. Run the tests using Maven:

    ```sh
    mvn test
    ```

## Java Testing Dependencies

The project uses the following testing libraries:
- JUnit 5 - for test framework
- AssertJ - for fluent assertions
- Mockito - for mocking in tests

## Java Runtime Dependencies

The project uses the following runtime libraries:
- JavaMail API - for email functionality
- Dotenv Java - for environment variables management
- MySQL Connector - for database connectivity

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

## Running Python Tests

1. Install dependencies:

    ```sh
    pip install -r requirements.txt
    ```

2. Run all tests:

    ```sh
    python3 -m pytest -v
    ```

3. Run specific test file:

    ```sh
    python3 -m pytest src/test/python/module2/test_invoice.py -v
    ```

4. Run tests with coverage:

    ```sh
    python3 -m pytest src/test/python/module2/test_invoice.py --cov=src/main/python
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

## Python Project Setup

The Python project requires the following files:

- `requirements.txt` - Python dependencies (pytest and pytest-cov)
- `src/__init__.py` - marks the src directory as a Python package
- `src/python/__init__.py` - marks the python directory as a package
- `src/python/module2/__init__.py` - marks the module2 directory as a package

This minimal setup allows for proper Python module imports and test execution.
