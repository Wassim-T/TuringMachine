package Validators;

import model.Code;
import model.ValidatorCountEven;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorCountEvenTest {

    @Test
    public void testEqualEvenCount() {
        Code secret = new Code(246);
        ValidatorCountEven validator = new ValidatorCountEven(secret,0);

        Code guessEqualEvenCount = new Code(482);
        assertTrue(validator.test(guessEqualEvenCount));
    }

    @Test
    public void testOneEvenCount() {
        Code secret = new Code(125);
        ValidatorCountEven validator = new ValidatorCountEven(secret,0);

        Code guessDifferentEvenCount = new Code(299);
        assertTrue(validator.test(guessDifferentEvenCount));
    }

    @Test
    public void testTwoEven() {
        Code secret = new Code(881);
        ValidatorCountEven validator = new ValidatorCountEven(secret,0);

        Code guessAllEven = new Code(221);
        assertTrue(validator.test(guessAllEven));
    }

    @Test
    public void testNoneEven() {
        Code secret = new Code(111);
        ValidatorCountEven validator = new ValidatorCountEven(secret,0);

        Code guessNoneEven = new Code(333);
        assertTrue(validator.test(guessNoneEven));
    }

    @Test
    public void testNotSameCategory(){
        Code secret = new Code(121);
        ValidatorCountEven validator = new ValidatorCountEven(secret,0);

        Code guessNoneEven = new Code(222);
        assertFalse(validator.test(guessNoneEven));
    }
}
