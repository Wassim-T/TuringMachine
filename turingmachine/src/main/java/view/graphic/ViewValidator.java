package view.graphic;

import controller.graphic.ControllerFx;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.*;
import model.command.VerifyValidatorCommand;
import model.util.Observer;


/**
 * ViewValidator represents the graphical interface for a validator in the Turing Machine game.
 * It displays the validator's information, including the robot image, card image, and a verify button.
 * This class extends the GridPane and implements the Observer interface.
 */

public class ViewValidator extends GridPane implements Observer {

    private ControllerFx controllerFx;
    private int index;

    /**
     * Constructor for ViewValidator, initializes the view for displaying a validator's information.
     *
     * @param controllerFx The ControllerFx object associated with this view.
     * @param index        The index of the validator.
     * @param validatorId  The ID of the validator.
     * @param stage        The stage for displaying alerts.
     */
    public ViewValidator(ControllerFx controllerFx, int index, int validatorId, Stage stage,double width){
        this.controllerFx = controllerFx;
        this.controllerFx.addObserver(this);
        this.setStyle("-fx-border-color: grey; -fx-border-width: 5px;");
        this.index = index;
        Label chooseValidator = new Label("Validator "+(index +1));
        GridPane.setHalignment(chooseValidator, HPos.CENTER);
        Image image = getRobotImage(index);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        GridPane.setHalignment(imageView, HPos.CENTER);
        Image validatorImage = getCardImage(validatorId);
        ImageView validatorImageView = new ImageView(validatorImage);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        validatorImageView.setFitHeight(primaryScreenBounds.getHeight()/7);
        validatorImageView.setFitWidth(width);
        GridPane.setHalignment(validatorImageView,HPos.CENTER);
        Button btnValidator = new Button("Verify");
        GridPane.setHalignment(chooseValidator, HPos.CENTER);


        btnValidator.setOnAction(e ->{
            try {
                this.controllerFx.verifyValidator(index);
            } catch (TuringMachineException turingMachineException){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input error");
                alert.setHeaderText(null);
                alert.initOwner(stage);
                alert.setContentText(turingMachineException.getMessage());
                alert.showAndWait();
            }

        });

        this.add(chooseValidator,0,0);
        this.add(imageView,0,1);
        this.add(validatorImageView,0,2);
        this.add(btnValidator,0,3);
        this.setVgap(10);
        this.setAlignment(Pos.TOP_CENTER);
    }


    /**
     * This method is called whenever the observed object has changed.
     *
     * @param notification
     * @param indexValidator
     */
    @Override
    public void update(Notification notification, int indexValidator) {
        if (notification == Notification._NEW_STATE){
            if (indexValidator == index){
                if (controllerFx.getValidators().get(indexValidator).getState() == ValidatorState._TRUE){
                    this.setStyle("-fx-border-color: grey; -fx-border-width: 5px; -fx-background-color: rgb(144, 238, 144);");
                } else if (controllerFx.getValidators().get(indexValidator).getState() == ValidatorState._FALSE) {
                    this.setStyle("-fx-border-color: grey; -fx-border-width: 5px; -fx-background-color: rgb(250, 30, 0);");
                } else if (controllerFx.getValidators().get(indexValidator).getState() == ValidatorState._UNSELECTED){
                    this.setStyle("-fx-border-color: grey; -fx-border-width: 5px; -fx-background-color: white;");
                }
            }

        }

    }


    /**
     * Retrieves the robot image based on the index of the validator.
     *
     * @param indexValidator The index of the validator to retrieve the robot image.
     * @return The image representing the robot associated with the validator.
     * @throws TuringMachineException If there's a problem selecting the robot image.
     */
    private Image getRobotImage(int indexValidator){
        Image image;
        switch (indexValidator) {
            case 0:
                image = new Image("file:src\\resources\\robotA.png");
                return image;
            case 1:
                image = new Image("file:src\\resources\\robotB.png");
                return image;
            case 2:
                image = new Image("file:src\\resources\\robotC.png");
                return image;
            case 3:
                image = new Image("file:src\\resources\\robotD.png");
                return image;
            case 4:
                image = new Image("file:src\\resources\\robotE.png");
                return image;
            case 5:
                image = new Image("file:src\\resources\\robotF.png");
                return image;
            default:
                throw new TuringMachineException("Problem selecting robot image");
        }

    }



    /**
     * Retrieves the card image based on the validator ID.
     *
     * @param validatorId The ID of the validator to retrieve the card image.
     * @return The image representing the card associated with the validator.
     */
    private Image getCardImage(int validatorId){

        Image image = new Image("file:src\\resources\\card"+validatorId+".png");

        return image;

    }



}