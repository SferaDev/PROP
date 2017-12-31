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
    private PresentationController presentationController = PresentationController.getInstance();

    @Override
    public void start(Stage primaryStage) {
        presentationController.setGameInterface(new GameInterfaceReceiver());
        presentationController.launchLoginForm(primaryStage);
    }
}