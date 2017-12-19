package presentation.visual.view;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentation.visual.view.components.RaisedButton;

import java.io.IOException;

public class LoginView {
    @FXML
    private ImageView logoImageView;
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private GridPane mainContent;
    @FXML
    private StackPane logoPlaceholder;
    @FXML
    private HBox buttonPlaceholder;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;

    private Stage stage;
    private Parent root;

    public LoginView(Stage stage, LoginListener listener) {
        this.stage = stage;
        inflateView();
        prepareComponents(listener);
        createScene();
    }

    private void inflateView() {
        try {
            // Instantiate the FXMLLoader
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/layout/Login.fxml"));
            fxmlLoader.setController(this);
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void prepareComponents(LoginListener listener) {
        // ImageViews
        backgroundImageView.setImage(new Image("/resources/img/anim_login.gif"));
        backgroundImageView.fitWidthProperty().bind(logoPlaceholder.widthProperty());
        backgroundImageView.fitHeightProperty().bind(logoPlaceholder.heightProperty());
        logoImageView.setImage(new Image("/resources/img/logo-2.png"));
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

    private void createScene() {
        Scene scene = new Scene(root);
        stage.setTitle("Mastermind");
        stage.setMinHeight(mainContent.getPrefHeight());
        stage.setMinWidth(mainContent.getPrefWidth());
        stage.setScene(scene);
        stage.show();
    }

    public interface LoginListener {
        void onLoginButton(String username, String password);

        void onRegisterButton(String username, String password);
    }
}
