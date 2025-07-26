package com.agarwalrohit;

/*
 * Sources 1:
 *   For display settings:
 *       https://stackoverflow.com/questions/47296215/set-labels-and-text-field-alignment-in-javafx
 *       https://stackoverflow.com/questions/51151856/what-is-the-difference-between-fx-padding-and-fx-label-padding-in-javafx-css
 *       https://stackoverflow.com/questions/35517104/center-text-in-gridpane
 *       https://jenkov.com/tutorials/javafx/gridpane.html
 *
 *  Event Handler for text field:
 *       https://stackoverflow.com/questions/42943652/how-to-trigger-an-event-on-focus-out-for-a-textfield-in-javafx-using-fxml
 *       https://stackoverflow.com/questions/30160899/value-change-listener-for-javafxs-textfield
 *       https://stackoverflow.com/questions/24231610/javafx-red-border-for-text-field
 *       https://stackoverflow.com/questions/27712213/how-do-i-make-a-simple-solid-border-around-a-flowpane-in-javafx
 *       //https://docs.oracle.com/javafx/2/ui_controls/label.htm#:~:text=Wrapping%20Text&text=To%20break%20up%20(wrap)%20the,shown%20in%20Example%202%2D4.&text=Label%20label3%20%3D%20new%20Label(%22A%20label%20that%20needs%20to,setWrapText(true)%3B
 *
 *  Source 2: IntelliJ IDE's feature: https://www.jetbrains.com/help/idea/full-line-code-completion.html
 *  Source 3: IntelliJ IDE's feature: https://www.jetbrains.com/help/idea/class-diagram.html
 * 
 * Student Grade Calculator - A JavaFX application for calculating weighted grades
 * 
 * Features:
 * - Real-time input validation with visual feedback
 * - Weighted grade calculation
 * - Support for up to 5 assignments
 * - Letter grade conversion
 * 
 * @author Rohit Agarwal
 * @version 1.0.0
 */



