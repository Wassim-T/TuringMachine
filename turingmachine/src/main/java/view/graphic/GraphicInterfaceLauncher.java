package view.graphic;

import controller.graphic.ControllerFx;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.*;
import model.command.NextTurnCommand;
import model.util.Loader;
import model.util.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the launcher for the graphic interface of the Turing Machine game.
 * This class initializes and manages the main graphical interface components.
 */
public class GraphicInterfaceLauncher implements Observer {


    List<Problem> problems;


    private TextField scoreFld;
    private TextField turnFld;
    private ControllerFx controllerFx;

    /**
     * Constructs a GraphicInterfaceLauncher for the provided Stage.
     *
     * @param primaryStage The primary stage for the graphical interface.
     */
    public GraphicInterfaceLauncher(Stage primaryStage) {
        StackPane root = new StackPane();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(20));


        Loader loader = Loader.getInstance();

        this.problems = loader.getProblems();
        ChoiceBox<String> level = new ChoiceBox<>();

        problems.forEach(e -> {
            level.getItems().add("Level " + e.getLevel() + " / Difficulty " + displayDifficulty(e.getDifficulty()) + " /luck " + displayLuck(e.getLuck()));
        });


        Button btnStart = new Button("START");
        Button btnRandom = new Button("Random Level");

        GridPane.setHalignment(btnStart, HPos.CENTER);
        GridPane.setHalignment(btnRandom, HPos.CENTER);
        gridPane.setVgap(10);
        gridPane.add(level, 0, 0);
        gridPane.add(btnStart, 0, 1);
        gridPane.add(btnRandom, 0, 2);

        Image image = new Image("file:src/Resources/background1.jpg");
        ImageView imageView = new ImageView(image);


        imageView.fitWidthProperty().bind(primaryStage.widthProperty());   //pour remplir tout l'écran
        imageView.fitHeightProperty().bind(primaryStage.heightProperty());


        root.getChildren().add(imageView);
        root.getChildren().add(gridPane);

        Scene scene = new Scene(root);


        primaryStage.setScene(scene);
        primaryStage.setTitle("Choose Level");


        primaryStage.show();


        btnStart.setOnAction(e -> {
            displayGame(retrieveLevel(level.getValue()));
            primaryStage.close();
        });


