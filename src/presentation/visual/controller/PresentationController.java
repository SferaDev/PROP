package presentation.visual.controller;

import domain.controller.DomainController;
import domain.model.exceptions.UserAlreadyExistsException;
import domain.model.exceptions.UserNotFoundException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import presentation.visual.controller.receiver.GameInterfaceReceiver;
import presentation.visual.controller.receiver.LoginActionReceiver;
import presentation.visual.utils.ComponentUtils;

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

    private BoardController currentBoard;

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
            NebulaViewController controller = loader.getController();
            ComponentUtils.buildScene(getClass(), stage, root, 600, 350);
        } catch (IOException ignored) {
        }
    }

    public void requestRegister(String username, String password) throws UserAlreadyExistsException {
        DomainController.getInstance().getUserController().createUser(username, password);
    }

    public boolean requestLogin(String username, String password) throws UserNotFoundException {
        return DomainController.getInstance().getUserController().loginUser(username, password);
    }

    public void closeWindow(Stage stage) {
        stage.close();
    }

    public void setGameInterface(GameInterfaceReceiver gameInterfaceReceiver) {
        DomainController.getInstance().setGameInterface(gameInterfaceReceiver);
    }

    /**
     * Gets current board.
     *
     * @return the current board
     */
    public BoardController getCurrentBoard() {
        return currentBoard;
    }

    /**
     * Sets current board.
     *
     * @param currentBoard the current board
     */
    public void setCurrentBoard(BoardController currentBoard) {
        this.currentBoard = currentBoard;
    }
}
