package Validators;

import model.Code;
import model.ValidatorComparatorSumTwoCifferValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorComparatorSumTwoCifferValueTest {

    @Test
    public void testLessThan() {
        Code secret = new Code(123);
        ValidatorComparatorSumTwoCifferValue validator = new ValidatorComparatorSumTwoCifferValue(secret,0);

        Code guessLessThan = new Code(233);
        assertTrue(validator.test(guessLessThan));
    }

    @Test
    public void testEquals() {
        Code secret = new Code(333);
        ValidatorComparatorSumTwoCifferValue validator = new ValidatorComparatorSumTwoCifferValue(secret,0);

        Code guessEquals = new Code(333);
        assertTrue(validator.test(guessEquals));
    }

    @Test
    public void testGreaterThan() {
        Code secret = new Code(563);
        ValidatorComparatorSumTwoCifferValue validator = new ValidatorComparatorSumTwoCifferValue(secret,0);

        Code guessGreaterThan = new Code(473);
        assertTrue(validator.test(guessGreaterThan));
    }

    @Test
    public void testMixedValues() {
        Code secret = new Code(783);
        ValidatorComparatorSumTwoCifferValue validator = new ValidatorComparatorSumTwoCifferValue(secret,0);

        Code guessMixedValues = new Code(123);
        assertFalse(validator.test(guessMixedValues));
    }
}
