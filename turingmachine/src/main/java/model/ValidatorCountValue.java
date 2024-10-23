package model;

/**
 * The ValidatorCountValue class represents a validator that counts the occurrence of a specific value in a code.
 * It extends the Validator abstract class and implements the test method for counting occurrences of a value.
 */
public class ValidatorCountValue extends Validator {
    private int value;

    /**
     * Constructs a ValidatorCountValue object with the given secret code, value to count, and identifier.
     *
     * @param secretCode The secret code used by the validator to count occurrences of the value.
     * @param value      The value to be counted in the codes.
     * @param id         The identifier used to retrieve the validator's image.
     */
    public ValidatorCountValue(Code secretCode,int value,int id) {
        super(secretCode, id );
        this.value = value;
    }

    /**
     * Counts the number of occurrences of a specific value in the guess code and compares it with the count in the secret code.
     *
     * @param guess The code to be tested for counting occurrences of the specified value.
     * @return True if the count of the specified value in the guess matches the count in the secret code, false otherwise.
     */
    @Override
    public boolean test(Code guess) {
         int counterSecret = 0;
         int counterGuess = 0;

         for (int i = 0 ; i< 3;i++){
             if (secretCode.getCifferAtIndex(i) == value){
                 counterSecret++;
             }
             if(guess.getCifferAtIndex(i) == value){
                 counterGuess++;
             }
         }
         return counterGuess == counterSecret;
    }

    /**
     * Returns a description of the validator's functionality.
     *
     * @return A string describing the purpose of the validator.
     */
    @Override
    public String toString() {
        return "count how much time does the value "+value+" appears in the code";
    }
}
