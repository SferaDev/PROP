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
import presentation.controller.view.LoginViewController;
import presentation.controller.view.NebulaViewController;
import presentation.utils.ComponentUtils;

import java.io.IOException;
import java.util.ArrayList;

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
            ComponentUtils.buildScene(getClass(), stage, root, 700, 400);
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

    public void requestRegister(String username, String password, String language) throws UserAlreadyExistsException {
        DomainController.getInstance().getUserController().createUser(username, password, language);
    }

    public boolean requestLogin(String username, String password) throws UserNotFoundException {
        if (!DomainController.getInstance().getUserController().loginUser(username, password)) return false;
        mUsername = username;
        LocaleController.getInstance().setLanguage(requestUserLanguage());
        return true;
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

    public void requestPasswordChange(String oldPassword, String newPassword) {
        if (mUsername == null) return;
        try {
            if (PresentationController.getInstance().requestLogin(mUsername, oldPassword)) {
                if (oldPassword.equals(newPassword)) {
                    ComponentUtils.showWarningDialog(LocaleController.getInstance().getString("SAME_PASSWORD"), LocaleController.getInstance().getString("CHOOSE_DIF_PASSWORD"));
                } else {
                    DomainController.getInstance().getUserController().changePassword(mUsername, newPassword);
                    ComponentUtils.showInformationDialog(LocaleController.getInstance().getString("PASSWORD_CHANGED"), LocaleController.getInstance().getString("CHANGED_SUCCESSFULLY"));
                }
            } else {
                ComponentUtils.showErrorDialog(LocaleController.getInstance().getString("INVALID_PASSWORD"), LocaleController.getInstance().getString("OLD_PASSWORD_INCORRECT"));
            }
        } catch (UserNotFoundException e) {
            ComponentUtils.showErrorDialog(LocaleController.getInstance().getString("USER_NOT_FOUND"), LocaleController.getInstance().getString("USER_NOT_FOUND_EXPLANATION"));
        }
    }

    private LocaleController.Language requestUserLanguage() {
        if (mUsername == null) return null;
        return LocaleController.Language.valueOf(DomainController.getInstance().getUserController().getUserLanguage(mUsername));
    }

    public void requestChangeLanguage(LocaleController.Language newLanguage) {
        LocaleController.getInstance().setLanguage(newLanguage);
        if (mUsername != null) DomainController.getInstance().getUserController().changeLanguage(mUsername, newLanguage.name());
    }

    public ArrayList requestSavedGames() {
        if (mUsername == null) return null;
        return DomainController.getInstance().getGameController().getAllGames(mUsername);
    }

    public void requestStartSavedGame(String game) {
        DomainController.getInstance().getGameController().continueGame(game);
    }
}
