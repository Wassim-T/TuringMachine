package model;

/**
 * ValidatorSumParity is a Validator that checks if the sum of the digits in the code is even or odd.
 */
public class ValidatorSumParity extends Validator {

    /**
     * Constructor for ValidatorSumParity.
     *
     * @param secretCode The secret code to be checked against.
     * @param id         The identifier for the validator.
     */
    public ValidatorSumParity(Code secretCode, int id) {
        super(secretCode, id);
    }

    /**
     * Tests if the sum of the digits in the code and guess are both even or both odd.
     *
     * @param guess The code guess to be tested.
     * @return True if the sum of the digits in both the secret code and guess are both even or both odd, false otherwise.
     */
    @Override
    public boolean test(Code guess) {
        int sumSecret = secretCode.getCifferAtIndex(0) + secretCode.getCifferAtIndex(1) + secretCode.getCifferAtIndex(2);
        int sumGuess = guess.getCifferAtIndex(0) + guess.getCifferAtIndex(1) + guess.getCifferAtIndex(2);
        return (sumSecret % 2 == 0 && sumGuess % 2 == 0) || (sumSecret % 2 == 1 && sumGuess % 2 == 1);
    }

    /**
     * Provides a description of the validator.
     *
     * @return A string describing the functionality of this validator.
     */
    @Override
    public String toString() {
        return "Determine if the sum of the digits in the code is even or odd";
    }
}
