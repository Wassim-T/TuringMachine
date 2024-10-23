package model;

/**
 * The ValidatorCountEven class represents a validator for counting the number of even ciphers in a code.
 * It extends the Validator abstract class and implements the test method for counting even ciphers.
 */
public class ValidatorCountEven extends Validator {

    /**
     * Constructs a ValidatorCountEven object with the given secret code and identifier.
     *
     * @param secretCode The secret code used by the validator to count even ciphers in guesses.
     * @param id         The identifier used to retrieve the validator's image.
     */
    public ValidatorCountEven(Code secretCode,int id){
        super(secretCode,id );
    }


    /**
     * Counts the number of even ciphers in the guess code and compares it with the count in the secret code.
     *
     * @param guess The code to be tested for counting even ciphers.
     * @return True if the count of even ciphers in the guess matches the count in the secret code, false otherwise.
     */
    @Override
    public boolean test(Code guess) {
        int countEvenSecret = 0;
        int countEvenGuess = 0;

        for (int i = 0; i<3; i++){
            int currSecret = secretCode.getCifferAtIndex(i);
            int currGuess = guess.getCifferAtIndex(i);
            if (currSecret % 2 == 0){
                countEvenSecret++;
            }
            if (currGuess % 2 == 0){
                countEvenGuess++;
            }
        }
        return countEvenSecret == countEvenGuess;
    }

    /**
     * Returns a description of the validator's functionality.
     *
     * @return A string describing the purpose of the validator.
     */
    @Override
    public String toString() {
        return "Count how much even ciffers are in the code";
    }
}
