package Validators;

import model.Code;
import model.ValidatorParity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorParityTest {

    @Test
    public void testEvenParity() {
        Code secret = new Code(246);
        ValidatorParity validator = new ValidatorParity(secret, 1,0);

        Code guessEven = new Code(165);
        assertTrue(validator.test(guessEven));
    }

    @Test
    public void testOddParity() {
        Code secret = new Code(135);
        ValidatorParity validator = new ValidatorParity(secret, 2,0);

        Code guessOdd = new Code(241);
        assertTrue(validator.test(guessOdd));
    }

    @Test
    public void testNotEqual() {
        Code secret = new Code(123);
        ValidatorParity validator = new ValidatorParity(secret, 0,0);

        Code guessNotEven = new Code(456);
        assertFalse(validator.test(guessNotEven));
    }


}
