package presentation;

import javafx.application.Application;
import javafx.stage.Stage;
import presentation.controller.PresentationController;
import presentation.controller.receiver.GameInterfaceReceiver;

/**
 * The type Visual mastermind.
 *
 * @author Alexis Rico Carreto
 */
public class VisualMastermind extends Application {
    private final PresentationController presentationController = PresentationController.getInstance();

    /**
     * Starts the Visual Mastermind
     *
     * @param primaryStage is used to build the scene.
     */
    @Override
    public void start(Stage primaryStage) {
        presentationController.setGameInterface(new GameInterfaceReceiver());
        presentationController.launchLoginForm(primaryStage);
    }
}
