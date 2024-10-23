package Validators;

import model.Code;
import model.ValidatorRepetition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorRepetitionTest {

    @Test
    public void testOneRepetition() {
        Code secret = new Code(121);
        ValidatorRepetition validator = new ValidatorRepetition(secret,0);

        Code guessSameRepetition = new Code(121);
        assertTrue(validator.test(guessSameRepetition));
    }

    @Test
    public void testTwoRepetition() {
        Code secret = new Code(555);
        ValidatorRepetition validator = new ValidatorRepetition(secret,0);

        Code guessDifferentRepetition = new Code(777);
        assertTrue(validator.test(guessDifferentRepetition));
    }

   @Test
   public void testNotEquals(){
       Code secret = new Code(787);
       ValidatorRepetition validator = new ValidatorRepetition(secret,0);

       Code guessNoRepetition = new Code(987);
       assertFalse(validator.test(guessNoRepetition));
   }


    @Test
    public void testNoRepetition() {
        Code secret = new Code(789);
        ValidatorRepetition validator = new ValidatorRepetition(secret,0);

        Code guessNoRepetition = new Code(987);
        assertTrue(validator.test(guessNoRepetition));
    }
}
