package model;
/**
 * The Notification enum represents different types of notifications used in the Turing Machine.
 * These notifications can trigger specific actions or updates in the application.
 */
public enum Notification {
    /**
     * Represents a notification for a new state.
     */
    _NEW_STATE,

    /**
     * Represents a notification for a new turn.
     */
    _NEW_TURN,

    /**
     * Represents a notification for a new score.
     */
    _NEW_SCORE,

    /**
     * Represents a notification for a new guess.
     */
    _NEW_GUESS
}

