package model;

import model.command.Command;
import model.command.CommandManager;
import model.util.Observable;
import model.util.Observer;

import java.util.ArrayList;
import java.util.List;
/**
 * The TuringMachine class represents the core logic of a Turing Machine game.
 * It manages the game state, commands, and interactions between components.
 */
public class TuringMachine implements Observable {

    private Problem problem;
    private CommandManager commandManager;
    private int score;
    private int turn;
    private Code guess;
    private List<Validator> tempvalidators;
    List<Observer> observers;


    /**
     * Constructs a TuringMachine object with the provided problem.
     *
     * @param problem The problem instance for the Turing Machine game.
     */
    public TuringMachine(Problem problem) {
        this.commandManager = new CommandManager();
        this.observers = new ArrayList<>();
        this.tempvalidators = new ArrayList<>();
        this.score = 0;
        this.turn = 1;
        this.problem = problem;
    }

    /**
     * Retrieves the temporary validators.
     *
     * @return The list of temporary validators.
     */
    public List<Validator> getTempvalidators(){
        return tempvalidators;
    }

    /**
     * Getter for the score
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter for the validators
     * @return the current validators
     */
    public List<Validator> getValidators(){
        return problem.getValidators();
    }

    /**
     * Getter for the current turn
     * @return the current turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Getter for the guess
     * @return the current guess
     */
    public Code getGuess(){
        return guess;
    }

    /**
     * redo the undone command
     */
    public void redo() {
        commandManager.redoCommand();
    }

    /**
     * undo the command
     */
    public void undo() {
        commandManager.undoCommand();
    }

    /**
     * execute a command
     * @param command the command to execute
     */
    public void execute(Command command){

        commandManager.executeCommand(command);

    }

    /**
     * Used to quit the game
     */
    public void quit() {
        System.exit(0);
    }

    /**
     * Used to pass to the next turn
     */
    public void nextTurn() {
        turn++;
        guess = null;

        tempvalidators.clear();
        problem.getValidators().forEach(e -> e.setState(ValidatorState._UNSELECTED));
        int[] indexestoNotify = new int[problem.getValidators().size()];
        for (int i = 0; i< indexestoNotify.length;i++ ){
            indexestoNotify[i] = i;
        }
        notifyObservers(Notification._NEW_TURN);
        notifyObservers(Notification._NEW_GUESS);
        notifyObservers(Notification._NEW_STATE,indexestoNotify);
    }


    /**
     * Setter for the score
     * @param score the value to add to the current score
     */
    public void setScore(int score) {
        this.score = this.score + score;
        notifyObservers(Notification._NEW_SCORE);
    }

    /**
     * Setter for the turn
     * @param turn the value to add to the current turn
     */
    public void setTurn(int turn) {
        this.turn = this.turn + turn;
        notifyObservers(Notification._NEW_TURN);
    }

    /**
     * Setter for the validators
     * @param newValidators the new validators to set
     * @param newTempValidators the new temporary validators to set
     */
    public void setValidators(List<Validator> newValidators, List<Validator> newTempValidators){
        problem.setValidators(newValidators);
        this.tempvalidators = newTempValidators;
    }

    /**
     * Used to Input the user's code
     * @param guess the code entered by the user
     */
    public void InputGuess(Code guess){
        if (!tempvalidators.isEmpty()){
            throw new TuringMachineException("A Validator has been played this turn");
        }
        this.guess = guess;
        notifyObservers(Notification._NEW_GUESS);
    }

    /**
     * Used to play the final play
     * @return if the code is identical to the secretcode or not
     */
    public boolean playCode(){
        if (guess == null){
            throw new TuringMachineException("No code put");
        }
        return guess.getSecretCodeValue() == problem.getSecretCode().getSecretCodeValue();
    }


    /**
     * Used to check if the validator is valid with the current guess
     * @param indexValidator the index of the validator to check
     */
    public void checkValidator(int indexValidator) {
        if (tempvalidators.size() == 3) {
            throw new TuringMachineException("You have already chosen 3 validators go to next turn");
        } else if (guess == null) {
            throw new TuringMachineException("No Code put");
        }
        Validator validator = problem.getValidators().get(indexValidator);

        if (!tempvalidators.contains(validator)) {
            tempvalidators.add(validator);
            score++;
        }
        if (validator.test(guess)){
            validator.setState(ValidatorState._TRUE);

        }
        else{
            validator.setState(ValidatorState._FALSE);

        }
        notifyObservers(Notification._NEW_SCORE);
        notifyObservers(Notification._NEW_STATE,indexValidator);


    }
    /**
     * Adds an observer to the list of observers.
     *
     * @param observer The observer to be added.
     */
    @Override
    public void addObserver(Observer observer) {
          if(!observers.contains(observer)){
              observers.add(observer);
          }
    }

    /**
     * Removes the observer from the list.
     *
     * @param observer The  observer to be removed.
     */
    @Override
    public void removeObserver(Observer observer) {
             observers.remove(observer);
    }

    /**
     * Notifies all observers by calling their 'update' method.
     */
    @Override
    public void notifyObservers(Notification notification,int... indexValidator) {
        if (indexValidator.length == 0){
            observers.forEach(e -> e.update(notification,0));
        } else {
            for (int index : indexValidator){
                observers.forEach(e -> e.update(notification, index));
            }
        }


    }



}
