package presentation.controller;

import domain.controller.DomainController;
import domain.model.exceptions.FinishGameException;
import domain.model.exceptions.UserAlreadyExistsException;
import domain.model.exceptions.UserNotFoundException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import presentation.controller.receiver.VisualGameReceiver;
import presentation.controller.view.NebulaViewController;
import presentation.utils.ComponentUtils;
import presentation.utils.LocaleUtils;

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

    private DomainController domainController = DomainController.getInstance();

    private PresentationController() {
    }

    /**
     * Gets the instance of the PresentationController.
     *
     * @return the instance of the PresentationController.
     */
    public static PresentationController getInstance() {
        return mInstance;
    }

    /**
     * Launch the login view
     */
    public void launchLoginForm() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(LOGIN_FXML_PATH));
            Parent root = loader.load();
            ComponentUtils.buildScene(getClass(), stage, root, 700, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launch the nebula view
     */
    public void launchNebulaForm() {
        try {
            Stage stage = new Stage();
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
     *
     * @param username the username that will be registered
     * @param password the password that will be tested
     * @param language the language of the user
     * @throws UserAlreadyExistsException if the user already exists
     */
    public void requestRegister(String username, String password, String language) throws UserAlreadyExistsException {
        domainController.getUserController().createUser(username, password, language);
    }

    /**
     * Checks if the username and the password match
     *
     * @param username the username that will be registered
     * @param password the password that will be tested
     * @return Returns if the user and password match or not
     * @throws UserNotFoundException if the user is not found
     */
    public boolean requestLogin(String username, String password) throws UserNotFoundException {
        if (!domainController.getUserController().loginUser(username, password)) return false;
        mUsername = username;
        LocaleUtils.getInstance().setLanguage(requestUserLanguage());
        return true;
    }

    /**
     * Request to save the current game.
     */
    public void requestSaveCurrentGame() {
        try {
            domainController.getGameController().saveCurrentGame();
        } catch (FinishGameException e) {
            nebulaController.finishGame();
        }
    }

    /**
     * Request to exit the current game without saving.
     */
    public void requestQuitCurrentGame() {
        try {
            domainController.getGameController().stopCurrentGame();
        } catch (FinishGameException e) {
            nebulaController.finishGame();
        }
    }

    /**
     * Request a help in the current game.
     */
    public void requestHelpCurrentGame() {
        domainController.getGameController().provideHelp();
    }

    /**
     * Saves the game interface to domain controller.
     *
     * @param gameInterfaceReceiver is the Interface that will be saved.
     */
    public void setGameInterface(VisualGameReceiver gameInterfaceReceiver) {
        domainController.setGameInterface(gameInterfaceReceiver);
    }

    /**
     * Gets the nebula controller.
     *
     * @return the nebula controller.
     */
    public NebulaViewController getNebulaController() {
        return nebulaController;
    }

    public void requestStartGame(String computerName, String role, int pegs, int colors) {
        domainController.getGameController().startNewGame(getUsername(),
                computerName + "Computer", role, pegs, colors, -1);
    }

    /**
     * Gets the username
     *
     * @return the name of the username.
     */
    public String getUsername() {
        return mUsername;
    }

    /**
     * Request to change the password of the current username.
     *
     * @param oldPassword is the old password, that will be checked if matches with the current user.
     * @param newPassword is the new password that will be set.
     */
    public void requestPasswordChange(String oldPassword, String newPassword) {
        if (mUsername == null) return;
        try {
            if (PresentationController.getInstance().requestLogin(mUsername, oldPassword)) {
                if (oldPassword.equals(newPassword)) {
                    ComponentUtils.showWarningDialog(LocaleUtils.getInstance().getString("SAME_PASSWORD"),
                            LocaleUtils.getInstance().getString("CHOOSE_DIF_PASSWORD"));
                } else {
                    domainController.getUserController().changePassword(mUsername, newPassword);
                    ComponentUtils.showInformationDialog(LocaleUtils.getInstance().getString("PASSWORD_CHANGED"),
                            LocaleUtils.getInstance().getString("CHANGED_SUCCESSFULLY"));
                }
            } else {
                ComponentUtils.showErrorDialog(LocaleUtils.getInstance().getString("INVALID_PASSWORD"),
                        LocaleUtils.getInstance().getString("OLD_PASSWORD_INCORRECT"));
            }
        } catch (UserNotFoundException e) {
            ComponentUtils.showErrorDialog(LocaleUtils.getInstance().getString("USER_NOT_FOUND"),
                    LocaleUtils.getInstance().getString("USER_NOT_FOUND_EXPLANATION"));
        }
    }

    /**
     * Gets the language of the current user.
     *
     * @return a language.
     */
    private LocaleUtils.Language requestUserLanguage() {
        if (mUsername == null) return null;
        return LocaleUtils.Language.valueOf(domainController.getUserController().getUserLanguage(mUsername));
    }

    /**
     * Requaet to change the language of the current user.
     *
     * @param newLanguage is the new language that will be set.
     */
    public void requestChangeLanguage(LocaleUtils.Language newLanguage) {
        LocaleUtils.getInstance().setLanguage(newLanguage);
        if (mUsername != null)
            domainController.getUserController().changeLanguage(mUsername, newLanguage.name());
    }

    /**
     * Request all the saved games.
     *
     * @return all the saved games of the current user.
     */
    public ArrayList requestSavedGames() {
        if (mUsername == null) return null;
        return domainController.getGameController().getAllGames(mUsername);
    }

    /**
     * Request to start a saved game.
     *
     * @param game the name of the game that will be started.
     */
    public void requestStartSavedGame(String game) {
        domainController.getGameController().continueGame(game);
    }

    /**
     * Request delete current user
     */
    public void requestDeleteUser() {
        domainController.getUserController().deleteUser(mUsername);
        mUsername = null;
    }
}
