package presentation;

import domain.controller.StatController;
import javafx.application.Application;
import javafx.stage.Stage;
import presentation.controller.PresentationController;
import presentation.controller.receiver.VisualGameReceiver;

/**
 * The type Visual mastermind.
 *
 * @author Oriol Borrell Roig
 */
public class VisualMastermind extends Application {
    /**
     * Starts the Visual Mastermind
     *
     * @param primaryStage is used to build the scene.
     */
    @Override
    public void start(Stage primaryStage) {
        PresentationController.getInstance().setGameInterface(new VisualGameReceiver());
        PresentationController.getInstance().launchLoginForm();
    }
}
