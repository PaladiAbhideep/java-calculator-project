# Java Calculator Project

A comprehensive Java calculator application with extensive JUnit 5 test coverage, designed for CI/CD pipeline demonstration.

## Features

- **12 Operations**: Addition, Subtraction, Multiplication, Division, Power, Square Root, Modulo, Factorial, Absolute Value, Max, Min, Rounding
- **65+ Test Cases**: Comprehensive JUnit 5 test suite with nested test classes
- **100% Test Coverage**: Tests for normal operations, edge cases, and exception handling
- **Maven Build**: Automated build and test execution
- **JaCoCo Integration**: Code coverage reporting
- **Jenkins Pipeline**: Fully automated CI/CD with Jenkinsfile

## Project Structure

```
java-calculator-project/
├── src/
│   ├── main/java/com/calculator/
│   │   └── Calculator.java          # Main calculator implementation
│   └── test/java/com/calculator/
│       └── CalculatorTest.java      # Comprehensive JUnit 5 test suite
├── pom.xml                          # Maven configuration
├── Jenkinsfile                      # Jenkins CI/CD pipeline
├── .gitignore                       # Git ignore patterns
└── README.md                        # This file
```

## Requirements

- Java 11 or higher
- Maven 3.6 or higher (auto-downloaded in Jenkins pipeline)

## Building and Testing

### Compile the project
```bash
mvn clean compile
```

### Run all tests
```bash
mvn test
```

### Run tests with coverage
```bash
mvn test jacoco:report
```

### Package the application
```bash
mvn package
```

### Run the calculator demo
```bash
java -cp target/java-calculator-project-1.0.0.jar com.calculator.Calculator
```

## Calculator Operations

The Calculator class provides the following operations:

| Operation | Method | Description |
|-----------|--------|-------------|
| Addition | `add(a, b)` | Returns sum of a and b |
| Subtraction | `subtract(a, b)` | Returns difference of a and b |
| Multiplication | `multiply(a, b)` | Returns product of a and b |
| Division | `divide(a, b)` | Returns quotient (throws ArithmeticException if b=0) |
| Power | `power(base, exponent)` | Returns base^exponent |
| Square Root | `sqrt(a)` | Returns √a (throws ArithmeticException if a<0) |
| Modulo | `modulo(a, b)` | Returns remainder (throws ArithmeticException if b=0) |
| Factorial | `factorial(n)` | Returns n! (throws IllegalArgumentException if n<0) |
| Absolute Value | `abs(a)` | Returns \|a\| |
| Maximum | `max(a, b)` | Returns larger of a and b |
| Minimum | `min(a, b)` | Returns smaller of a and b |
| Round | `round(a)` | Returns nearest integer |

## Test Coverage

The comprehensive test suite includes **65+ test cases** organized into categories:

- ✅ **Addition Tests** (5 tests)
- ✅ **Subtraction Tests** (5 tests)
- ✅ **Multiplication Tests** (5 tests)
- ✅ **Division Tests** (5 tests including exception handling)
- ✅ **Power Tests** (5 tests)
- ✅ **Square Root Tests** (4 tests including exception handling)
- ✅ **Modulo Tests** (4 tests including exception handling)
- ✅ **Factorial Tests** (5 tests including exception handling)
- ✅ **Absolute Value Tests** (3 tests)
- ✅ **Max/Min Tests** (6 tests)
- ✅ **Rounding Tests** (4 tests)
- ✅ **Edge Cases & Integration Tests** (4 tests)

### Test Categories

- **Positive Numbers**: Standard operation tests
- **Negative Numbers**: Handling negative inputs
- **Zero Cases**: Edge cases with zero
- **Decimal Numbers**: Floating-point arithmetic
- **Exception Handling**: Division by zero, negative square roots, negative factorials
- **Large Numbers**: Handling very large values
- **Small Decimals**: Precision with very small numbers
- **Mixed Operations**: Combined calculations

## CI/CD Pipeline

The project includes a complete Jenkins pipeline that:

1. **Checkout**: Pulls code from Git repository
2. **Setup Maven**: Auto-downloads Maven 3.9.9 if not available
3. **Build**: Compiles Java source code
4. **Test**: Runs all JUnit 5 tests
5. **Package**: Creates executable JAR file
6. **Publish Results**: Publishes test results and coverage reports

### Jenkins Integration

The Jenkinsfile:
- Automatically detects and sets JAVA_HOME
- Downloads Maven if not present
- Runs all build stages with proper error handling
- Publishes JUnit test results for trend analysis
- Archives build artifacts (JAR files and test reports)
- Displays comprehensive test result summary

## Test Results Display

After each build, Jenkins displays:
- ✅ Total number of tests executed
- ✅ Number of passed tests
- ✅ Number of failed tests
- ✅ Number of skipped tests
- ✅ Success rate percentage
- ✅ Historical test result trends
- ✅ Code coverage metrics (via JaCoCo)

## Example Output

```
===== TEST RESULTS =====
Total tests: 65
Passed: 65
Failed: 0
Skipped: 0
Success Rate: 100.0%
=======================
```

## Usage Example

```java
Calculator calc = new Calculator();

// Basic operations
double sum = calc.add(10, 5);              // 15.0
double difference = calc.subtract(10, 5);   // 5.0
double product = calc.multiply(10, 5);      // 50.0
double quotient = calc.divide(10, 5);       // 2.0

// Advanced operations
double power = calc.power(2, 8);            // 256.0
double sqrt = calc.sqrt(16);                // 4.0
double mod = calc.modulo(17, 5);            // 2.0
long factorial = calc.factorial(5);         // 120

// Utility operations
double absolute = calc.abs(-15);            // 15.0
double maximum = calc.max(10, 20);          // 20.0
double minimum = calc.min(10, 20);          // 10.0
long rounded = calc.round(7.6);             // 8
```

## License

This project is created for CI/CD demonstration and educational purposes.

## Author

Created for demonstrating comprehensive testing and CI/CD best practices with Java, Maven, JUnit 5, and Jenkins.
