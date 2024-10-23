package model;

/**
 * The ValidatorMostParity class determines whether there are more even or odd numbers in a given code.
 * It extends the Validator abstract class and implements the test method to compare the count of even and odd numbers.
 */
public class ValidatorMostParity extends Validator {

    /**
     * Constructs a ValidatorMostParity object to check for the most common parity in a code.
     *
     * @param secretCode The secret code used by the validator to compare even and odd numbers.
     * @param id         The identifier used to retrieve the validator's image.
     */
    public ValidatorMostParity(Code secretCode,int id) {
        super(secretCode, id );
    }

    /**
     * Determines if there are more even or odd numbers in the guess compared to the secret code.
     *
     * @param guess The code to be tested for the count of even and odd numbers.
     * @return True if there are more even or more odd numbers in the guess compared to the secret code, false otherwise.
     */
    @Override
    public boolean test(Code guess) {
        int countEvenSecret = 0, countOddSecret = 0, countEvenGuess = 0, countOddGuess = 0;

        for (int i = 0; i < 3; i++) {
            int currSecret = secretCode.getCifferAtIndex(i);
            int currGuess = guess.getCifferAtIndex(i);

            if (currSecret % 2 == 0) {
                countEvenSecret++;
            } else {
                countOddSecret++;
            }
            if (currGuess % 2 == 0) {
                countEvenGuess++;
            } else {
                countOddGuess++;
            }
        }


        return ((countEvenSecret > countOddSecret) && (countEvenGuess > countOddGuess))
                || ((countOddSecret > countEvenSecret) && (countOddGuess > countEvenGuess));


    }

    /**
     * Returns a description of the validator's functionality.
     *
     * @return A string describing the validator's task of comparing even and odd numbers.
     */
    @Override
    public String toString() {
        return "Determines if there are more even numbers or odds in the code";
    }
}
