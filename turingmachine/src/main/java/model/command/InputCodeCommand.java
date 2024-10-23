package model.command;

import model.Code;
import model.TuringMachine;

/**
 * Represents a command to input a code in the Turing Machine.
 */
public class InputCodeCommand implements Command {

    private TuringMachine turingMachine;
    private Code guess;

    /**
     * Constructor for the InputCodeCommand.
     *
     * @param turingMachine The Turing Machine to apply the command on
     * @param guess         The code to be inputted
     */
    public InputCodeCommand(TuringMachine turingMachine, int guess) {
        this.turingMachine = turingMachine;
        this.guess = new Code(guess);
    }

    /**
     * Executes the specified command.
     */
    @Override
    public void execute() {
        turingMachine.InputGuess(guess);
    }

    /**
     * Undoes the specified command, reverting the inputted code.
     */
    @Override
    public void undo() {
        turingMachine.InputGuess(null);
    }
}
