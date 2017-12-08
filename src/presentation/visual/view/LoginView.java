package presentation.visual.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView {
    @FXML private ImageView logoImageView;
    @FXML private GridPane mainContent;
    @FXML private BorderPane logoPlaceholder;

    private Stage stage;

    public LoginView(Stage stage) {
        this.stage = stage;
    }

    public void inflateView() {
        try {
            // Instantiate the FXMLLoader
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/layout/Login.fxml"));
            fxmlLoader.setController(this);

            // Create Main Window
            Parent root = fxmlLoader.load();
            // Set up MainContent
            boolean showElena = false;
            if (showElena) {
                logoPlaceholder.setBackground(new Background(new BackgroundImage(new Image("/resources/img/login_background.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
                logoPlaceholder.setStyle("-fx-opacity: 0.85");
            } else logoPlaceholder.setStyle("-fx-background-color: #0d47a1;");

            logoImageView.setImage(new Image("/resources/img/logo.png"));
            logoImageView.fitWidthProperty().bind(logoPlaceholder.widthProperty());
            logoImageView.fitHeightProperty().bind(logoPlaceholder.heightProperty());

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
