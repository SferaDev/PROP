package presentation.controller.receiver;

import domain.model.exceptions.UserAlreadyExistsException;
import domain.model.exceptions.UserNotFoundException;
import javafx.stage.Stage;
import presentation.controller.LoginViewController;
import presentation.controller.PresentationController;
import presentation.utils.ComponentUtils;
import presentation.utils.LocaleUtils;

/**
 * The LoginActionReceiver
 *
 * @author Elena Alonso Gonzalez
 */
public class LoginActionReceiver implements LoginViewController.LoginListener {
    private final PresentationController presentationController = PresentationController.getInstance();
    private final Stage stage;

    /**
     * Recives the stage
     *
     * @param stage is the stage that will be ust to bild the login
     */
    public LoginActionReceiver(Stage stage) {
        this.stage = stage;
    }

    /**
     * Checks if the password and user are valid. Executed when loggin button pressed.
     *
     * @param username is the username given by the user.
     * @param password is the password given by the user.
     */
    @Override
    public void onLoginButton(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            ComponentUtils.showErrorDialog(LocaleUtils.getInstance().getString("INVALID_INPUT"), LocaleUtils.getInstance().getString("TRY_AGAIN"));
        } else {
            try {
                boolean loginSuccessful = presentationController.requestLogin(username, password);
                if (loginSuccessful) {
                    presentationController.closeWindow(stage);
                    presentationController.setUsername(username);
                    presentationController.launchNebulaForm(stage);
                } else {
                    ComponentUtils.showErrorDialog(LocaleUtils.getInstance().getString("INVALID_PASSWORD"), LocaleUtils.getInstance().getString("TRY_AGAIN"));
                }
            } catch (UserNotFoundException e) {
                ComponentUtils.showErrorDialog(LocaleUtils.getInstance().getString("USER_NOT_FOUND"), LocaleUtils.getInstance().getString("USER_NOT_FOUND_EXPLANATION"));
            }
        }
    }

    /**
     * Checks if the password and user are valid, and if so it registers the user. Executed when register button is pressed.
     *
     * @param username is the username given by the user.
     * @param password is the password given by the user.
     */
    @Override
    public void onRegisterButton(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            ComponentUtils.showErrorDialog(LocaleUtils.getInstance().getString("INVALID_INPUT"), LocaleUtils.getInstance().getString("TRY_AGAIN"));
        } else {
            try {
                presentationController.requestRegister(username, password);
                onLoginButton(username, password);
            } catch (UserAlreadyExistsException e) {
                ComponentUtils.showErrorDialog(LocaleUtils.getInstance().getString("USER_EXISTS"),
                        LocaleUtils.getInstance().getString("CHANGE_USERNAME"));
            }
        }
    }
}
