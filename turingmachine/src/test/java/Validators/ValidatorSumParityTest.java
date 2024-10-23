package Validators;

import model.Code;
import model.ValidatorSumParity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorSumParityTest {

    @Test
    public void testEvenSum() {
        Code secretEvenSum = new Code(246);
        ValidatorSumParity validatorEvenSum = new ValidatorSumParity(secretEvenSum,0);

        Code guessEvenSum = new Code(125);
        assertTrue(validatorEvenSum.test(guessEvenSum));
    }

    @Test
    public void testOddSum() {
        Code secretOddSum = new Code(135);
        ValidatorSumParity validatorOddSum = new ValidatorSumParity(secretOddSum,0);

        Code guessOddSum = new Code(146);
        assertTrue(validatorOddSum.test(guessOddSum));
    }

    @Test
    public void testNotEqualsSum() {
        Code secretNotEvenSum = new Code(123);
        ValidatorSumParity validatorNotEvenSum = new ValidatorSumParity(secretNotEvenSum,0);

        Code guessNotEvenSum = new Code(111);
        assertFalse(validatorNotEvenSum.test(guessNotEvenSum));
    }


}
