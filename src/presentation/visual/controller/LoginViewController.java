package presentation.visual.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import presentation.visual.view.RaisedButton;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {
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

    private LoginListener listener;

    @FXML
    public void initialize(URL url, ResourceBundle bundle) {
        // ImageViews
        backgroundImageView.setImage(new Image("/resources/img/anim_login.gif"));
        backgroundImageView.fitWidthProperty().bind(logoPlaceholder.widthProperty());
        backgroundImageView.fitHeightProperty().bind(logoPlaceholder.heightProperty());
        logoImageView.setImage(new Image("/resources/img/logo.png"));
        logoImageView.fitWidthProperty().bind(logoPlaceholder.widthProperty());
        logoImageView.fitHeightProperty().bind(logoPlaceholder.heightProperty());

        // Text Fields
        usernameField.setPromptText("Username"); // TODO: Strings
        passwordField.setPromptText("Password"); // TODO: Strings

        // Buttons
        RaisedButton loginButton = new RaisedButton("Login"); // TODO: Strings
        RaisedButton registerButton = new RaisedButton("Register"); // TODO: Strings
        loginButton.setOnMouseClicked(event ->
                listener.onLoginButton(usernameField.getText(), passwordField.getText()));
        registerButton.setOnMouseClicked(event ->
                listener.onRegisterButton(usernameField.getText(), passwordField.getText()));
        buttonPlaceholder.getChildren().add(loginButton);
        buttonPlaceholder.getChildren().add(registerButton);
    }

    public void setListener(LoginListener listener) {
        this.listener = listener;
    }

    public interface LoginListener {
        void onLoginButton(String username, String password);

        void onRegisterButton(String username, String password);
    }
}
