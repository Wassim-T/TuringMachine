package model;

/**
 * ValidatorRepetition class checks for the repetition of digits in a code.
 * It extends the Validator abstract class and implements the test method to compare repetitions between secret and guessed codes.
 */
public class ValidatorRepetition extends Validator{

    /**
     * Constructs a ValidatorRepetition object to check for digit repetitions in a code.
     *
     * @param secretCode The secret code used by the validator to check repetitions.
     * @param id         The identifier used to retrieve the validator's image.
     */
    public ValidatorRepetition(Code secretCode,int id) {
        super(secretCode, id );
    }

    /**
     * Checks if there are repetitions of digits between the secret and guessed codes.
     *
     * @param guess The code to be tested for digit repetitions.
     * @return True if the count of digit repetitions matches between the secret and guessed codes, false otherwise.
     */
    @Override
    public boolean test(Code guess) {
        int countRepetitionSecret = 0;
        int countRepetitionGuess = 0;

       for (int i = 0; i < 2; i++){
           int currSecret = secretCode.getCifferAtIndex(i);
           int currGuess = guess.getCifferAtIndex(i);

           for (int j = i+1; j<3; j++){
               if (secretCode.getCifferAtIndex(j) == currSecret) {
                   countRepetitionSecret++;
               }
               if (guess.getCifferAtIndex(j) == currGuess){
                   countRepetitionGuess++;
               }
           }
           if (countRepetitionSecret >= 1){
               break;
           }

       }

       return countRepetitionSecret == countRepetitionGuess;

    }

    /**
     * Returns a description of the validator's functionality.
     *
     * @return A string describing the validator's task of checking for digit repetitions in a code.
     */
    @Override
    public String toString() {
        return "Determine if a ciffer of the code repeat, if yes, how much time";
    }
}
