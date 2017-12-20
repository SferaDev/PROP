package presentation.visual;

import javafx.application.Application;
import javafx.stage.Stage;
import presentation.visual.controller.PresentationController;

/**
 * The type Visual mastermind.
 *
 * @author Alexis Rico Carreto
 */
public class VisualMastermind extends Application {
    @Override
    public void start(Stage primaryStage) {
        PresentationController.getInstance().launchLoginForm(primaryStage);
    }
}
