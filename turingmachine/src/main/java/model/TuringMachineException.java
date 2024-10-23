package model;

/**
 * The TuringMachineException class represents an exception specific to the Turing Machine game.
 * It extends the RuntimeException class to denote runtime exceptions that may occur during the game.
 */
public class TuringMachineException extends RuntimeException {

    /**
     * Constructs a TuringMachineException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public TuringMachineException(String message) {
        super(message);
    }
}