        btnRandom.setOnAction(e -> {
            Random random = new Random();
            displayGame(random.nextInt(problems.size()));
            primaryStage.close();
        });


    }

    /**
     * the second stage of the game
     * @param level the level to start the game with
     */
    private void displayGame(int level) {
        this.controllerFx = new ControllerFx(new TuringMachine(problems.get(level)));

        controllerFx.addObserver(this);
        Stage gameStage = new Stage();

        gameStage.setMaximized(true);


        StackPane root = new StackPane();

        HBox hBoxValidators = new HBox();
        VBox vBoxGame = new VBox();


        Menu menu = new Menu("File");
        MenuBar menuBar = new MenuBar(menu);
        MenuItem exit = new MenuItem("exit");      // My menuBar
        menu.getItems().add(exit);


        hBoxValidators.setMaxHeight(gameStage.getHeight()*5);









        ViewInputGuess viewInputGuess = new ViewInputGuess(controllerFx, gameStage);

        HBox hBoxButtons = new HBox();

        hBoxButtons.getChildren().add(viewInputGuess);           // Add the buttons for choosing a code


        Label scoreLbl = new Label("Score");
        Label TurnLbl = new Label("Turn");
        scoreLbl.setPadding(new Insets(10, 5, 5, 10));
        TurnLbl.setPadding(new Insets(10, 5, 5, 10));

        VBox vBoxScoreTurnLbl = new VBox(scoreLbl, TurnLbl);


        hBoxButtons.getChildren().add(vBoxScoreTurnLbl);


        this.scoreFld = new TextField("0");
        scoreFld.setEditable(false);
        this.turnFld = new TextField("1");
        turnFld.setEditable(false);
        scoreFld.setPadding(new Insets(10, 5, 5, 10));
        turnFld.setPadding(new Insets(10, 5, 5, 10));


        VBox vBoxScoreTurnFld = new VBox(scoreFld, turnFld);                              //Add the fields with the score and turn

        hBoxButtons.getChildren().add(vBoxScoreTurnFld);


        Button submitBtn = new Button();

        Image submitImg = new Image("file:src/resources/submit.jpg");              //Submit button creation
        ImageView submitView = new ImageView(submitImg);
        submitBtn.setGraphic(submitView);
        submitView.setFitHeight(150);
        submitView.setFitWidth(150);

        VBox vBoxSubmit = new VBox();


        Label submitLbl = new Label("Submit Code");
        submitLbl.setFont(new Font("Arial", 25));


        Button undoBtn = new Button("Undo");
        undoBtn.setPrefWidth(100);                              //Undo redo buttons

        Button redoBtn = new Button("Redo");
        undoBtn.setPrefWidth(50);


        VBox vBoxPassTurn = new VBox();
        Button passTurnBtn = new Button("Pass Turn");
        passTurnBtn.setPrefWidth(100);
        vBoxPassTurn.setAlignment(Pos.CENTER);
        Button newStage = new Button("view score");

        vBoxPassTurn.getChildren().addAll(passTurnBtn,newStage);         //Add pass turn button


        vBoxSubmit.setAlignment(Pos.CENTER);

        vBoxSubmit.getChildren().addAll(submitLbl, submitBtn);  //Add submit button

        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        // I use this for placing the submit button near the center
        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);




        hBoxButtons.getChildren().addAll(region1, vBoxSubmit, region2, vBoxPassTurn);


        HBox hBoxUndoRedo = new HBox();
        hBoxUndoRedo.setAlignment(Pos.BOTTOM_RIGHT);

        hBoxUndoRedo.getChildren().addAll(undoBtn, redoBtn);



        root.getChildren().addAll(vBoxGame);


        Scene scene = new Scene(root);
        gameStage.setTitle("TURING MACHINE");
        gameStage.setScene(scene);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        ScrollPane scrollPane = new ScrollPane(vBoxGame);
        scrollPane.setFitToWidth(true);
        root.getChildren().add(scrollPane);

        hBoxValidators.getChildren().addAll(createViewValidators(controllerFx, level, primaryScreenBounds.getWidth()/controllerFx.getValidators().size()-10, gameStage));
        System.out.println(scene.getWindow().getWidth());
        vBoxGame.getChildren().addAll(menuBar,hBoxValidators,hBoxButtons, hBoxUndoRedo);//Add everything
        gameStage.show();




        submitBtn.setOnAction(e -> {
            try {
                if (controllerFx.playFinal()) {
                    showAlert("You won! Score : " +scoreFld.getText(), gameStage);
                } else {
                    showAlert("You lost!", gameStage);
                }
            } catch (TuringMachineException turingMachineException) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input error");
                alert.setHeaderText(null);
                alert.initOwner(gameStage);
                alert.setContentText(turingMachineException.getMessage());
                alert.showAndWait();
            }

        });


        passTurnBtn.setOnAction(e -> {
            controllerFx.nextTurn();
        });


        undoBtn.setOnAction(e -> {
            controllerFx.undo();
        });

        redoBtn.setOnAction(e -> {
            controllerFx.redo();
        });

        exit.setOnAction(e -> {
            controllerFx.quit();
        });

        newStage.setOnAction(e ->{
            DisplayTest(scoreFld.getText());
            gameStage.close();
        });


    }


    public void DisplayTest(String score){
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);


        TextField field = new TextField();
        field.setEditable(false);

        field.appendText(score);
        field.setAlignment(Pos.CENTER);
        root.getChildren().add(field);




        stage.show();




    }


    @Override
    public void update(Notification notification, int indexValidator) {
        if (notification == Notification._NEW_SCORE) {
            scoreFld.clear();
            scoreFld.setText(Integer.toString(controllerFx.getScore()));
        } else if (notification == Notification._NEW_TURN) {

            turnFld.clear();
            turnFld.setText(Integer.toString(controllerFx.getTurn()));
        }
    }

    /**
     * Displays an alert with the given message and associates it with the provided stage.
     * This alert is used to show game results and performs an action when OK is pressed.
     *
     * @param message The message to be displayed in the alert.
     * @param stage   The stage used to show the alert.
     */
    private void showAlert(String message, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Result");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(stage);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                controllerFx.quit();
            }
        });
    }

    /**
     * Retrieves the level from the choice string (parsed from a ChoiceBox selection).
     * It extracts the level information and returns the corresponding index in the problems list.
     *
     * @param choice The choice string containing level information.
     * @return The index of the level selected in the problems list.
     */
    private int retrieveLevel(String choice) {
        String[] split = choice.split(" ");
        return Integer.parseInt(split[1]) - 1;   //-1 to get the index of the problem in the list
    }

    /**
     * Displays the difficulty level as a string based on the provided integer.
     *
     * @param difficulty The integer representing the difficulty level.
     * @return A string representation of the difficulty level.
     * @throws TuringMachineException If an invalid difficulty value is encountered.
     */
    private String displayDifficulty(int difficulty) {
        switch (difficulty) {
            case 1:
                return "easy";

            case 2:
                return "medium";

            case 3:
                return "hard";

            default:
                throw new TuringMachineException("difficulty problem");
        }
    }

    /**
     * Displays the luck level as a string based on the provided integer.
     *
     * @param luck The integer representing the luck level.
     * @return A string representation of the luck level using star symbols.
     * @throws TuringMachineException If an invalid luck value is encountered.
     */
    private String displayLuck(int luck) {
        switch (luck) {
            case 1:
                return "⭐";

            case 2:
                return "⭐⭐";

            case 3:
                return "⭐⭐⭐";

            default:
                throw new TuringMachineException("luck problem");
        }
    }

    /**
     * Creates a list of ViewValidator instances based on the provided ControllerFx,
     * level index, preferred width, and stage for display.
     *
     * @param controller The ControllerFx instance providing the validators.
     * @param level      The index of the level in the problems list.
     * @param prefWidth  The preferred width for the ViewValidators.
     * @param stage      The stage for display.
     * @return A list of ViewValidator instances based on the provided parameters.
     */
    private List<ViewValidator> createViewValidators(ControllerFx controller, int level, double prefWidth, Stage stage) {
        List<ViewValidator> viewValidators = new ArrayList<>();
        List<Validator> validators = controller.getValidators();

        for (int i = 0; i < validators.size(); i++) {
            ViewValidator viewValidator = new ViewValidator(controller, i, validators.get(i).getValidatorId(),stage,prefWidth);
            viewValidators.add(viewValidator);
        }

        return viewValidators;
    }


}


