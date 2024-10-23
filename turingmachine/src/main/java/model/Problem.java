package model;

import model.command.ValidatorFactory;

import java.util.List;

/**
 * The Problem class represents a specific problem within the Turing Machine.
 * It encapsulates details such as level, difficulty, luck, secret code, and associated validators.
 */
public class Problem {
    private final int level;
    private final int difficulty;
    private final int luck;
    private final Code secretCode;
    private List<Validator> validators; // Not final because state changes

    /**
     * Constructs a Problem object with the provided attributes.
     *
     * @param level       The level of the problem.
     * @param difficulty  The difficulty of the problem.
     * @param luck        The luck associated with the problem.
     * @param secretCode  The secret code associated with the problem.
     * @param validators  The list of validators associated with the problem.
     */
    public Problem(int level, int difficulty, int luck, Code secretCode, List<Validator> validators) {
        this.level = level;
        this.difficulty = difficulty;
        this.luck = luck;
        this.secretCode = secretCode;
        this.validators = validators;
    }

    /**
     * Retrieves the level of the problem.
     *
     * @return The level of the problem.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Retrieves the difficulty of the problem.
     *
     * @return The difficulty of the problem.
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Retrieves the luck associated with the problem.
     *
     * @return The luck associated with the problem.
     */
    public int getLuck() {
        return luck;
    }

    /**
     * Retrieves the secret code associated with the problem.
     *
     * @return The secret code associated with the problem.
     */
    public Code getSecretCode() {
        return secretCode;
    }

    /**
     * Retrieves the list of validators associated with the problem.
     *
     * @return The list of validators associated with the problem.
     */
    public List<Validator> getValidators() {
        return validators;
    }

    /**
     * Sets the list of validators for the problem, allowing state changes.
     *
     * @param changedStateValidators The updated list of validators for the problem.
     */
    public void setValidators(List<Validator> changedStateValidators) {
        this.validators = changedStateValidators;
    }
}
