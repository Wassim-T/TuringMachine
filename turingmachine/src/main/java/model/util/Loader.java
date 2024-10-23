package model.util;

import model.Code;
import model.Problem;
import model.TuringMachineException;
import model.Validator;
import model.command.ValidatorFactory;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Loader class reads and processes a CSV file containing problem information to load into the application.
 * It retrieves problem data from the CSV file, constructs problems with associated validators,
 * and provides access to this information for the application's use.
 */
public class Loader {
    private List<Problem> problems;

    /**
     * The singleton instance of the Loader class.
     */
    private static final Loader instance = new Loader();

    /**
     * Constructs a Loader object, initializing the problems list and loading game data from a CSV file.
     */
    public Loader() {
        this.problems = new ArrayList<>();
        loadGame();
    }

    /**
     * Loads game data from a CSV file containing known problems and their information.
     * Processes each line to create Problem objects with associated validators and adds them to the problems list.
     */
    private void loadGame() {
        String cheminFichierCSV = "src\\Resources\\known_problems.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichierCSV))) {

                         String line;


                         boolean firstLine = true;
            while((line = reader.readLine())!=null){

                             {
                                 if (firstLine) {
                                     firstLine = false;
                                 } else {
                                     String[] tokens = line.split(",");
                                     int level = Integer.valueOf(tokens[0]);
                                     int difficulty = Integer.valueOf(tokens[1]);
                                     int luck = Integer.valueOf(tokens[2]);
                                     Code secretCode = new Code(Integer.valueOf(tokens[3]));
                                     List<Validator> validators = new ArrayList<>();
                                     ValidatorFactory validatorFactory = new ValidatorFactory(secretCode);
                                     List<String> lastFour = Arrays.asList(Arrays.copyOfRange(tokens, 4, tokens.length));
                                     for (String index : lastFour) {
                                         validators.add(makeValidator(Integer.valueOf(index), validatorFactory));
                                     }
                                     problems.add(new Problem(level, difficulty, luck, secretCode, validators));
                                 }
                             }
                         }


                     } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructs a validator based on the given index using the provided validator factory.
     *
     * @param index            The index used to create the validator
     * @param validatorFactory The factory used to generate the validator
     * @return The constructed validator
     */
    private Validator makeValidator(int index, ValidatorFactory validatorFactory) {
        return validatorFactory.MakeValidator(index);
    }

    /**
     * Retrieves the singleton instance of the Loader class.
     *
     * @return The singleton instance of the Loader class
     */
    public static Loader getInstance() {
        return instance;
    }

    /**
     * Gets a specific problem based on the provided index level.
     *
     * @param indexLevel The index level used to retrieve the problem
     * @return The problem associated with the provided index level
     */
    public Problem getSpecificProblem(int indexLevel) {
        return problems.get(indexLevel);
    }

    /**
     * Gets the list of problems loaded from the CSV file.
     *
     * @return The list of problems loaded from the CSV file
     */
    public List<Problem> getProblems() {
        return problems;
    }
}
