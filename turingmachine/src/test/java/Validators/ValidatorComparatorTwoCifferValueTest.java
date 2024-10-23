package Validators;

import model.Code;
import model.ValidatorComparatorTwoCifferValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorComparatorTwoCifferValueTest {

    @Test
    public void testBigger() {
        Code secret = new Code(523);
        ValidatorComparatorTwoCifferValue validator = new ValidatorComparatorTwoCifferValue(secret, 0, 1,0);

        Code guessAscending = new Code(934);
        assertTrue(validator.test(guessAscending));
    }

    @Test
    public void testSmaller() {
        Code secret = new Code(354);
        ValidatorComparatorTwoCifferValue validator = new ValidatorComparatorTwoCifferValue(secret, 0, 1,0);

        Code guessDescending = new Code(143);
        assertTrue(validator.test(guessDescending));
    }

    @Test
    public void testEqual() {
        Code secret = new Code(555);
        ValidatorComparatorTwoCifferValue validator = new ValidatorComparatorTwoCifferValue(secret, 0, 1,0);

        Code guessEqualDigits = new Code(222);
        assertTrue(validator.test(guessEqualDigits));
    }

    @Test
    public void testMixedValues() {
        Code secret = new Code(789);
        ValidatorComparatorTwoCifferValue validator = new ValidatorComparatorTwoCifferValue(secret, 0, 1,0);

        Code guessMixedValues = new Code(976);
        assertFalse(validator.test(guessMixedValues));
    }
}
