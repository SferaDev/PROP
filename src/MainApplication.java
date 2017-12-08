import com.jfoenix.controls.JFXTreeTableView;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @FXML private ImageView logoImageView;
    @FXML private GridPane mainContent;
    @FXML private BorderPane logoPlaceholder;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Instantiate the FXMLLoader
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/layout/Login.fxml"));
        fxmlLoader.setController(this);

        // Create Main Window
        Parent root = fxmlLoader.load();

        // Set up MainContent
        logoImageView.setImage(new Image("/resources/img/logo.png"));
        logoImageView.fitWidthProperty().bind(logoPlaceholder.widthProperty());
        logoImageView.fitHeightProperty().bind(logoPlaceholder.heightProperty());

        // Create Scene and show it
        Scene scene = new Scene(root);
        stage.setTitle("Mastermind: Login");
        stage.setMinHeight(mainContent.getPrefHeight());
        stage.setMinWidth(mainContent.getPrefWidth());
        stage.setScene(scene);
        stage.show();
    }
}
