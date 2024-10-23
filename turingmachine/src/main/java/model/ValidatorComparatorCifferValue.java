package model;

/**
 * The ValidatorComparatorCifferValue class represents a validator for comparing a specific cipher's value at an index position in the code with a given value.
 * It extends the Validator abstract class and implements the test method for comparing cipher values.
 */
public class ValidatorComparatorCifferValue extends Validator {
    private int index;
    private int value;

    /**
     * Constructs a ValidatorComparatorCifferValue object with the given secret code, index, value, and identifier.
     *
     * @param secretCode The secret code used by the validator to compare against guesses.
     * @param index      The index position in the code to compare against the value.
     * @param value      The value used for comparison.
     * @param id         The identifier used to retrieve the validator's image.
     */
    public ValidatorComparatorCifferValue(Code secretCode, int index, int value,int id) {
        super(secretCode, id);
        this.index = index;
        this.value = value;

    }

    /**
     * Compares the value at the specified index position in the guess code against the provided value.
     *
     * @param guess The code to be tested against the value at the specified index.
     * @return True if the value at the given index in the guess matches the comparison conditions, false otherwise.
     */
    @Override
    public boolean test(Code guess) {
        int secretAtIndex = secretCode.getCifferAtIndex(index);
        int guessAtIndex = guess.getCifferAtIndex(index);

        if (secretAtIndex < value && guessAtIndex < value){
            return true;
        } else if (secretAtIndex == value && guessAtIndex == value) {
            return true;
        } else if (secretAtIndex > value && guessAtIndex > value) {
            return true;
        } else
            return false;
    }

    /**
     * Returns a description of the validator's functionality.
     *
     * @return A string describing the purpose of the validator.
     */
    @Override
    public String toString() {
        return "Compare the position "+(index+1)+" of the code with "+value;
    }
}
