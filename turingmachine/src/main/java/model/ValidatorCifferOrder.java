package model;
/**
 * The ValidatorCifferOrder class represents a validator for checking whether the ciphers in the code
 * follow a specific order (ascending or descending).
 * It extends the Validator abstract class and implements the test method for checking the order of ciphers.
 */
public class ValidatorCifferOrder extends Validator {

    /**
     * Constructs a ValidatorCifferOrder object with the given secret code and identifier.
     *
     * @param secretCode The secret code used by the validator to compare against guesses.
     * @param id         The identifier used to retrieve the validator's image.
     */
    public ValidatorCifferOrder(Code secretCode,int id) {
        super(secretCode, id);
    }

    /**
     * Checks if the given guess code's ciphers are in ascending or descending order as per the secret code.
     *
     * @param guess The code to be tested against the order conditions.
     * @return True if the guess's ciphers follow the order (ascending or descending) of the secret code's ciphers, false otherwise.
     */
    @Override
    public boolean test(Code guess) {
        boolean estCroissantSecret = true;
        boolean estDecroissantSecret = true;
        boolean estCroissantGuess = true;
        boolean estDecroissantGuess = true;

        for (int i = 0; i < 2; i++) {
            int currSecret = secretCode.getCifferAtIndex(i);
            int nextSecret = secretCode.getCifferAtIndex(i + 1);
            int currGuess = guess.getCifferAtIndex(i);
            int nextGuess = guess.getCifferAtIndex(i + 1);


            if (currSecret > nextSecret) {
                estCroissantSecret = false;
            } else if (currSecret < nextSecret) {
                estDecroissantSecret = false;
            }

            if (currGuess > nextGuess) {
                estCroissantGuess = false;
            } else if (currGuess < nextGuess) {
                estDecroissantGuess = false;
            }

        }

        if (estCroissantSecret && estCroissantGuess){
            return true;
        } else if (estDecroissantSecret && estDecroissantGuess) {
            return true;
        } else if ((!estCroissantSecret && !estCroissantGuess) && (!estDecroissantSecret && !estDecroissantGuess)) {
            return true;
        } else{
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
        return "Determine if the 3 ciffers of the code are ascending or descending";
    }
}
