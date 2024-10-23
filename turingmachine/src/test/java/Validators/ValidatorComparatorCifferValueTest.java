package Validators;

import model.Code;
import model.ValidatorComparatorCifferValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorComparatorCifferValueTest {

    @Test
    public void testLessThan() {
        Code secret = new Code(123);
        ValidatorComparatorCifferValue validator = new ValidatorComparatorCifferValue(secret, 1, 5,0);

        Code guessLessThan = new Code(234);
        assertTrue(validator.test(guessLessThan));
    }

    @Test
    public void testEquals() {
        Code secret = new Code(756);
        ValidatorComparatorCifferValue validator = new ValidatorComparatorCifferValue(secret, 0, 7,0);

        Code guessEquals = new Code(776);
        assertTrue(validator.test(guessEquals));
    }

    @Test
    public void testGreaterThan() {
        Code secret = new Code(789);
        ValidatorComparatorCifferValue validator = new ValidatorComparatorCifferValue(secret, 2, 4,0);

        Code guessGreaterThan = new Code(729);
        assertTrue(validator.test(guessGreaterThan));
    }

    @Test
    public void testMixedValues() {
        Code secret = new Code(216);
        ValidatorComparatorCifferValue validator = new ValidatorComparatorCifferValue(secret, 1, 3,0);

        Code guessMixedValues = new Code(256);
        assertFalse(validator.test(guessMixedValues));
    }
}
