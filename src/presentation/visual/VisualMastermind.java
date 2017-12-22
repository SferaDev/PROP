package presentation.visual;

import javafx.application.Application;
import javafx.stage.Stage;
import presentation.visual.controller.PresentationController;
import presentation.visual.controller.receiver.GameInterfaceReceiver;

/**
 * The type Visual mastermind.
 *
 * @author Alexis Rico Carreto
 */
public class VisualMastermind extends Application {
    PresentationController presentationController = PresentationController.getInstance();
    @Override
    public void start(Stage primaryStage) {
        presentationController.setGameInterface(new GameInterfaceReceiver());
        presentationController.launchLoginForm(primaryStage);
    }
}
