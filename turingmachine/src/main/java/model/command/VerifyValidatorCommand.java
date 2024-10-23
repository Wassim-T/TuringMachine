package model.command;

import model.Notification;
import model.TuringMachine;
import model.Validator;
import model.ValidatorState;

/**
 * Command class responsible for verifying a validator within a Turing Machine.
 * Executes the verification process and allows undoing the verification operation.
 */
public class VerifyValidatorCommand implements Command {

    private TuringMachine turingMachine;
    private int indexValidator;
    private Validator verifiedValidator;

    /**
     * Constructs a VerifyValidatorCommand to verify a specific validator within the Turing Machine.
     *
     * @param turingMachine  The Turing Machine instance containing validators to be verified
     * @param indexValidator The index of the validator to be verified within the Turing Machine
     */
    public VerifyValidatorCommand(TuringMachine turingMachine, int indexValidator) {
        this.turingMachine = turingMachine;
        this.indexValidator = indexValidator;
    }

    /**
     * Executes the verification process for the specified validator within the Turing Machine.
     * Retrieves the verified validator from the Turing Machine's validators collection.
     */
    @Override
    public void execute() {
        turingMachine.checkValidator(indexValidator);
        this.verifiedValidator = turingMachine.getValidators().get(indexValidator);
    }

    /**
     * Undoes the verification process for the validator within the Turing Machine.
     * Resets the score, removes the validator from temporary validators,
     * resets the validator's state to UNSELECTED, and notifies observers about the state change.
     */
    @Override
    public void undo() {
        turingMachine.setScore(-1);
        turingMachine.getTempvalidators().remove(verifiedValidator);
        turingMachine.getValidators().get(indexValidator).setState(ValidatorState._UNSELECTED);
        turingMachine.notifyObservers(Notification._NEW_STATE, indexValidator);
    }
}
