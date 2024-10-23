package model;
/**
 * The ValidatorComparatorSumTwoCifferValue class represents a validator for comparing the sum of two ciphers in a code with a specified value.
 * It extends the Validator abstract class and implements the test method for sum comparison.
 */
public class ValidatorComparatorSumTwoCifferValue extends Validator{

    /**
     * Constructs a ValidatorComparatorSumTwoCifferValue object with the given secret code and identifier.
     *
     * @param secretCode The secret code used by the validator to compare against guesses.
     * @param id         The identifier used to retrieve the validator's image.
     */
    public ValidatorComparatorSumTwoCifferValue(Code secretCode,int id) {
        super(secretCode, id);
    }

    /**
     * Compares the sum of the first two ciphers in the guess code with a specified value.
     *
     * @param guess The code to be tested for sum comparison.
     * @return True if the sum of the first two ciphers in the guess code matches the comparison conditions, false otherwise.
     */
    @Override
    public boolean test(Code guess) {
        int sumSecret = secretCode.getCifferAtIndex(0) + secretCode.getCifferAtIndex(1);
        int sumGuess = guess.getCifferAtIndex(0) + guess.getCifferAtIndex(1);

        if (sumSecret < 6 && sumGuess < 6){
            return true;
        } else if (sumSecret == 6 && sumGuess == 6) {
            return true;
        } else if (sumSecret > 6 && sumGuess > 6) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a description of the validator's functionality.
     *
     * @return A string describing the purpose of the validator.
     */
    @Override
    public String toString() {
        return "Compare the sum of the first and second ciffer of the code with 6";
    }
}
