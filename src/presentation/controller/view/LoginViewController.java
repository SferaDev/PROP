package presentation.controller.view;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import presentation.controller.LocaleController;
import presentation.view.components.RaisedButton;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {
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

    private LoginListener listener;

    @FXML
    public void initialize(URL url, ResourceBundle bundle) {
        title.setText(LocaleController.getInstance().getString("WELCOME"));
        setUpImageViews();
        setUpTextFields();
        setUpButtons();
    }

    public void setListener(LoginListener listener) {
        this.listener = listener;
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
        usernameField.setPromptText(LocaleController.getInstance().getString("USERNAME"));
        usernameField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER) && !passwordField.getText().equals(""))
                listener.onLoginButton(usernameField.getText(), passwordField.getText());
            else if (event.getCode().equals(KeyCode.ENTER)) passwordField.requestFocus();
        });

        passwordField.setPromptText(LocaleController.getInstance().getString("PASSWORD"));
        passwordField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER))
                listener.onLoginButton(usernameField.getText(), passwordField.getText());
        });
    }

    private void setUpButtons() {
        RaisedButton loginButton = new RaisedButton(LocaleController.getInstance().getString("LOGIN"));
        loginButton.setOnMouseClicked(event ->
                listener.onLoginButton(usernameField.getText(), passwordField.getText()));
        loginButton.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER))
                listener.onLoginButton(usernameField.getText(), passwordField.getText());
        });
        buttonPlaceholder.getChildren().add(loginButton);

        RaisedButton registerButton = new RaisedButton(LocaleController.getInstance().getString("REGISTER"));
        registerButton.setOnMouseClicked(event ->
                listener.onRegisterButton(usernameField.getText(), passwordField.getText()));
        registerButton.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER))
                listener.onRegisterButton(usernameField.getText(), passwordField.getText());
        });
        buttonPlaceholder.getChildren().add(registerButton);
    }

    public interface LoginListener {
        void onLoginButton(String username, String password);

        void onRegisterButton(String username, String password);
    }
}