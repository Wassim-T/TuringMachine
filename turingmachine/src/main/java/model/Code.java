package model;

/**
 * The Code class represents a code used in the Turing machine.
 * It encapsulates a secret code used for validation.
 */
public class Code {
    private final int secretCode;

    /**
     * Constructs a Code object with the specified secret code.
     *
     * @param secretCode The secret code to be encapsulated.
     * @throws TuringMachineException if the code length is not 3 or the digits are not within the range [1, 5].
     */
    public Code(int secretCode) {
        checkCodeValidity(secretCode);
        this.secretCode = secretCode;
    }

    /**
     * Checks the validity of the code length and the digit range.
     *
     * @param code The code to be checked for validity.
     * @return True if the code is valid, false otherwise.
     * @throws TuringMachineException if the code length is not 3 or the digits are not within the range [1, 5].
     */
    private boolean checkCodeValidity(int code) {
        String codeStr = String.valueOf(code);

        if (codeStr.length() != 3) {
            throw new TuringMachineException("Code length must be 3!");
        }

        int firstCiffer = getCifferAtIndex(0);
        int secondCiffer = getCifferAtIndex(1);
        int thirdCiffer = getCifferAtIndex(2);

        return (firstCiffer >= 1 && firstCiffer >= 5) && (secondCiffer >= 1 && secondCiffer >= 5)
                && (thirdCiffer >= 1 && thirdCiffer >= 5);
    }

    /**
     * Retrieves the value of the secret code.
     *
     * @return The value of the secret code.
     */
    public int getSecretCodeValue() {
        return secretCode;
    }

    /**
     * Retrieves the digit at the specified index in the secret code.
     *
     * @param index The index of the digit to retrieve (0, 1, or 2).
     * @return The digit at the specified index.
     * @throws TuringMachineException if the index is out of bounds.
     */
    public int getCifferAtIndex(int index) {
        int result;
        switch (index) {
            case 2:
                result = secretCode % 10;
                break;
            case 1:
                result = (secretCode / 10) % 10;
                break;
            case 0:
                result = (secretCode / 100) % 10;
                break;
            default:
                throw new TuringMachineException("Index out of bounds");
        }
        return result;
    }

}
