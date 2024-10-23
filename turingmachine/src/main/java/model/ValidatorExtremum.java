package model;

/**
 * The ValidatorExtremum class represents a validator that checks for extremum (either the highest or lowest digit)
 * in a code according to a specified condition.
 * It extends the Validator abstract class and implements the test method to identify the highest or lowest digit.
 */
public class ValidatorExtremum extends Validator{

    boolean findBigger;

    /**
     * Constructs a ValidatorExtremum object to find the highest or lowest digit based on the condition.
     *
     * @param findBigger  If true, finds the highest digit; if false, finds the lowest digit.
     * @param secretCode  The secret code used by the validator to determine the extremum.
     * @param id          The identifier used to retrieve the validator's image.
     */
    public ValidatorExtremum(boolean findBigger,Code secretCode,int id){
        super(secretCode, id );
        this.findBigger = findBigger;
    }

    /**
     * Determines whether the highest or lowest digit in the guess matches the extremum in the secret code.
     *
     * @param guess The code to be tested for the highest or lowest digit.
     * @return True if the position of the extremum in the guess matches that in the secret code, false otherwise.
     */
    @Override
    public boolean test(Code guess) {
        int secretCodeFirstCiffer = secretCode.getCifferAtIndex(0);
        int secretCodeSecondCiffer = secretCode.getCifferAtIndex(1);
        int secretCodeThirdCiffer = secretCode.getCifferAtIndex(2);
        int guessFirstCiffer = guess.getCifferAtIndex(0);
        int guessSecondCiffer = guess.getCifferAtIndex(1);
        int guessThirdCiffer = guess.getCifferAtIndex(2);
        if(findBigger){
           int posMaxSecret = 0;
           int maxSecret = Math.max(secretCodeFirstCiffer,secretCodeSecondCiffer);
           maxSecret = Math.max(maxSecret,secretCodeThirdCiffer);
           if (maxSecret == secretCodeFirstCiffer && maxSecret == secretCodeSecondCiffer && maxSecret == secretCodeThirdCiffer){
               posMaxSecret = -1;                      //I use -1 to say that there's no max number
           }
           else{
               if (maxSecret == secretCodeFirstCiffer){
                   posMaxSecret = 0;
               } else if (maxSecret == secretCodeSecondCiffer) {
                   posMaxSecret = 1;
               } else if (maxSecret == secretCodeThirdCiffer) {
                   posMaxSecret = 2;
               } else{
                   System.out.println("Not working for extremum");                //Its to debug
               }
           }

           int posMaxGuess = 0;
           int maxGuess = Math.max(guessFirstCiffer,guessSecondCiffer);
           maxGuess = Math.max(maxGuess,guessThirdCiffer);
           if (maxGuess == guessFirstCiffer && maxGuess == guessSecondCiffer && maxGuess == guessThirdCiffer){
               posMaxGuess = -1;
           }
           else{
               if (maxGuess == guessFirstCiffer){
                   posMaxGuess = 0;
               } else if (maxGuess == guessSecondCiffer) {
                   posMaxGuess = 1;
               } else if (maxGuess == guessThirdCiffer) {
                   posMaxGuess = 2;
               } else{
                   System.out.println("Not working for extremum");              //Its to debug
               }
           }


           return posMaxGuess == posMaxSecret;

        }
        else{
            int posMinSecret = 0;
            int minSecret = Math.min(secretCodeFirstCiffer,secretCodeSecondCiffer);
            minSecret = Math.min(minSecret,secretCodeThirdCiffer);
            if (minSecret == secretCodeFirstCiffer && minSecret == secretCodeSecondCiffer && minSecret == secretCodeThirdCiffer){
                posMinSecret = -1;                      //I use -1 to say that there's no max number
            }
            else{
                if (minSecret == secretCodeFirstCiffer){
                    posMinSecret = 0;
                } else if (minSecret == secretCodeSecondCiffer) {
                    posMinSecret = 1;
                } else if (minSecret == secretCodeThirdCiffer) {
                    posMinSecret = 2;
                } else{
                    System.out.println("Not working for extremum");                //Its to debug
                }
            }

            int posMinGuess = 0;
            int minGuess = Math.min(guessFirstCiffer,guessSecondCiffer);
            minGuess = Math.min(minGuess,guessThirdCiffer);
            if (minGuess == guessFirstCiffer && minGuess == guessSecondCiffer && minGuess == guessThirdCiffer){
                posMinGuess = -1;
            }
            else{
                if (minGuess == guessFirstCiffer){
                    posMinGuess = 0;
                } else if (minGuess == guessSecondCiffer) {
                    posMinGuess = 1;
                } else if (minGuess == guessThirdCiffer) {
                    posMinGuess = 2;
                } else{
                    System.out.println("Not working for extremum");              //Its to debug
                }
            }


            return posMinGuess == posMinSecret;
        }

    }

    /**
     * Returns a description of the validator's functionality.
     *
     * @return A string describing whether the validator identifies the highest or lowest digit.
     */
    @Override
    public String toString() {
        if (findBigger){
            return "Determine which ciffer is the biggest";
        } else {
            return "Determine which ciffer is the lowest";
        }

    }
}
