package controller.graphic;

import model.Code;
import model.TuringMachine;
import model.Validator;
import model.command.InputCodeCommand;
import model.command.NextTurnCommand;
import model.command.VerifyValidatorCommand;
import model.util.Observer;

import java.util.List;
/**
 * The ControllerFx class represents the controller in the graphical user interface,
 * facilitating interactions between the view and the Turing machine model.
 */
public class ControllerFx {
    private TuringMachine turingMachine;
    /**
     * Constructs a ControllerFx object with a specified TuringMachine.
     *
     * @param turingMachine The TuringMachine associated with this controller.
     */
    public ControllerFx(TuringMachine turingMachine) {
        this.turingMachine = turingMachine;
    }

    /**
     * Input the user's Code
     * @param guess what the user entered
     */
    public void inputCode(int guess) {
        InputCodeCommand command = new InputCodeCommand(turingMachine, guess);
        turingMachine.execute(command);
    }

    /**
     * Pass to the next turn
     */
    public void nextTurn() {
        NextTurnCommand command = new NextTurnCommand(turingMachine);
        turingMachine.execute(command);
    }

    /**
     * Used to submit the code
     * @return a bool indicating if the code is ok or not
     */
    public boolean playFinal() {
        return turingMachine.playCode();
    }

    /**
     * undo the previous action
     */
    public void undo() {
        turingMachine.undo();
    }

    /**
     * redo the undone action
     */
    public void redo() {
        turingMachine.redo();
    }

    /**
     * Used to verify the validator
     * @param index what is the validator to verify
     */
    public void verifyValidator(int index) {
        VerifyValidatorCommand command = new VerifyValidatorCommand(turingMachine, index);
        turingMachine.execute(command);
    }

    /**
     * Adds an observer to the TuringMachine's list of observers.
     *
     * @param observer The observer to be added.
     */
    public void addObserver(Observer observer) {
        turingMachine.addObserver(observer);
    }

    /**
     * Used to quit the game
     */
    public void quit() {
        turingMachine.quit();
    }

    /**
     * Getter for the validators of the game
     * @return the validators
     */
    public List<Validator> getValidators() {
        return turingMachine.getValidators();
    }

    /**
     * Getter for the guess that's the user put
     * @return the guess
     */
    public Code getGuess() {
        return turingMachine.getGuess();
    }

    /**
     * Getter for the current turn
     * @return the current turn
     */
    public int getTurn() {
        return turingMachine.getTurn();
    }

    /**
     * Getter for the current score
     * @return the current score
     */
    public int getScore() {
        return turingMachine.getScore();
    }


}
