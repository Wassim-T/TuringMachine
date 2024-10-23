package Validators;

import model.Code;
import model.ValidatorTwoSameCiffer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTwoSameCifferTest {

    @Test
    public void testTwoSameCiffer() {
        Code secretTwoSame = new Code(121);
        ValidatorTwoSameCiffer validatorTwoSame = new ValidatorTwoSameCiffer(secretTwoSame,0);

        Code guessTwoSame = new Code(211);
        assertTrue(validatorTwoSame.test(guessTwoSame));
    }

    @Test
    public void testNoTwoSameCiffer() {
        Code secretNoTwoSame = new Code(123);
        ValidatorTwoSameCiffer validatorNoTwoSame = new ValidatorTwoSameCiffer(secretNoTwoSame,0);

        Code guessNoTwoSame = new Code(456);
        assertTrue(validatorNoTwoSame.test(guessNoTwoSame));
    }

    @Test
    public void testNotEqual() {
        Code secretMultipleSame = new Code(111);
        ValidatorTwoSameCiffer validatorMultipleSame = new ValidatorTwoSameCiffer(secretMultipleSame,0);

        Code guessMultipleSame = new Code(211);
        assertFalse(validatorMultipleSame.test(guessMultipleSame));
    }


}
