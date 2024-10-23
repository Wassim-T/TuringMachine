package Validators;

import model.Code;
import model.ValidatorCifferOrder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorCifferOrderTest {

    @Test
    public void testCroissantGuessSecret() {
        int secret = 123;
        ValidatorCifferOrder validator = new ValidatorCifferOrder(new Code(secret),0);

        int guessCroissant = 456;
        assertTrue(validator.test(new Code(guessCroissant)));
    }

    @Test
    public void testDecroissantGuessSecret() {
        int secret = 654;
        ValidatorCifferOrder validator = new ValidatorCifferOrder(new Code(secret),0);

        int guessDecroissant = 321;
        assertTrue(validator.test(new Code(guessDecroissant)));
    }

    @Test
    public void testNonEqualGuessSecret() {
        int secret = 789;
        ValidatorCifferOrder validator = new ValidatorCifferOrder(new Code(secret),0);

        int guessEgaux = 987;
        assertFalse(validator.test(new Code(guessEgaux)));
    }

    @Test
    public void testNonOrderedGuessSecret() {
        int secret = 151;
        ValidatorCifferOrder validator = new ValidatorCifferOrder(new Code(secret),0);

        int guessNonOrdered = 241;
        assertTrue(validator.test(new Code(guessNonOrdered)));
    }

}
