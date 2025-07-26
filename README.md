# Student Grade Tracker

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
- **Gradle** (included via Gradle Wrapper)
- **JavaFX 23** (automatically downloaded by Gradle)

## Project Structure

```
AgarwalRohit/
├── src/
│   └── main/
│       └── java/
│           ├── module-info.java                 # Java module configuration
│           └── com/
│               └── agarwalrohit/
│                   └── AgarwalRohitA7.java      # Main application class
├── gradle/
│   └── wrapper/                                 # Gradle wrapper files
├── build.gradle                                 # Build configuration
├── settings.gradle                              # Gradle project settings
├── gradlew                                      # Gradle wrapper (Unix)
├── gradlew.bat                                  # Gradle wrapper (Windows)
└── README.md                                    # This file
```

## How to Run

### Method 1: Using Gradle (Recommended)

1. **Clone or navigate to the project directory:**
   ```bash
   git clone https://github.com/rohitrajagarwal/student-grade-calculator.git
   cd student-grade-calculator
   ```

2. **Run the application:**
   ```bash
   ./gradlew run
   ```
   
   On Windows:
   ```cmd
   gradlew.bat run
   ```

### Method 2: Using IntelliJ IDEA

1. **Open the project in IntelliJ IDEA**
2. **Navigate to** `src/main/java/com/agarwalrohit/AgarwalRohitA7.java`
3. **Right-click on the file** and select "Run 'AgarwalRohitA7.main()'"
4. **Or click the green play button** next to the `main` method

### Method 3: Building and Running JAR

1. **Build the project:**
   ```bash
   ./gradlew build
   ```

2. **Run with JavaFX modules:**
   ```bash
   java --module-path /path/to/javafx/lib --add-modules javafx.controls -cp build/libs/AgarwalRohit-1.0-SNAPSHOT.jar com.agarwalrohit.AgarwalRohitA7
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
   - The Gradle build automatically handles JavaFX dependencies

2. **Window doesn't appear:**
   - Check if the application is running in the background
   - Try running from command line to see any error messages

3. **Build fails:**
   - Ensure Java 23 is installed: `java --version`
   - Clean and rebuild: `./gradlew clean build`

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
./gradlew build

# Run tests
./gradlew test

# Run the application
./gradlew run
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
