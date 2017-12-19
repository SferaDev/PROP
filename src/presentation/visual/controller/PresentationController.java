package presentation.visual.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.visual.controller.receiver.LoginActionReceiver;

import java.io.IOException;

/**
 * The type Presentation controller.
 *
 * @author Alexis Rico Carreto
 */
public class PresentationController {
    private static final PresentationController mInstance = new PresentationController();
    private static final String LOGIN_FXML_PATH = "/resources/layout/Login.fxml";
    private static final String NEBULA_FXML_PATH = "/resources/layout/Nebula.fxml";

    private PresentationController() {
    }

    public static PresentationController getInstance() {
        return mInstance;
    }

    public void launchLoginForm(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(LOGIN_FXML_PATH));
            Parent root = loader.load();
            LoginViewController controller = loader.getController();
            controller.setListener(new LoginActionReceiver());
            buildScene(primaryStage, root, 600, 350);
        } catch (IOException ignored) {
        }
    }

    public void launchNebulaForm(Stage primaryStage) {
        // TODO: Alexis
    }

    private void buildScene(Stage primaryStage, Parent parent, double minWidth, double minHeight) {
        primaryStage.setTitle("Mastermind"); // TODO: Strings
        primaryStage.setScene(new Scene(parent));
        primaryStage.setMinWidth(minWidth);
        primaryStage.setMinHeight(minHeight);
        primaryStage.show();
    }
}
