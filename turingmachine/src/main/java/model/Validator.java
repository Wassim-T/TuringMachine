package model;

/**
 * The abstract class Validator represents a validator used in the Turing Machine game to check codes.
 * It provides the basic structure and behavior for different validator types.
 * This class is abstract and must be extended to implement specific validator types.
 */
public abstract class Validator implements Cloneable {

    protected final Code secretCode; // The secret code against which to test
    private ValidatorState state = ValidatorState._UNSELECTED; // The current state of the validator
    private int validatorId; // Identifier used to retrieve the validator's image

    /**
     * Constructs a Validator object with the given secret code and validator identifier.
     *
     * @param secretCode   The secret code used by the validator.
     * @param validatorId  The identifier used to retrieve the validator's image.
     */
    public Validator(Code secretCode, int validatorId) {
        this.secretCode = secretCode;
        this.validatorId = validatorId;
    }

    /**
     * Gets the identifier of the validator.
     *
     * @return The identifier of the validator.
     */
    public int getValidatorId() {
        return this.validatorId;
    }

    /**
     * Sets the state of the validator.
     *
     * @param state The state to set for the validator.
     */
    public void setState(ValidatorState state) {
        this.state = state;
    }

    /**
     * Gets the state of the validator.
     *
     * @return The state of the validator.
     */
    public ValidatorState getState() {
        return this.state;
    }

    /**
     * Abstract method to be implemented by subclasses.
     * Checks if the given guess code satisfies the validator's conditions.
     *
     * @param guess The code to be tested against the validator's conditions.
     * @return True if the code satisfies the validator's conditions, false otherwise.
     */
    public abstract boolean test(Code guess);

    /**
     * Creates and returns a copy of this object using shallow copy.
     * Overrides the clone method from the Object class.
     *
     * @return A cloned instance of this Validator.
     * @throws CloneNotSupportedException if the object's class does not support cloning.
     * @implSpec This method performs a shallow copy of the object.
     *           Subclasses should override this method to implement deep copy if required.
     * @see Cloneable
     */
    @Override
    public Validator clone() {
        try {
            Validator clonedValidator = (Validator) super.clone();
            return clonedValidator;
        } catch (CloneNotSupportedException e) {
            return null; // Returns null if cloning is not supported
        }
    }
}
