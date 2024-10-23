    package controller.console;

    import model.TuringMachineException;
    import model.util.Loader;
    import model.TuringMachine;
    import model.command.InputCodeCommand;
    import model.command.NextTurnCommand;
    import model.command.VerifyValidatorCommand;
    import view.console.ViewConsole;

    import java.util.Collections;
    import java.util.Random;
    import java.util.Scanner;

    public class Application {

        public static void main(String[] args) {
            ViewConsole.displayWelcomeMessage();
            TuringMachine turingMachine;

            Loader loader = Loader.getInstance();



            int level;

            if (isRandomLevel("Entrez y pour oui / n pour non : ")) {
                Random random = new Random();
                level = random.nextInt(16) ;
                turingMachine = new TuringMachine(loader.getSpecificProblem(level));
            } else {
                ViewConsole.displayProblems(loader.getProblems());
                level = chooseLevel(loader.getProblems().size());
                turingMachine = new TuringMachine(loader.getSpecificProblem(level-1));
            }

            ViewConsole viewConsole = new ViewConsole(turingMachine);

            while (true) {
                try{
                    viewConsole.displayGame();
                    System.out.println("Write help to see commands");
                    String userInput = userInput("command : ").trim();

                    if (userInput.equalsIgnoreCase("help")){
                        viewConsole.displayHelp();
                    } else if (userInput.equalsIgnoreCase("input")){
                        System.out.print("Enter the code :");
                        InputCodeCommand command = new InputCodeCommand(turingMachine,getInt());
                        turingMachine.execute(command);
                        System.out.println();
                    } else if (userInput.equalsIgnoreCase("test")) {
                        System.out.print("Enter the index of the Validator :");
                        VerifyValidatorCommand command = new VerifyValidatorCommand(turingMachine,chooseValidator()-1);
                        turingMachine.execute(command);
                        System.out.println();
                    } else if (userInput.equalsIgnoreCase("pass")){
                        NextTurnCommand command = new NextTurnCommand(turingMachine);
                        turingMachine.execute(command);
                    } else if (userInput.equalsIgnoreCase("submit")) {
                        if (turingMachine.playCode()){
                            System.out.println(viewConsole.ANSI_GREEN+"You won !"+viewConsole.ANSI_RESET);
                            turingMachine.quit();
                        }
                        else{
                            System.out.println(viewConsole.ANSI_RED+"You lost !"+viewConsole.ANSI_RESET);
                            turingMachine.quit();
                        }
                    } else if (userInput.equalsIgnoreCase("undo")){
                        turingMachine.undo();
                    } else if (userInput.equalsIgnoreCase("redo")) {
                        turingMachine.redo();
                    } else if (userInput.equalsIgnoreCase("quit")) {
                        turingMachine.quit();
                    } else{
                        System.out.println("Commande incorrect");
                    }

                } catch (TuringMachineException e){
                    System.out.println(viewConsole.ANSI_RED+e.getMessage()+viewConsole.ANSI_RESET);
                }


            }
        }



        /**
         * Allows the user to select a level from a given range.
         *
         * @param numberOfLevels The total number of levels available.
         * @return The selected level by the user.
         */
        private static int chooseLevel(int numberOfLevels) {

            int entier;
            System.out.println("Entrez le niveau voulu");
            do {
                System.out.println("(de 1 a "+numberOfLevels+")");
                entier = getInt();
            } while (entier < 1 && entier > numberOfLevels );
            return entier;
        }


        /**
         * Allows the user to choose a validator index.
         *
         * @return The selected validator index by the user.
         */
        private static int chooseValidator() {
            Scanner clavier = new Scanner(System.in);
            int entier = 0;
            do {


                while (!clavier.hasNextInt()) {
                    System.out.println("Choose a Validator from 1 to 4");
                    clavier.next();
                }
                entier = clavier.nextInt();
                if (entier <= 0) {
                    System.out.println("No number 0 Validator");
                }
            } while (entier < 1 && entier > 4);
            return entier;
        }


        /**
         * Used to get a int no mather what
         * @return a int
         */
        private static int getInt() {
            Scanner clavier = new Scanner(System.in);
            int entier;
            while (!clavier.hasNextInt()) {
                clavier.next();
            }
            entier = clavier.nextInt();
            return entier;
        }




        /**
         * Used to see if the user want a random level
         *
         * @return The selected validator index by the user.
         */
        private static boolean isRandomLevel(String message) {
            String choice;
            do {
                choice = userInput(message);
            } while (!choice.equals("y") && !choice.equals("n"));

            return choice.equals("y");
        }


        /**
         * Used to get input from user
         * @param message message to show
         * @return what the user wrote
         */
        private static String userInput(String message) {
            Scanner clavier = new Scanner(System.in);
            System.out.print(message);
            return clavier.nextLine();
        }




    }
