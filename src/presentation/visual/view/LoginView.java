package presentation.visual.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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

    private Stage stage;

    public LoginView(Stage stage) {
        this.stage = stage;
        inflateView();
    }

    public void inflateView() {
        try {
            // Instantiate the FXMLLoader
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/layout/Login.fxml"));
            fxmlLoader.setController(this);

            // Create Main Window
            Parent root = fxmlLoader.load();
            // Set up MainContent

            backgroundImageView.setImage(new Image("/resources/img/anim_login.gif"));
            backgroundImageView.fitWidthProperty().bind(logoPlaceholder.widthProperty());
            backgroundImageView.fitHeightProperty().bind(logoPlaceholder.heightProperty());
            logoImageView.setImage(new Image("/resources/img/logo-2.png"));
            logoImageView.fitWidthProperty().bind(logoPlaceholder.widthProperty());
            logoImageView.fitHeightProperty().bind(logoPlaceholder.heightProperty());
            logoPlaceholder.setBackground(new Background(new BackgroundFill(Color.web("#171715"), CornerRadii.EMPTY, Insets.EMPTY)));

            // Create Scene and show it
            Scene scene = new Scene(root);
            stage.setTitle("Mastermind");
            stage.setMinHeight(mainContent.getPrefHeight());
            stage.setMinWidth(mainContent.getPrefWidth());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
