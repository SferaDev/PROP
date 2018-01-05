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
import presentation.utils.LocaleUtils;

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

    private String mUsername;

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

    public void requestQuitCurrentGame() {
        try {
            DomainController.getInstance().getGameController().stopCurrentGame();
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

    public void requestStartGame(String computerName, String role, int pegs, int colors) {
        DomainController.getInstance().getGameController().startNewGame(getUsername(),
                computerName + "Computer", role, pegs, colors, -1);
        nebulaController.startGame(role, computerName, pegs, colors);
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public void requestPasswordChange(String username, String oldPassword, String newPassword) {
        try {
            if (PresentationController.getInstance().requestLogin(username, oldPassword)) {
                if (oldPassword.equals(newPassword)) {
                    ComponentUtils.showWarningDialog(LocaleUtils.getInstance().getString("SAME_PASSWORD"), LocaleUtils.getInstance().getString("CHOOSE_DIF_PASSWORD"));
                } else {
                    DomainController.getInstance().getUserController().changePassword(username, newPassword);
                    ComponentUtils.showInformationDialog(LocaleUtils.getInstance().getString("PASSWORD_CHANGED"), LocaleUtils.getInstance().getString("CHANGED_SUCCESSFULLY"));
                }
            } else {
                ComponentUtils.showErrorDialog(LocaleUtils.getInstance().getString("INVALID_PASSWORD"), LocaleUtils.getInstance().getString("OLD_PASSWORD_INCORRECT"));
            }
        } catch (UserNotFoundException e) {
            ComponentUtils.showErrorDialog(LocaleUtils.getInstance().getString("USER_NOT_FOUND"), LocaleUtils.getInstance().getString("USER_NOT_FOUND_EXPLANATION"));
        }
    }

    public void requestChangeLanguage(String username, String selectedItem) {
        // TODO
    }
}
