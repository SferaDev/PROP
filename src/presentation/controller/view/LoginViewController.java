package presentation.controller.view;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import domain.model.exceptions.UserAlreadyExistsException;
import domain.model.exceptions.UserNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentation.controller.PresentationController;
import presentation.utils.ComponentUtils;
import presentation.utils.LocaleUtils;
import presentation.view.components.RaisedButton;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The login View Controller.
 *
 * @author Alexis Rico Carreto
 */
public class LoginViewController implements Initializable {
    /**
     * The title of the view
     */
    @FXML
    public Label title;
    @FXML
    private ImageView logoImageView;
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private StackPane logoPlaceholder;
    @FXML
    private HBox buttonPlaceholder;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;

    /**
     * Inicialize the LoginView
     *
     * @param url    is the url where to find Login.fxml
     * @param bundle is the recoure bundle
     */
    @FXML
    public void initialize(URL url, ResourceBundle bundle) {
        title.setText(LocaleUtils.getInstance().getString("WELCOME"));
        setUpImageViews();
        setUpTextFields();
        setUpButtons();
    }

    private void setUpImageViews() {
        backgroundImageView.setImage(new Image("/resources/img/anim_login.gif"));
        backgroundImageView.fitWidthProperty().bind(logoPlaceholder.widthProperty());
        backgroundImageView.fitHeightProperty().bind(logoPlaceholder.heightProperty());

        logoImageView.setImage(new Image("/resources/img/logo.png"));
        logoImageView.fitWidthProperty().bind(logoPlaceholder.widthProperty());
        logoImageView.fitHeightProperty().bind(logoPlaceholder.heightProperty());
    }

    private void setUpTextFields() {
        usernameField.setPromptText(LocaleUtils.getInstance().getString("USERNAME"));
        usernameField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER) && passwordField.getText().equals(""))
                passwordField.requestFocus();
        });

        passwordField.setPromptText(LocaleUtils.getInstance().getString("PASSWORD"));
        passwordField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER) && usernameField.getText().equals(""))
                passwordField.requestFocus();
        });
    }

    private void setUpButtons() {
        RaisedButton loginButton = new RaisedButton(LocaleUtils.getInstance().getString("LOGIN"));
        loginButton.setOnMouseClicked(event ->
                onLoginButton(usernameField.getText(), passwordField.getText()));
        buttonPlaceholder.getChildren().add(loginButton);

        RaisedButton registerButton = new RaisedButton(LocaleUtils.getInstance().getString("REGISTER"));
        registerButton.setOnMouseClicked(event ->
                onRegisterButton(usernameField.getText(), passwordField.getText()));
        buttonPlaceholder.getChildren().add(registerButton);
    }

    private void onLoginButton(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            ComponentUtils.showErrorDialog(LocaleUtils.getInstance().getString("INVALID_INPUT"),
                    LocaleUtils.getInstance().getString("TRY_AGAIN"));
        } else {
            try {
                boolean loginSuccessful = PresentationController.getInstance().requestLogin(username, password);
                if (loginSuccessful) {
                    Stage stage = (Stage) title.getScene().getWindow();
                    stage.close();
                    PresentationController.getInstance().launchNebulaForm();
                } else {
                    ComponentUtils.showErrorDialog(LocaleUtils.getInstance().getString("INVALID_PASSWORD"),
                            LocaleUtils.getInstance().getString("TRY_AGAIN"));
                }
            } catch (UserNotFoundException e) {
                ComponentUtils.showErrorDialog(LocaleUtils.getInstance().getString("USER_NOT_FOUND"),
                        LocaleUtils.getInstance().getString("USER_NOT_FOUND_EXPLANATION"));
            }
        }
    }

    private void onRegisterButton(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            ComponentUtils.showErrorDialog(LocaleUtils.getInstance().getString("INVALID_INPUT"),
                    LocaleUtils.getInstance().getString("TRY_AGAIN"));
        } else {
            try {
                PresentationController.getInstance().requestRegister(username, password, LocaleUtils.getInstance().getLanguage().name());
                onLoginButton(username, password);
            } catch (UserAlreadyExistsException e) {
                ComponentUtils.showErrorDialog(LocaleUtils.getInstance().getString("USER_EXISTS"),
                        LocaleUtils.getInstance().getString("CHANGE_USERNAME"));
            }
        }
    }
}
