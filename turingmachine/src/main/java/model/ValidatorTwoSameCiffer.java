package model;

/**
 * ValidatorTwoSameCiffer checks if any digit in the code appears exactly twice (but not three times).
 */
public class ValidatorTwoSameCiffer extends Validator {

    /**
     * Constructor for ValidatorTwoSameCiffer.
     *
     * @param secretCode The secret code to be checked against.
     * @param id         The identifier for the validator.
     */
    public ValidatorTwoSameCiffer(Code secretCode, int id) {
        super(secretCode, id);
    }

    /**
     * Tests if any digit in the code appears exactly twice (but not three times).
     *
     * @param guess The code guess to be tested.
     * @return True if any digit appears exactly twice in both the secret code and guess, false otherwise.
     */
    @Override
    public boolean test(Code guess) {
        int countRepetitionSecret = 0;
        int countRepetitionGuess = 0;

        for (int i = 0; i < 2; i++) {
            int currSecret = secretCode.getCifferAtIndex(i);
            int currGuess = guess.getCifferAtIndex(i);

            for (int j = i + 1; j < 3; j++) {
                if (countRepetitionSecret != 2) {
                    if (secretCode.getCifferAtIndex(j) == currSecret) {
                        countRepetitionSecret++;
                    }
                }
                if (countRepetitionGuess != 2) {
                    if (guess.getCifferAtIndex(j) == currGuess) {
                        countRepetitionGuess++;
                    }
                }
            }
        }

        return (countRepetitionSecret == 1 && countRepetitionGuess == 1) || (countRepetitionSecret != 1 && countRepetitionGuess != 1);
    }

    /**
     * Provides a description of the validator.
     *
     * @return A string describing the functionality of this validator.
     */
    @Override
    public String toString() {
        return "Determine if a digit in the code appears twice (but not three times)";
    }
}
