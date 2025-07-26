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
 */



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    @Override
    public void start(Stage stage) throws IOException {
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 500, 500);

        //set stage settings
        stage.setTitle("Student Grade Tracker");
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.setResizable(true);

        // Add padding, columns widths, and center alignment to gridPane contents.
        root.getColumnConstraints().addAll( new ColumnConstraints( 150 ), new ColumnConstraints( 150 ),new ColumnConstraints( 150 ));
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(20,5,5,5));
        root.setAlignment(Pos.TOP_CENTER);
        root.setMinWidth(490);

        //Labels: creating 3 labels for all 3 columns defined in the GridPane and set it to center alignment
        Label assignment = new Label("Assignment Type");
        assignment.setPadding(new Insets(5,5,5,5));
        Label grade = new Label("Grade");
        grade.setPadding(new Insets(5,5,5,5));
        Label weightage = new Label("Weightage");
        weightage.setPadding(new Insets(5,5,5,5));
        Label results = new Label("");
        results.setPadding(new Insets(5,5,5,5));
        Label helpText = new Label("Note: \n" +
                "1. For Assignment Type please enter values with consists of only a-z and/or A-z. No symbols or numeric values are accepted.\n\n" +
                "2. For Grade and Weightage please enter a double value. Negatives numbers are not accepted.\n\n" +
                "3. For Weightage please ensure the total weightage is summing up to 100%.");
        helpText.setPadding(new Insets(5,5,5,5));
        helpText.setWrapText(true);
        helpText.setTextAlignment(TextAlignment.JUSTIFY);
        // alignment of labels within each grid
        GridPane.setHalignment(assignment, HPos.CENTER);
        GridPane.setHalignment(grade, HPos.CENTER);
        GridPane.setHalignment(weightage, HPos.CENTER);
        GridPane.setHalignment(results, HPos.LEFT);

        //create text fields in a grid format.
        TextField[][] textFields = new TextField[3][5];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                textFields[i][j] = new TextField();
                textFields[i][j].setPrefWidth(100);
                textFields[i][j].setMinWidth(140);
                textFields[i][j].setPromptText(getTextFieldPromptText(i,j));
                textFields[i][j].setId(getTextFieldIDString(i,j));

            }
        }

        // create calculate and clear buttons
        Button calculate = new Button("Calculate");
        calculate.setPadding(new Insets(5,5,5,5));
        Button clear = new Button("Clear");
        clear.setPadding(new Insets(5,5,5,5));

        //add help text to the root:
        root.add(helpText, 0, 0, 3, 7);
        // add labels at the top of each column in grid to improve readability.
        root.add(assignment, 0, 8);
        root.add(grade, 1, 8);
        root.add(weightage, 2, 8);

        // add all text fields to the grid pane. i = column, j = row
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                root.add(textFields[i][j], i, j+9);
            }
        }
        //add buttons 1 row (i.e., row 8) after all the fields just to provide additional default padding from the last set of textFields
        root.add(calculate, 0, 14);
        root.add(clear, 1, 14);

        // add results Label
        root.add(results, 0, 16 , 3, 1);



        //set up event handlers for text fields
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                final int col = i;
                final int row = j;

                textFields[i][j].focusedProperty().addListener((observable, oldValue, newValue) -> {
                    if(!newValue){
                        textFields[col][row].setBorder(new Border(new BorderStroke(Color.rgb(187, 187, 187),
                                BorderStrokeStyle.SOLID, new CornerRadii(2), BorderWidths.DEFAULT)));
                    }
                });

                textFields[i][j].focusedProperty().addListener((observable, oldValue, newValue) -> {
                    if(!newValue){
                        if(textFields[col][row].getId().equals(getTextFieldIDString(col,row)) && textFields[col][row].getId().contains("assignment")) {
                            if(!textFields[col][row].getText().isEmpty()) {
                                if (!textFields[col][row].getText().matches("[a-zA-Z ]+")) {
                                    textFields[col][row].setBorder(new Border(new BorderStroke(Color.rgb(240, 100, 92),
                                            BorderStrokeStyle.SOLID, new CornerRadii(2), BorderWidths.DEFAULT)));
                                    textFields[col][row].setText("");
                                } else {
                                    textFields[col][row].setBorder(new Border(new BorderStroke(Color.rgb(174, 255, 193),
                                            BorderStrokeStyle.SOLID, new CornerRadii(2), BorderWidths.DEFAULT)));
                                }
                            }else{
                                textFields[col][row].setBorder(new Border(new BorderStroke(Color.rgb(187, 187, 187),
                                        BorderStrokeStyle.SOLID, new CornerRadii(2), BorderWidths.DEFAULT)));

                            }
                        }else if(textFields[col][row].getId().equals(getTextFieldIDString(col,row)) &&
                                (textFields[col][row].getId().contains("grade") || textFields[col][row].getId().contains("weightage"))) {
                            if(!textFields[col][row].getText().isEmpty()) {
                                if (!textFields[col][row].getText().matches("[0-9]*(\\.?[0-9]+)?")) {
                                    textFields[col][row].setBorder(new Border(new BorderStroke(Color.rgb(240, 100, 92),
                                            BorderStrokeStyle.SOLID, new CornerRadii(2), BorderWidths.DEFAULT)));
                                    textFields[col][row].setText("");
                                } else {
                                    textFields[col][row].setBorder(new Border(new BorderStroke(Color.rgb(174, 255, 193),
                                            BorderStrokeStyle.SOLID, new CornerRadii(2), BorderWidths.DEFAULT)));
                                }
                            }else{
                                textFields[col][row].setBorder(new Border(new BorderStroke(Color.rgb(187, 187, 187),
                                        BorderStrokeStyle.SOLID, new CornerRadii(2), BorderWidths.DEFAULT)));


                            }
                            int tWeightage = checkTotalWeightage(textFields);
                            switch(tWeightage){
                                case 1: //when total weightage is 100%
                                    results.setText("Please enter details to calculate");
                                    results.setTextFill(Color.BLACK);
                                    break;
                                case 2: //when total weightage is < 100%
                                    boolean isAnyWeightageEmpty = false;
                                    for(int b = 0; b < 5; b++) {
                                        if( !textFields[0][b].getText().isEmpty()  // check if assignment is empty.
                                                && textFields[2][b].getText().isEmpty()){// check if the corresponding weightage is empty
                                            isAnyWeightageEmpty = true;
                                            break;
                                        }
                                    }
                                    if(textFields[0][0].getText().isEmpty()
                                    && textFields[0][1].getText().isEmpty()
                                    && textFields[0][2].getText().isEmpty()
                                    && textFields[0][3].getText().isEmpty()
                                        && textFields[0][4].getText().isEmpty()) {
                                        isAnyWeightageEmpty = true;
                                    }

                                    if(isAnyWeightageEmpty){
                                        results.setText("Please enter details to calculate");
                                        results.setTextFill(Color.BLACK);
                                    }else{
                                        results.setText("Weightage does not add up to 100%. Please check.");
                                        results.setTextFill(Color.RED);

                                    }
                                    break;
                                case 3: //when total weightage is > 100%
                                    results.setText("Weightage exceeds 100%. Please check.");
                                    results.setTextFill(Color.RED);
                                    break;

                            }
                        }
                    }
                });
            }
        }

        // create event handler for buttons
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 5; j++) {
                        textFields[i][j].setText("");
                        textFields[i][j].setBorder(new Border(new BorderStroke(Color.rgb(187,187, 187),
                                BorderStrokeStyle.SOLID, new CornerRadii(2), BorderWidths.DEFAULT)));
                    }
                }
                results.setText("Please enter details to calculate");
            }
        });

        calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean[] flags = new boolean[5];
                boolean readyToCalculate = true;
                double totalScore = -1;
                for (int j = 0; j < 5; j++) {
                    //checking if assignment field is empty.
                    if(textFields[0][j].getId().contains("assignment") && textFields[0][j].getText().isEmpty()){
                        flags[j] = false; // assignment input field is empty. therefore, ignore weightage
                    }else{
                        flags[j] = true;
                    }
                    // if assignment field is found to be not empty, check if the grade or weightage column is empty.
                    // if either of them is empty, we are not ready to  calculate. otherwise, continue to assume we are ready for calculation.
                    if(flags[j] && (textFields[1][j].getText().isEmpty()  || textFields[2][j].getText().isEmpty())){
                        readyToCalculate = false;
                    }
                }
                if(textFields[0][0].getText().isEmpty()
                && textFields[0][1].getText().isEmpty()
                    && textFields[0][2].getText().isEmpty()
                    && textFields[0][3].getText().isEmpty()
                    && textFields[0][4].getText().isEmpty()) {
                    readyToCalculate = false;
                }
                // if ready to calculate impiles: all values in assignment columns has a corresponding weightage values in the weightage column.
                // not checking for grade values.
                if(readyToCalculate && checkTotalWeightage(textFields) == 1){

                    for(int j = 0; j < 5; j++) {
                        if(textFields[0][j].getId().contains("assignment") && !textFields[0][j].getText().isEmpty()){
                            if(totalScore == -1){
                                totalScore = 0;
                            }
                            totalScore += Double.parseDouble(textFields[1][j].getText()) * Double.parseDouble(textFields[2][j].getText())/100.0;
                        }
                    }
                }else{
                    results.setText("Not ready to calculate. Please check inputs values.");
                }
                if( readyToCalculate && totalScore >= 0){
                    results.setText("Student's Grade is: "+getGrade(totalScore));
                }else if(readyToCalculate && totalScore == -1){
                    results.setText("Please complete details to calculate");
                }else{
                    results.setText("Please enter details to calculate");
                }
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    private String getGrade(double tScore){

        if(tScore > 95){ //96-100
            return "A";
        }else if(tScore >= 90){ // 90-95
            return "A-";
        }else if(tScore >= 85){ // 85-89
            return "B";
        }else if(tScore >= 80){ //80-84
            return "B-";
        }else if(tScore >= 75){ //75-79
            return "C";
        }else if(tScore >= 70){ //70-74
            return "C-";
        }else if(tScore >= 60){ //60-69
            return "D";
        }else{ // <60
            return "F";
        }
    }

    private int checkTotalWeightage(TextField[][] tFields){
        double totalWeightage = 0;
        for (int i = 0; i < 3; i++) { //columns
            for (int j = 0; j < 5; j++) { //rows
                if(tFields[i][j].getId().contains("assignment") && !tFields[i][j].getText().isEmpty() && !tFields[2][j].getText().isEmpty() ) {
                    totalWeightage += Double.parseDouble(tFields[2][j].getText());
                }
            }
        }
        if(totalWeightage < 100.0){
            return 2; // total weightage doesn't add up to 100.
        }else if(totalWeightage == 100.0){
            return 1; // total weightage is exactly 100 percent.
        }else{
            return 3; // total weightage exceeds 100
        }
    }
    private String getTextFieldPromptText(int i, int j) {

        switch(i){ //for each column 1, 2, 3
            case 0 : return "Assignment Type " + (j+1); // add a prompt text for Assignments. E.g.: Assignment 4
            case 1 : return "Grade " + (j+1); // add a prompt text for Grade. E.g.: Grade 4
            case 2 : return "Weightage " + (j+1); // add a prompt text for Weightage. E.g.: Weightage 4
            default : return "";
        }

    }
    private String getTextFieldIDString(int i, int j) {

        switch(i){
            case 0 : return "assignment" + (j+1); //setting ID for text field.. E.g.: assignment1, assignment2.
            case 1 : return "grade" + (j+1); //setting ID for text field.. E.g.: grade1, grade2.
            case 2 : return "weightage" + (j+1); // setting ID for text field. E.g.: weightage1, weightage2.
            default : return "";
        }

    }
    public static void main(String[] args) {
        launch();
    }
}
