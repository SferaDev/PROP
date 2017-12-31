package presentation.controller;

import domain.controller.DomainController;
import domain.model.exceptions.FinishGameException;
import domain.model.exceptions.UserAlreadyExistsException;
import domain.model.exceptions.UserNotFoundException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import presentation.controller.receiver.GameInterfaceReceiver;
import presentation.controller.receiver.LoginActionReceiver;
import presentation.utils.ComponentUtils;

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

    private NebulaViewController nebulaController;

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
            ComponentUtils.buildScene(getClass(), stage, root, 600, 350);
        } catch (IOException ignored) {
        }
    }

    public void launchNebulaForm(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(NEBULA_FXML_PATH));
            Parent root = loader.load();
            nebulaController = loader.getController();
            ComponentUtils.buildScene(getClass(), stage, root, 800, 600);
        } catch (IOException ignored) {
        }
    }

    public void requestRegister(String username, String password) throws UserAlreadyExistsException {
        DomainController.getInstance().getUserController().createUser(username, password);
    }

    public boolean requestLogin(String username, String password) throws UserNotFoundException {
        return DomainController.getInstance().getUserController().loginUser(username, password);
    }

    public void requestSaveCurrentGame() {
        try {
            DomainController.getInstance().getGameController().saveCurrentGame();
        } catch (FinishGameException e) {
            nebulaController.finishGame();
        }
    }

    public void requestHelpCurrentGame() {
        DomainController.getInstance().getGameController().provideHelp();
    }

    public void closeWindow(Stage stage) {
        stage.close();
    }

    public void setGameInterface(GameInterfaceReceiver gameInterfaceReceiver) {
        DomainController.getInstance().setGameInterface(gameInterfaceReceiver);
    }

    public NebulaViewController getNebulaController() {
        return nebulaController;
    }

}
