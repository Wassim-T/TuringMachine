package model;

/**
 * The ValidatorParity class verifies the parity of a specific position in a code.
 * It extends the Validator abstract class and implements the test method to compare the parity of a given index.
 */
public class ValidatorParity extends Validator {
    private int index;


    /**
     * Constructs a ValidatorParity object to check the parity of a specific position in a code.
     *
     * @param secretCode The secret code used by the validator to check parity.
     * @param index      The index representing the position in the code to verify the parity.
     * @param id         The identifier used to retrieve the validator's image.
     */
    public ValidatorParity(Code secretCode, int index,int id) {
        super(secretCode, id );
        this.index = index;
    }

    /**
     * Verifies if the parity of the guess at the specified index matches the parity of the secret code.
     *
     * @param guess The code to be tested for parity at the specified index.
     * @return True if the parity of the guess matches the parity of the secret code at the specified index, false otherwise.
     */
    @Override
    public boolean test(Code guess) {
        int secretAtIndex = secretCode.getCifferAtIndex(index);
        int guessAtIndex = guess.getCifferAtIndex(index);
        return (secretAtIndex % 2 == 0 && guessAtIndex % 2 == 0) || (secretAtIndex % 2 == 1 && guessAtIndex % 2 == 1);
    }

    /**
     * Returns a description of the validator's functionality.
     *
     * @return A string describing the validator's task of verifying the parity of a specific position in the code.
     */
    @Override
    public String toString() {
        return "Verify the parity of the position "+(index+1)+" of the code";
    }
}
