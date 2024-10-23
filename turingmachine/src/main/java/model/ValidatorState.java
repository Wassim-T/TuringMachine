package model;

import javafx.scene.paint.Color;

/**
 * ValidatorState enum represents the different states a validator can have.
 * Each state is associated with a specific color.
 */
public enum ValidatorState {
    /**
     * ValidatorState representing a true or correct state.
     */
    _TRUE(Color.GREEN),

    /**
     * ValidatorState representing a false or incorrect state.
     */
    _FALSE(Color.RED),

    /**
     * ValidatorState representing an unselected state.
     */
    _UNSELECTED(null);

    private final Color color;

    /**
     * Constructor for ValidatorState with a specific color.
     *
     * @param color The associated color for the state.
     */
    ValidatorState(Color color) {
        this.color = color;
    }

    /**
     * Retrieves the color associated with the state.
     *
     * @return The color associated with the state.
     */
    public Color getColor() {
        return this.color;
    }
}