import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentGradeCalculator extends Application {
    
    // Constants for better maintainability
    private static final int MAX_ASSIGNMENTS = 5;
    private static final int COLUMNS = 3;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;
    private static final int COLUMN_WIDTH = 150;
    private static final int MIN_FIELD_WIDTH = 140;
    private static final double PERFECT_WEIGHTAGE = 100.0;
    
    // Color constants for validation feedback
    private static final Color NEUTRAL_BORDER = Color.rgb(187, 187, 187);
    private static final Color VALID_BORDER = Color.rgb(174, 255, 193);
    private static final Color INVALID_BORDER = Color.rgb(240, 100, 92);
    
    // Validation patterns
    private static final String ASSIGNMENT_PATTERN = "[a-zA-Z ]+";
    private static final String NUMERIC_PATTERN = "[0-9]*(\\.?[0-9]+)?";
    
    // UI Components
    private TextField[][] textFields;
    private Label resultsLabel;
    
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Student Grade Calculator");
        stage.setMinWidth(WINDOW_WIDTH);
        stage.setMinHeight(WINDOW_HEIGHT);
        stage.setResizable(true);
        
        GridPane root = createMainLayout();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Creates the main layout with all UI components
     */
    private GridPane createMainLayout() {
        GridPane root = new GridPane();
        setupGridPane(root);
        
        // Initialize components
        textFields = new TextField[COLUMNS][MAX_ASSIGNMENTS];
        resultsLabel = new Label("Please enter details to calculate");
        
        // Add components to layout
        addHelpText(root);
        addColumnHeaders(root);
        addTextFields(root);
        addButtons(root);
        addResultsLabel(root);
        
        return root;
    }
    
    /**
     * Configures the main GridPane layout properties
     */
    private void setupGridPane(GridPane root) {
        root.getColumnConstraints().addAll(
            new ColumnConstraints(COLUMN_WIDTH),
            new ColumnConstraints(COLUMN_WIDTH), 
            new ColumnConstraints(COLUMN_WIDTH)
        );
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(20, 5, 5, 5));
        root.setAlignment(Pos.TOP_CENTER);
        root.setMinWidth(490);
    }
    
    /**
     * Adds help text to the layout
     */
    private void addHelpText(GridPane root) {
        Label helpText = new Label(
            "Instructions:\n" +
            "1. Assignment Type: Enter alphabetic characters only (a-z, A-Z, spaces allowed)\n" +
            "2. Grade: Enter numeric values (decimals allowed, no negatives)\n" +
            "3. Weightage: Enter percentage values that total exactly 100%"
        );
        helpText.setPadding(new Insets(5));
        helpText.setWrapText(true);
        helpText.setTextAlignment(TextAlignment.JUSTIFY);
        
        root.add(helpText, 0, 0, COLUMNS, 7);
    }
    
    /**
     * Adds column headers to the layout
     */
    private void addColumnHeaders(GridPane root) {
        String[] headers = {"Assignment Type", "Grade", "Weightage"};
        HPos[] alignments = {HPos.CENTER, HPos.CENTER, HPos.CENTER};
        
        for (int i = 0; i < headers.length; i++) {
            Label header = createLabel(headers[i]);
            GridPane.setHalignment(header, alignments[i]);
            root.add(header, i, 8);
        }
    }
    
    /**
     * Creates and adds text fields to the layout
     */
    private void addTextFields(GridPane root) {
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < MAX_ASSIGNMENTS; row++) {
                TextField field = createTextField(col, row);
                textFields[col][row] = field;
                root.add(field, col, row + 9);
            }
        }
    }
    
    /**
     * Creates a configured text field
     */
    private TextField createTextField(int col, int row) {
        TextField field = new TextField();
        field.setPrefWidth(100);
        field.setMinWidth(MIN_FIELD_WIDTH);
        field.setPromptText(getPromptText(col, row));
        field.setId(getFieldId(col, row));
        field.setBorder(createBorder(NEUTRAL_BORDER));
        
        // Add validation listener
        setupFieldValidation(field, col, row);
        
        return field;
    }
    
    /**
     * Sets up validation for a text field
     */
    private void setupFieldValidation(TextField field, int col, int row) {
        field.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Lost focus
                validateField(field, col, row);
            }
        });
    }
    
    /**
     * Validates a single field and updates its appearance
     */
    private void validateField(TextField field, int col, int row) {
        String text = field.getText().trim();
        
        if (text.isEmpty()) {
            field.setBorder(createBorder(NEUTRAL_BORDER));
            updateWeightageStatus();
            return;
        }
        
        boolean isValid = false;
        
        if (col == 0) { // Assignment column
            isValid = text.matches(ASSIGNMENT_PATTERN);
        } else { // Grade or Weightage columns
            isValid = text.matches(NUMERIC_PATTERN) && 
                     !text.equals(".") && 
                     Double.parseDouble(text) >= 0;
        }
        
        if (isValid) {
            field.setBorder(createBorder(VALID_BORDER));
        } else {
            field.setBorder(createBorder(INVALID_BORDER));
            field.setText("");
        }
        
        updateWeightageStatus();
    }
    
    /**
     * Updates the weightage status message
     */
    private void updateWeightageStatus() {
        WeightageStatus status = checkWeightageStatus();
        
        switch (status) {
            case PERFECT:
                resultsLabel.setText("Ready to calculate!");
                resultsLabel.setTextFill(Color.BLACK);
                break;
            case INCOMPLETE:
                resultsLabel.setText("Please enter details to calculate");
                resultsLabel.setTextFill(Color.BLACK);
                break;
            case UNDER_100:
                resultsLabel.setText("Weightage does not add up to 100%. Please check.");
                resultsLabel.setTextFill(Color.RED);
                break;
            case OVER_100:
                resultsLabel.setText("Weightage exceeds 100%. Please check.");
                resultsLabel.setTextFill(Color.RED);
                break;
        }
    }
    
    /**
     * Adds calculate and clear buttons to the layout
     */
    private void addButtons(GridPane root) {
        Button calculateBtn = createButton("Calculate", this::calculateGrade);
        Button clearBtn = createButton("Clear", this::clearAllFields);
        
        root.add(calculateBtn, 0, 14);
        root.add(clearBtn, 1, 14);
    }
    
    /**
     * Adds the results label to the layout
     */
    private void addResultsLabel(GridPane root) {
        resultsLabel.setPadding(new Insets(5));
        GridPane.setHalignment(resultsLabel, HPos.LEFT);
        root.add(resultsLabel, 0, 16, COLUMNS, 1);
    }
    
    /**
     * Handles the calculate button action
     */
    private void calculateGrade() {
        if (!isReadyToCalculate()) {
            resultsLabel.setText("Please complete all assignment details before calculating.");
            resultsLabel.setTextFill(Color.RED);
            return;
        }
        
        double totalScore = calculateWeightedScore();
        String letterGrade = getLetterGrade(totalScore);
        
        resultsLabel.setText(String.format("Final Grade: %.2f%% (%s)", totalScore, letterGrade));
        resultsLabel.setTextFill(Color.DARKGREEN);
    }
    
    /**
     * Handles the clear button action
     */
    private void clearAllFields() {
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < MAX_ASSIGNMENTS; row++) {
                textFields[col][row].setText("");
                textFields[col][row].setBorder(createBorder(NEUTRAL_BORDER));
            }
        }
        resultsLabel.setText("Please enter details to calculate");
        resultsLabel.setTextFill(Color.BLACK);
    }

    
    // ===============================
    // Helper Methods
    // ===============================
    
    /**
     * Checks if all required fields are completed for calculation
     */
    private boolean isReadyToCalculate() {
        boolean hasAtLeastOneAssignment = false;
        
        for (int row = 0; row < MAX_ASSIGNMENTS; row++) {
            String assignment = textFields[0][row].getText().trim();
            String grade = textFields[1][row].getText().trim();
            String weightage = textFields[2][row].getText().trim();
            
            if (!assignment.isEmpty()) {
                hasAtLeastOneAssignment = true;
                // If assignment is filled, grade and weightage must also be filled
                if (grade.isEmpty() || weightage.isEmpty()) {
                    return false;
                }
            }
        }
        
        return hasAtLeastOneAssignment && checkWeightageStatus() == WeightageStatus.PERFECT;
    }
    
    /**
     * Calculates the weighted score based on filled assignments
     */
    private double calculateWeightedScore() {
        double totalScore = 0.0;
        
        for (int row = 0; row < MAX_ASSIGNMENTS; row++) {
            String assignment = textFields[0][row].getText().trim();
            
            if (!assignment.isEmpty()) {
                double grade = Double.parseDouble(textFields[1][row].getText().trim());
                double weightage = Double.parseDouble(textFields[2][row].getText().trim());
                totalScore += grade * (weightage / 100.0);
            }
        }
        
        return totalScore;
    }
    
    /**
     * Checks the current weightage status
     */
    private WeightageStatus checkWeightageStatus() {
        double totalWeightage = 0.0;
        boolean hasAssignments = false;
        boolean hasIncompleteEntries = false;
        
        for (int row = 0; row < MAX_ASSIGNMENTS; row++) {
            String assignment = textFields[0][row].getText().trim();
            String weightage = textFields[2][row].getText().trim();
            
            if (!assignment.isEmpty()) {
                hasAssignments = true;
                if (weightage.isEmpty()) {
                    hasIncompleteEntries = true;
                } else {
                    totalWeightage += Double.parseDouble(weightage);
                }
            }
        }
        
        if (!hasAssignments || hasIncompleteEntries) {
            return WeightageStatus.INCOMPLETE;
        }
        
        if (Math.abs(totalWeightage - PERFECT_WEIGHTAGE) < 0.01) {
            return WeightageStatus.PERFECT;
        } else if (totalWeightage < PERFECT_WEIGHTAGE) {
            return WeightageStatus.UNDER_100;
        } else {
            return WeightageStatus.OVER_100;
        }
    }
    
    /**
     * Converts numeric score to letter grade
     */
    private String getLetterGrade(double score) {
        if (score >= 96) return "A";
        if (score >= 90) return "A-";
        if (score >= 85) return "B";
        if (score >= 80) return "B-";
        if (score >= 75) return "C";
        if (score >= 70) return "C-";
        if (score >= 60) return "D";
        return "F";
    }
    
    /**
     * Creates a border with the specified color
     */
    private Border createBorder(Color color) {
        return new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, 
                                         new CornerRadii(2), BorderWidths.DEFAULT));
    }
    
    /**
     * Creates a label with standard padding
     */
    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setPadding(new Insets(5));
        return label;
    }
    
    /**
     * Creates a button with the specified text and action
     */
    private Button createButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setPadding(new Insets(5));
        button.setOnAction(e -> action.run());
        return button;
    }
    
    /**
     * Generates prompt text for text fields
     */
    private String getPromptText(int col, int row) {
        String[] prefixes = {"Assignment Type", "Grade", "Weightage"};
        return prefixes[col] + " " + (row + 1);
    }
    
    /**
     * Generates ID for text fields
     */
    private String getFieldId(int col, int row) {
        String[] prefixes = {"assignment", "grade", "weightage"};
        return prefixes[col] + (row + 1);
    }
    
    // ===============================
    // Enums and Inner Classes
    // ===============================
    
    /**
     * Represents the status of weightage validation
     */
    private enum WeightageStatus {
        PERFECT,     // Exactly 100%
        INCOMPLETE,  // Missing assignments or weightages
        UNDER_100,   // Less than 100%
        OVER_100     // More than 100%
    }
    
    public static void main(String[] args) {
        launch();
    }
}
