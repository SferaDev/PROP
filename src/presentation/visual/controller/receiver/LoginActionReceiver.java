package presentation.visual.controller.receiver;

import domain.model.exceptions.UserAlreadyExistsException;
import domain.model.exceptions.UserNotFoundException;
import javafx.stage.Stage;
import presentation.visual.controller.LoginViewController;
import presentation.visual.controller.PresentationController;
import presentation.visual.utils.PresentationUtils;

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
        try {
            boolean loginSuccessful = presentationController.requestLogin(username, password);
            if (loginSuccessful) {
                presentationController.closeWindow(stage);
                presentationController.launchNebulaForm(stage);
            } else {
                PresentationUtils.showErrorDialog("Invalid password", "Please, try again!"); // TODO: Strings
            }
        } catch (UserNotFoundException e) {
            PresentationUtils.showErrorDialog("User not found",
                    "Looks like the user is not in our database!"); // TODO: Strings
        }
    }

    @Override
    public void onRegisterButton(String username, String password) {
        try {
            presentationController.requestRegister(username, password);
        } catch (UserAlreadyExistsException e) {
            PresentationUtils.showErrorDialog("User already exists",
                    "Please login with your password!"); // TODO: Strings
        }
    }
}
