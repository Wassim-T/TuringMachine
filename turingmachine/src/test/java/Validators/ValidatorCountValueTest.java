package Validators;

import model.Code;
import model.ValidatorCountValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorCountValueTest {

    @Test
    public void testEqualCountOfValue() {
        Code secret = new Code(123);
        ValidatorCountValue validator = new ValidatorCountValue(secret, 3,0);

        Code guessEqualCountOfValue = new Code(135);
        assertTrue(validator.test(guessEqualCountOfValue));
    }

    @Test
    public void testDifferentCountOfValue() {
        Code secret = new Code(256);
        ValidatorCountValue validator = new ValidatorCountValue(secret, 5,0);

        Code guessDifferentCountOfValue = new Code(553);
        assertFalse(validator.test(guessDifferentCountOfValue));
    }

    @Test
    public void testAllValue() {
        Code secret = new Code(888);
        ValidatorCountValue validator = new ValidatorCountValue(secret, 8,0);

        Code guessAllValue = new Code(888);
        assertTrue(validator.test(guessAllValue));
    }

    @Test
    public void testNoneValue() {
        Code secret = new Code(111);
        ValidatorCountValue validator = new ValidatorCountValue(secret, 2,0);

        Code guessNoneValue = new Code(333);
        assertTrue(validator.test(guessNoneValue));
    }
}
