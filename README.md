# Student Grade Calculator

A JavaFX application for calculating student grades based on assignment types, grades, and weightages. This application provides an intuitive GUI for entering assignment details and automatically calculates the final grade with proper validation.

**Repository**: [https://github.com/rohitrajagarwal/student-grade-calculator.git](https://github.com/rohitrajagarwal/student-grade-calculator.git)

## Features

- **Input Validation**: Real-time validation for assignment types (alphabetic characters only) and numeric fields (grades and weightages)
- **Visual Feedback**: Color-coded borders (green for valid, red for invalid input)
- **Grade Calculation**: Automatically calculates final grade based on weighted averages
- **Weightage Validation**: Ensures total weightage equals 100%
- **Grade Scale**: Uses standard letter grading (A, A-, B, B-, C, C-, D, F)
- **Clear Functionality**: Reset all fields with a single click

## Prerequisites

- **Java 23** or higher
- **VS Code** with Extension Pack for Java
- **Gradle** (can be installed via Homebrew: `brew install gradle`)
- **JavaFX 23 SDK** (download from [OpenJFX](https://openjfx.io/))
  - Extract to a directory like `/path/to/javafx-sdk-23.x.x/`
  - Note the `lib` folder path for runtime configuration

## Project Structure

```
student-grade-calculator/
├── src/
│   └── main/
│       └── java/
│           ├── module-info.java                 # Java module configuration
│           └── com/
│               └── agarwalrohit/
│                   └── StudentGradeCalculator.java  # Main application class
├── gradle/
│   └── wrapper/                                 # Gradle wrapper files
├── build.gradle                                 # Build configuration
├── settings.gradle                              # Gradle project settings
├── .gitignore                                   # Git ignore file
├── gradlew                                      # Gradle wrapper (Unix)
├── gradlew.bat                                  # Gradle wrapper (Windows)
└── README.md                                    # This file
```

## How to Run

### Method 1: Using VS Code (Recommended for Development)

1. **Install the Extension Pack for Java** if not already installed
2. **Open the project folder** in VS Code
3. **Navigate to** `src/main/java/com/agarwalrohit/StudentGradeCalculator.java`
4. **Click the "Run" button** that appears above the `main` method
5. **Or use Command Palette** (`Cmd+Shift+P` / `Ctrl+Shift+P`) → "Java: Run"

*Note: VS Code with the Java Extension Pack automatically handles JavaFX dependencies and module configuration.*

### Method 2: Using Gradle (Requires proper JavaFX setup)

1. **Clone or navigate to the project directory:**
   ```bash
   git clone https://github.com/rohitrajagarwal/student-grade-calculator.git
   cd student-grade-calculator
   ```

2. **Build the project:**
   ```bash
   gradle clean build
   ```

3. **Run the application (if Gradle JavaFX plugin works):**
   ```bash
   gradle run
   ```

### Method 3: Direct Java Execution (Recommended for Reliable Execution)

1. **Build the project first:**
   ```bash
   cd /path/to/student-grade-calculator
   gradle clean build
   ```

2. **Run with explicit JavaFX module configuration:**
   ```bash
   java --module-path /path/to/javafx-sdk-23.x.x/lib \
        --add-modules javafx.controls,javafx.fxml \
        --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED \
        -cp "build/classes/java/main" \
        com.agarwalrohit.StudentGradeCalculator
   ```

   **Example on macOS (adjust paths for your system):**
   ```bash
   java --module-path /Users/username/Library/Java/JavaVirtualMachines/javafx-sdk-23.0.2/lib \
        --add-modules javafx.controls,javafx.fxml \
        --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED \
        -cp "build/classes/java/main" \
        com.agarwalrohit.StudentGradeCalculator
   ```

### Method 4: Building and Running JAR (Advanced)

1. **Build the project:**
   ```bash
   gradle build
   ```

2. **Run with JavaFX modules:**
   ```bash
   java --module-path /path/to/javafx-sdk-23.x.x/lib \
        --add-modules javafx.controls \
        -cp build/libs/student-grade-calculator-1.0-SNAPSHOT.jar \
        com.agarwalrohit.StudentGradeCalculator
   ```

## Setup Instructions

### Step-by-Step Setup

1. **Install Java 23:**
   - Download from [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
   - Verify installation: `java --version`

2. **Install Gradle:**
   - **macOS:** `brew install gradle`
   - **Windows:** Download from [Gradle.org](https://gradle.org/install/)
   - **Linux:** Use package manager or download binary
   - Verify installation: `gradle --version`

3. **Download JavaFX SDK:**
   - Visit [OpenJFX Downloads](https://openjfx.io/)
   - Download JavaFX 23 SDK for your platform
   - Extract to a permanent location (e.g., `/Users/username/Library/Java/JavaVirtualMachines/javafx-sdk-23.x.x/`)
   - Note the path to the `lib` folder

4. **Install VS Code Extensions:**
   - Install "Extension Pack for Java" from VS Code marketplace
   - This includes Language Support, Debugger, Test Runner, Maven, and Project Manager for Java

5. **Clone and Build Project:**
   ```bash
   git clone https://github.com/rohitrajagarwal/student-grade-calculator.git
   cd student-grade-calculator
   gradle clean build
   ```

## Usage Instructions

### Input Guidelines

1. **Assignment Type**: 
   - Enter assignment names using only alphabetic characters (a-z, A-Z)
   - Spaces are allowed
   - Examples: "Homework", "Quiz", "Final Exam"

2. **Grade**: 
   - Enter numeric values (integers or decimals)
   - Negative numbers are not accepted
   - Examples: 85, 92.5, 78

3. **Weightage**: 
   - Enter percentage values as numbers (without % symbol)
   - Must total exactly 100 across all assignments
   - Examples: 20, 15.5, 30

### Application Workflow

1. **Enter assignment details** in the corresponding fields
2. **Real-time validation** provides immediate feedback:
   - **Gray border**: Default/empty field
   - **Green border**: Valid input
   - **Red border**: Invalid input (field will be cleared)
3. **Monitor weightage total** - application alerts if total ≠ 100%
4. **Click "Calculate"** to compute final grade
5. **Use "Clear"** to reset all fields

### Grade Scale

| Percentage Range | Letter Grade |
|------------------|--------------|
| 96-100           | A            |
| 90-95            | A-           |
| 85-89            | B            |
| 80-84            | B-           |
| 75-79            | C            |
| 70-74            | C-           |
| 60-69            | D            |
| Below 60         | F            |

## Troubleshooting

### Common Issues

1. **JavaFX Runtime Error:**
   - Ensure you're using Java 23 or higher with JavaFX support
   - Download JavaFX SDK separately from [OpenJFX](https://openjfx.io/)
   - Use Method 3 (Direct Java Execution) for most reliable results

2. **Window doesn't appear:**
   - Check if the application is running in the background
   - Try running from VS Code's integrated terminal to see any error messages
   - Ensure JavaFX SDK path is correct in the command

3. **Build fails:**
   - Ensure Java 23 is installed: `java --version`
   - Clean and rebuild: `gradle clean build`
   - Check that JAVA_HOME is set correctly

4. **Module path issues:**
   - Verify JavaFX SDK is downloaded and extracted properly
   - Update the `--module-path` to point to your JavaFX `lib` directory
   - Example paths:
     - **macOS:** `/Users/username/Library/Java/JavaVirtualMachines/javafx-sdk-23.x.x/lib`
     - **Windows:** `C:\Program Files\Java\javafx-sdk-23.x.x\lib`
     - **Linux:** `/usr/local/javafx-sdk-23.x.x/lib`

### System Requirements

- **macOS**: 10.15+ (Catalina or later)
- **Windows**: Windows 10 or later
- **Linux**: Most modern distributions
- **Memory**: 512MB RAM minimum
- **Display**: 500x500 pixels minimum resolution

## Development

### Building from Source

```bash
# Clone the repository
git clone https://github.com/rohitrajagarwal/student-grade-calculator.git
cd student-grade-calculator

# Build the project
gradle build

# Run tests
gradle test

# Run the application (Method 3 - most reliable)
java --module-path /path/to/javafx-sdk-23.x.x/lib \
     --add-modules javafx.controls,javafx.fxml \
     --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED \
     -cp "build/classes/java/main" \
     com.agarwalrohit.StudentGradeCalculator
```

### Dependencies

- **JavaFX 23**: Core UI framework
- **JUnit 5.10.2**: Testing framework

## Technical Details

- **Architecture**: Pure JavaFX application with programmatic UI creation
- **Module System**: Uses Java Platform Module System (JPMS)
- **Build Tool**: Gradle with JavaFX plugin
- **UI Layout**: GridPane-based responsive layout
- **Validation**: Real-time input validation with visual feedback

## License

This project is created for educational purposes.

## Author

Rohit Agarwal

---

For questions or issues, please refer to the source code comments or contact the author.
