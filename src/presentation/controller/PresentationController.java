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

    /**
     * Gets the instance of the PresentationController.
     * @return the instance of the PresentationController.
     */
    public static PresentationController getInstance() {
        return mInstance;
    }

    /**
     * Launch the login view
     * @param stage the stage that we will use to built the scene
     */
    public void launchLoginForm(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(LOGIN_FXML_PATH));
            Parent root = loader.load();
            LoginViewController controller = loader.getController();
            controller.setListener(new LoginActionReceiver(stage));
            ComponentUtils.buildScene(getClass(), stage, root, 700, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launch the nebula view
     * @param stage the stage that we will use to built the scene
     */
    public void launchNebulaForm(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(NEBULA_FXML_PATH));
            Parent root = loader.load();
            nebulaController = loader.getController();
            ComponentUtils.buildScene(getClass(), stage, root, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Request the register of a user
     * @param username the username that will be registered
     * @param password the password that will be tested
     * @param language the language of the user
     * @throws UserAlreadyExistsException
     */
    public void requestRegister(String username, String password, String language) throws UserAlreadyExistsException {
        DomainController.getInstance().getUserController().createUser(username, password, language);
    }

    /**
     * Checks if the username and the password match
     * @param username the username that will be registered
     * @param password the password that will be tested
     * @return Returns if the user and password match or not
     * @throws UserNotFoundException
     */
    public boolean requestLogin(String username, String password) throws UserNotFoundException {
        if (!DomainController.getInstance().getUserController().loginUser(username, password)) return false;
        mUsername = username;
        LocaleController.getInstance().setLanguage(requestUserLanguage());
        return true;
    }

    /**
     * Request to save the current game.
     */
    public void requestSaveCurrentGame() {
        try {
            DomainController.getInstance().getGameController().saveCurrentGame();
        } catch (FinishGameException e) {
            nebulaController.finishGame();
        }
    }

    /**
     * Request to exit the current game without saving.
     */
    public void requestQuitCurrentGame() {
        try {
            DomainController.getInstance().getGameController().stopCurrentGame();
        } catch (FinishGameException e) {
            nebulaController.finishGame();
        }
    }

    /**
     * Request a help in the current game.
     */
    public void requestHelpCurrentGame() {
        DomainController.getInstance().getGameController().provideHelp();
    }

    /**
     * Closes the stage.
     * @param stage is the stage that will be closed.
     */
    public void closeWindow(Stage stage) {
        stage.close();
    }

    /**
     * Saves the game interface to domain controller.
     * @param gameInterfaceReceiver is the Interface that will be saved.
     */
    public void setGameInterface(GameInterfaceReceiver gameInterfaceReceiver) {
        DomainController.getInstance().setGameInterface(gameInterfaceReceiver);
    }

    /**
     * Gets the nebula controller.
     * @return the nebula controller.
     */
    public NebulaViewController getNebulaController() {
        return nebulaController;
    }

    public void requestStartGame(String computerName, String role, int pegs, int colors) {
        DomainController.getInstance().getGameController().startNewGame(getUsername(),
                computerName + "Computer", role, pegs, colors, -1);
    }

    /**
     * Gets the username
     * @return the name of the username.
     */
    public String getUsername() {
        return mUsername;
    }

    /**
     * Request to change the password of the current username.
     * @param oldPassword is the old password, that will be checked if matches with the current user.
     * @param newPassword is the new password that will be set.
     */
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

    /**
     * Gets the language of the current user.
     * @return a language.
     */
    private LocaleController.Language requestUserLanguage() {
        if (mUsername == null) return null;
        return LocaleController.Language.valueOf(DomainController.getInstance().getUserController().getUserLanguage(mUsername));
    }

    /**
     * Requaet to change the language of the current user.
     * @param newLanguage is the new language that will be set.
     */
    public void requestChangeLanguage(LocaleController.Language newLanguage) {
        LocaleController.getInstance().setLanguage(newLanguage);
        if (mUsername != null)
            DomainController.getInstance().getUserController().changeLanguage(mUsername, newLanguage.name());
    }

    /**
     * Request all the saved games.
     * @return all the saved games of the current user.
     */
    public ArrayList requestSavedGames() {
        if (mUsername == null) return null;
        return DomainController.getInstance().getGameController().getAllGames(mUsername);
    }

    /**
     * Request to start a saved game.
     * @param game the name of the game that will be started.
     */
    public void requestStartSavedGame(String game) {
        DomainController.getInstance().getGameController().continueGame(game);
    }
}
