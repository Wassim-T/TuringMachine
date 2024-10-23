package view.console;

import model.*;
import model.util.Observer;

import java.util.List;

/**
 * Represents the console view of the Turing machine game.
 * This class observes changes in the game and displays the game state in the console.
 */
public class ViewConsole implements Observer {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREY = "\u001B[90m";

    private TuringMachine turingMachine;
    private List<Validator> validators;

    private ValidatorState[] currentStates;

    private int score;
    private int turn;

    private Code guess;

    /**
     * Constructs a ViewConsole object for the provided TuringMachine.
     *
     * @param turingMachine The TuringMachine instance to observe.
     */
   public ViewConsole(TuringMachine turingMachine){
       this.score = 0;
       this.turn = 0;
       this.guess = null;
       this.turingMachine = turingMachine;
       this.validators = turingMachine.getValidators();
       this.currentStates = new ValidatorState[validators.size()];
       for (int i = 0; i < currentStates.length; i++) {
           currentStates[i] = ValidatorState._UNSELECTED;
       }
       turingMachine.addObserver(this);
   }

    /**
     * Displays the welcome message for the game.
     */
    public static void displayWelcomeMessage() {
        System.out.println();
        System.out.println(ANSI_RED + "         Turing machine" + ANSI_RESET);
        System.out.println("");
        System.out.println(ANSI_GREEN + "          Welcome" + ANSI_RESET);
        System.out.println();
        System.out.println("Voulez vous charger un niveau au hasard ?(y/n)");

    }

    /**
     * Displays the current state of the game in the console.
     */
    public void displayGame() {
        System.out.println("Score : " + score + "      Turn : " + turn);



        for (int i = 0; i < validators.size(); i++) {
         String validatorColor = determineColor(currentStates[i]);
            System.out.print("Validator "+(i+1)+" : ");
           System.out.println(validatorColor + validators.get(i)  + ANSI_RESET);

        }
        if (guess== null){
            System.out.println("Code : No Code entered yet");
        }
        else{
            System.out.println("Code : "+guess.getSecretCodeValue());
        }

    }

    /**
     * Used to determine the color to put on the validator
     * @param state of the validator
     * @return  the color in ANSI format
     */
    private String determineColor(ValidatorState state) {
        switch (state) {
            case _UNSELECTED:
                return ANSI_GREY;
            case _TRUE:
                return ANSI_GREEN;
            case _FALSE:
                return ANSI_RED;
            default:
                return ANSI_RESET;
        }
    }


    /**
     * Displays the list of available problems for the game.
     *
     * @param problems The list of problems to display.
     */
    public static void displayProblems(List<Problem> problems){
        System.out.println("niveau / difficulty / luck  ");
       for (Problem problem : problems){
           System.out.println(problem.getLevel()+" / "+ problem.getDifficulty()+" / "+ problem.getLuck());
       }
    }



    /**
     * Displays the available commands for the Turing machine game.
     */
    public static void displayHelp() {
        System.out.println("Turing machine commands:");
        System.out.println("- input a code : input");
        System.out.println("- test a validator : test");
        System.out.println("- go to next turn : pass");
        System.out.println("- play final move : submit");
        System.out.println("- undo : undo");
        System.out.println("- redo : redo");
        System.out.println("- quit : quit");
        System.out.println();

    }


    @Override
    public void update(Notification notification, int indexValidator) {
      //TODO j'ai des problèmes de "lien" entre objet validators ce met pas a jour quand turingmachine se fait undo et je peux pas undo 2 fois de suite
        switch (notification) {
            case _NEW_STATE:
                this.validators = turingMachine.getValidators();
                this.currentStates[indexValidator] = validators.get(indexValidator).getState();
                break;
            case _NEW_TURN:
                this.turn = turingMachine.getTurn();
                break;
            case _NEW_SCORE:
                this.score = turingMachine.getScore();
                break;
            case _NEW_GUESS:
                this.guess = turingMachine.getGuess();
                break;
            default:
                throw new TuringMachineException("Problème Notification");
        }

    }
}



