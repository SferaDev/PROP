package presentation.visual.controller;

import javafx.stage.Stage;
import presentation.visual.view.LoginView;

/**
 * The type Presentation controller.
 *
 * @author Alexis Rico Carreto
 */
public class PresentationController {
    private static final PresentationController mInstance = new PresentationController();

    private PresentationController() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static PresentationController getInstance() {
        return mInstance;
    }

    public void launchLoginForm(Stage primaryStage) {
        new LoginView(primaryStage, null);
    }
}
