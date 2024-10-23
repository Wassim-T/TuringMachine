package Validators;

import model.Code;
import model.ValidatorExtremum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorExtremumTest {

    @Test
    public void testFindBigger() {
        Code secretBigger = new Code(753);
        ValidatorExtremum validatorBigger = new ValidatorExtremum(true, secretBigger,0);

        Code guessBigger = new Code(935);
        assertTrue(validatorBigger.test(guessBigger));
    }

    @Test
    public void testFindBiggerNotSame() {
        Code secretNotBigger = new Code(246);
        ValidatorExtremum validatorNotBigger = new ValidatorExtremum(true, secretNotBigger,0);

        Code guessNotBigger = new Code(624);
        assertFalse(validatorNotBigger.test(guessNotBigger));
    }

    @Test
    public void testFindSmaller() {
        Code secretSmaller = new Code(215);
        ValidatorExtremum validatorSmaller = new ValidatorExtremum(false, secretSmaller,0);

        Code guessSmaller = new Code(315);
        assertTrue(validatorSmaller.test(guessSmaller));
    }

    @Test
    public void testEquals() {
        Code secretNotSmaller = new Code(888);
        ValidatorExtremum validatorNotSmaller = new ValidatorExtremum(false, secretNotSmaller,0);

        Code guessNotSmaller = new Code(888);
        assertTrue(validatorNotSmaller.test(guessNotSmaller));
    }
}
