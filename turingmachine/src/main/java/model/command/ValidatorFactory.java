package model.command;

import model.*;

public class ValidatorFactory {
    Code secretCode;

    /**
     * Constructs a ValidatorFactory with a provided secret code.
     *
     * @param secretCode The secret code used by the validators for comparison and validation
     */
    public ValidatorFactory(Code secretCode){
        this.secretCode = secretCode;
    }

    /**
     * Creates a validator based on the given index.
     *
     * @param indexValidator The index to determine which validator to create
     * @return A specific validator based on the given index
     * @throws TuringMachineException If an invalid index is provided
     */
    public Validator MakeValidator(int indexValidator){
        switch (indexValidator){
            case 1 :
                return new ValidatorComparatorCifferValue(secretCode,0,1,1);
            case 2 :
                return new ValidatorComparatorCifferValue(secretCode,0,3,2);
            case 3 :
                return new ValidatorComparatorCifferValue(secretCode,1,3,3);
            case 4 :
                return new ValidatorComparatorCifferValue(secretCode,1,4,4);
            case 5 :
                return new ValidatorParity(secretCode,0,5);
            case 6 :
                return new ValidatorParity(secretCode,1,6);
            case 7 :
                return new ValidatorParity(secretCode,2,7);
            case 8 :
                return new ValidatorCountValue(secretCode,1,8);
            case 9 :
                return new ValidatorCountValue(secretCode,3,9);
            case 10 :
                return new ValidatorCountValue(secretCode,4,10);
            case 11 :
                return new ValidatorComparatorTwoCifferValue(secretCode,0,1,11);
            case 12 :
                return new ValidatorComparatorTwoCifferValue(secretCode,0,2,12);
            case 13 :
                return new ValidatorComparatorTwoCifferValue(secretCode,1,2,13);
            case 14 :
                return  new ValidatorExtremum(false,secretCode,14);
            case 15 :
                return new ValidatorExtremum(true,secretCode,15);
            case 16 :
                return new ValidatorMostParity(secretCode,16);
            case 17 :
                return new ValidatorCountEven(secretCode,17);
            case 18 :
                return new ValidatorSumParity(secretCode,18);
            case 19 :
                return new ValidatorComparatorSumTwoCifferValue(secretCode,19);
            case 20 :
                return new ValidatorRepetition(secretCode,20);
            case 21 :
                return new ValidatorTwoSameCiffer(secretCode,21);
            case 22 :
                return new ValidatorCifferOrder(secretCode,22);

            default:
                throw new TuringMachineException("Validateurs");
        }

    }



}
