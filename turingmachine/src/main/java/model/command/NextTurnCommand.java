package model.command;

import model.Code;
import model.Notification;
import model.TuringMachine;
import model.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to progress to the next turn in the Turing Machine game.
 */
public class NextTurnCommand implements Command {

    private TuringMachine turingMachine;

    private int previousTurn;
    private Code previousGuess;
    private List<Validator> previousTempValidators;
    private List<Validator> previousValidators;

    /**
     * Constructor for the NextTurnCommand.
     *
     * @param turingMachine The Turing Machine to apply the command on
     */
    public NextTurnCommand(TuringMachine turingMachine) {
        this.turingMachine = turingMachine;
        this.previousTurn = turingMachine.getTurn();
        this.previousGuess = turingMachine.getGuess();
    }

    /**
     * Copies the list of validators to create a new instance.
     *
     * @param originalValidators The original list of validators
     * @return A new list containing copies of the original validators
     */
    private List<Validator> copyValidatorsList(List<Validator> originalValidators) {
        List<Validator> copiedValidators = new ArrayList<>();

        originalValidators.forEach(e -> copiedValidators.add((Validator) e.clone()));

        return copiedValidators;
    }

    /**
     * Executes the specified command by progressing to the next turn.
     */
    @Override
    public void execute() {
        this.previousTempValidators = copyValidatorsList(turingMachine.getTempvalidators());
        this.previousValidators = copyValidatorsList(turingMachine.getValidators());
        turingMachine.nextTurn();
    }

    /**
     * Undoes the specified command, reverting to the previous turn state.
     */
    @Override
    public void undo() {
        turingMachine.setTurn(-1);
        turingMachine.InputGuess(previousGuess);
        turingMachine.setValidators(previousValidators, previousTempValidators);
        int[] indexesToNotify = new int[previousValidators.size()];
        for (int i = 0; i < indexesToNotify.length; i++) {
            indexesToNotify[i] = i;
        }
        turingMachine.notifyObservers(Notification._NEW_STATE, indexesToNotify);
    }
}
