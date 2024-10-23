package Validators;

import model.Code;
import model.ValidatorMostParity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorMostParityTest {

    @Test
    public void testSecretHasMoreEvenThanOdd() {
        Code secret = new Code(246);
        ValidatorMostParity validator = new ValidatorMostParity(secret,0);

        Code guessMoreEven = new Code(248);
        assertTrue(validator.test(guessMoreEven));
    }

    @Test
    public void testSecretHasMoreOddThanEven() {
        Code secret = new Code(132);
        ValidatorMostParity validator = new ValidatorMostParity(secret,0);

        Code guessMoreOdd = new Code(213);
        assertTrue(validator.test(guessMoreOdd));
    }

    @Test
    public void testNotEqual() {
        Code secretMoreEven = new Code(123);
        ValidatorMostParity validator = new ValidatorMostParity(secretMoreEven,0);

        Code guessMoreEven = new Code(246);
        assertFalse(validator.test(guessMoreEven));
    }


}
