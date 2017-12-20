package presentation.visual.controller;

import domain.controller.DomainController;
import domain.model.exceptions.UserAlreadyExistsException;
import domain.model.exceptions.UserNotFoundException;
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

    public void launchLoginForm(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(LOGIN_FXML_PATH));
            Parent root = loader.load();
            LoginViewController controller = loader.getController();
            controller.setListener(new LoginActionReceiver(stage));
            buildScene(stage, root, 600, 350);
        } catch (IOException ignored) {
        }
    }

    public void launchNebulaForm(Stage stage) {
        // TODO: Alexis
    }

    public void requestRegister(String username, String password) throws UserAlreadyExistsException {
        DomainController.getInstance().getUserController().createUser(username, password);
    }

    public boolean requestLogin(String username, String password) throws UserNotFoundException {
        return DomainController.getInstance().getUserController().loginUser(username, password);
    }

    private void buildScene(Stage primaryStage, Parent parent, double minWidth, double minHeight) {
        primaryStage.setTitle("Mastermind"); // TODO: Strings
        primaryStage.setScene(new Scene(parent));
        primaryStage.setMinWidth(minWidth);
        primaryStage.setMinHeight(minHeight);
        primaryStage.show();
    }

    public void closeWindow(Stage stage) {
        stage.close();
    }
}
