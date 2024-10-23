package model;

/**
 * The ValidatorComparatorTwoCifferValue class represents a validator for comparing the values of two specific ciphers in a code.
 * It extends the Validator abstract class and implements the test method for value comparison.
 */
public class ValidatorComparatorTwoCifferValue extends Validator{

    private int firstIndex;
    private int secondIndex;

    /**
     * Constructs a ValidatorComparatorTwoCifferValue object with the given secret code, indices, and identifier.
     *
     * @param secretCode  The secret code used by the validator to compare against guesses.
     * @param firstIndex  The index of the first cipher in the code for comparison.
     * @param secondIndex The index of the second cipher in the code for comparison.
     * @param id          The identifier used to retrieve the validator's image.
     */
    public ValidatorComparatorTwoCifferValue(Code secretCode, int firstIndex, int secondIndex,int id){
        super(secretCode, id );
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;
    }

    /**
     * Compares the values of two specific ciphers in the guess code with the corresponding ciphers in the secret code.
     *
     * @param guess The code to be tested for value comparison.
     * @return True if the comparison conditions between the ciphers are met, false otherwise.
     */
    @Override
    public boolean test(Code guess) {
        int secretCodeAtFirstIndex = secretCode.getCifferAtIndex(firstIndex);
        int secretCodeAtSecondIndex = secretCode.getCifferAtIndex(secondIndex);
        int guessAtFirstIndex = guess.getCifferAtIndex(firstIndex);
        int guessAtSecondIndex = guess.getCifferAtIndex(secondIndex);

        if (secretCodeAtFirstIndex < secretCodeAtSecondIndex && guessAtFirstIndex < guessAtSecondIndex){
            return true;
        } else if (secretCodeAtFirstIndex > secretCodeAtSecondIndex && guessAtFirstIndex > guessAtSecondIndex) {
            return true;
        } else if (secretCodeAtFirstIndex == secretCodeAtSecondIndex && guessAtFirstIndex == guessAtSecondIndex) {
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
        return "Compare the position "+(firstIndex+1)+" of the code with the position "+(secondIndex+1);
    }
}
