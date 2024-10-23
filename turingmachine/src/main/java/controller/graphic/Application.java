package controller.graphic;

import javafx.stage.Stage;
import model.TuringMachine;
import model.util.Loader;
import view.graphic.GraphicInterfaceLauncher;

/**
 * The main application class for launching the graphical user interface.
 */
public class Application extends javafx.application.Application {

    /**
     * The main method to launch the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes and starts the JavaFX application.
     *
     * @param stage The primary stage for the application
     * @throws Exception If an error occurs during application startup
     */
    @Override
    public void start(Stage stage) throws Exception {

        // Launches the graphic interface

        GraphicInterfaceLauncher launcher = new GraphicInterfaceLauncher(stage);
    }



}
