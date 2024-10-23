package view.graphic;

import controller.graphic.ControllerFx;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Notification;
import model.TuringMachine;
import model.TuringMachineException;
import model.command.InputCodeCommand;
import model.util.Observer;


/**
 * ViewInputGuess represents the graphical interface for entering guesses in the Turing Machine game.
 * It consists of a grid with input buttons and text fields for entering the guesses.
 * This class extends the GridPane and implements the Observer interface.
 */
public class ViewInputGuess extends GridPane implements Observer {


    private ControllerFx controller;

    private TextField textFieldInput;

    private ToggleGroup group1;
    private ToggleGroup group2;
    private ToggleGroup group3;

    private Boolean isChangeable;


    /**
     * Constructor for ViewInputGuess, initializes the view for entering guesses.
     *
     * @param controller The ControllerFx object associated with this view.
     * @param stage      The stage for displaying alerts.
     */
    public ViewInputGuess(ControllerFx controller, Stage stage) {

        this.controller = controller;
        this.controller.addObserver(this);
        this.isChangeable = true;

        Image triangle = new Image("file:src/resources/triangle.jpg");
        ImageView imageViewTriangle = new ImageView(triangle);
        Image square = new Image("file:src/resources/square.png");
        ImageView imageViewSquare = new ImageView(square);
        Image circle = new Image("file:src/resources/circle.jpg");
        ImageView imageViewCircle = new ImageView(circle);
        imageViewTriangle.setFitWidth(50);
        imageViewTriangle.setFitHeight(50);
        imageViewSquare.setFitWidth(50);
        imageViewSquare.setFitHeight(50);
        imageViewCircle.setFitWidth(50);
        imageViewCircle.setFitHeight(50);
        this.textFieldInput = new TextField();
        textFieldInput.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold; -fx-font-size: 50px;");
        textFieldInput.setEditable(false);
        textFieldInput.setPrefWidth(300);
        textFieldInput.setPrefHeight(100);
        Label labelInput = new Label("Your guess");
        labelInput.setAlignment(Pos.BOTTOM_CENTER);
        labelInput.setFont(new Font("Arial", 25));


        VBox vBoxNumbers = new VBox();
        HBox hBoxNumbersTriangle = new HBox();
        HBox hBoxNumbersSquare = new HBox();
        HBox hBoxNumbersCircle = new HBox();
        HBox hBoxTextField = new HBox();


        this.setAlignment(Pos.BASELINE_LEFT);
        this.setPadding(new Insets(10, 0, 10, 10)); //TODO Insets(top, right, bottom, left)
        this.setVgap(20);

        hBoxNumbersTriangle.getChildren().add(imageViewTriangle);
        hBoxNumbersSquare.getChildren().add(imageViewSquare);
        hBoxNumbersCircle.getChildren().add(imageViewCircle);


        Button buttonInputCode = new Button("Input Code");


        this.add(labelInput, 0, 3);
        this.add(buttonInputCode, 1, 3);
        this.add(textFieldInput, 0, 4);

        this.group1 = new ToggleGroup();
        this.group2 = new ToggleGroup();
        this.group3 = new ToggleGroup();


        // for group 1
        for (int i = 1; i <= 5; i++) {
            ToggleButton button = new ToggleButton(String.valueOf(i));
            button.setToggleGroup(group1);
            button.setPrefWidth(50);
            button.setPrefHeight(50);

            button.setOnAction(e -> {
                if (isChangeable) {
                    if (!textFieldInput.getText().isEmpty()) {
                        // Clear the character at position 0 if there's existing text
                        textFieldInput.replaceText(0, 1, "");
                    }

                    // Insert the text from the button at position 0
                    textFieldInput.insertText(0, button.getText());
                }


            });
            hBoxNumbersTriangle.getChildren().add(button);

        }
        hBoxNumbersTriangle.setSpacing(10);


        // for group 2
        for (int i = 1; i <= 5; i++) {
            ToggleButton button = new ToggleButton(String.valueOf(i));
            button.setToggleGroup(group2);
            button.setPrefWidth(50);
            button.setPrefHeight(50);

            button.setOnAction(e -> {
                if (isChangeable) {
                    if (textFieldInput.getText().length() > 1) {
                        textFieldInput.replaceText(1, 2, "");
                    }

                    // Insertion du texte du bouton à la position 1
                    textFieldInput.insertText(1, button.getText());
                }
            });
            hBoxNumbersSquare.getChildren().add(button);
        }
        hBoxNumbersSquare.setSpacing(10);


        // for group 3
        for (int i = 1; i <= 5; i++) {
            ToggleButton button = new ToggleButton(String.valueOf(i));
            button.setToggleGroup(group3);
            button.setPrefWidth(50);
            button.setPrefHeight(50);

            button.setOnAction(e -> {
                if (isChangeable) {
                    if (textFieldInput.getText().length() > 2) {
                        textFieldInput.replaceText(2, 3, "");
                    }

                    // Insertion du texte du bouton à la position 1
                    textFieldInput.insertText(2, button.getText());
                }
            });
            hBoxNumbersCircle.getChildren().add(button);
        }
        hBoxNumbersCircle.setSpacing(10);

        vBoxNumbers.getChildren().addAll(hBoxNumbersTriangle, hBoxNumbersSquare, hBoxNumbersCircle);
        vBoxNumbers.setSpacing(20);
        this.add(vBoxNumbers, 0, 0);


        buttonInputCode.setOnAction(e -> {
            try {
                controller.inputCode(getTextFieldCode());
                isChangeable = false;
            } catch (NumberFormatException numberFormatException){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input error");
                alert.setHeaderText(null);
                alert.initOwner(stage);
                alert.setContentText("No Code entered !");
                alert.showAndWait();
            } catch (TuringMachineException turingMachineException){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input error");
                alert.setHeaderText(null);
                alert.initOwner(stage);
                alert.setContentText(turingMachineException.getMessage());
                alert.showAndWait();
            }


        });

    }

    /**
     * Gets the integer value from the textFieldInput field representing the code input.
     *
     * @return The integer value entered in the text field.
     */
    private int getTextFieldCode() {

            return Integer.parseInt(this.textFieldInput.getText());


    }


    @Override
    public void update(Notification notification, int indexValidator) {
        if (notification == Notification._NEW_GUESS) {
            String newText;
            if (controller.getGuess() == null){
                newText ="";
            } else{
                newText = Integer.toString(controller.getGuess().getSecretCodeValue());
            }


            textFieldInput.setText(newText);
            isChangeable = true;
            group1.selectToggle(null);
            group2.selectToggle(null);
            group3.selectToggle(null);
        }
    }
}
