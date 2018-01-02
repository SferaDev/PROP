package presentation.controller.receiver;

import domain.model.exceptions.UserAlreadyExistsException;
import domain.model.exceptions.UserNotFoundException;
import javafx.stage.Stage;
import presentation.controller.LoginViewController;
import presentation.controller.PresentationController;
import presentation.utils.ComponentUtils;

/**
 * The LoginActionReceiver
 *
 * @author Elena Alonso Gonzalez
 */
public class LoginActionReceiver implements LoginViewController.LoginListener {
    private PresentationController presentationController = PresentationController.getInstance();
    private Stage stage;

    public LoginActionReceiver(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void onLoginButton(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            ComponentUtils.showErrorDialog("Invalid input", "Please, try again"); // TODO: Strings
        } else {
            try {
                boolean loginSuccessful = presentationController.requestLogin(username, password);
                if (loginSuccessful) {
                    presentationController.closeWindow(stage);
                    presentationController.setUsername(username);
                    presentationController.launchNebulaForm(stage);
                } else {
                    ComponentUtils.showErrorDialog("Invalid password", "Please, try again!"); // TODO: Strings
                }
            } catch (UserNotFoundException e) {
                ComponentUtils.showErrorDialog("User not found", "Looks like the user is not in our database!"); // TODO: Strings
            }
        }
    }

    @Override
    public void onRegisterButton(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            ComponentUtils.showErrorDialog("Invalid input", "Please, try again"); // TODO: Strings
        } else {
            try {
                presentationController.requestRegister(username, password);
                onLoginButton(username, password);
            } catch (UserAlreadyExistsException e) {
                ComponentUtils.showErrorDialog("User already exists",
                        "Please login with your password!"); // TODO: Strings
            }
        }
    }
}
