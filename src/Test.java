import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.visual.view.BoardPane;
import presentation.visual.view.ColorPeg;
import presentation.visual.view.ColorRow;
import presentation.visual.view.ControlRow;

import java.io.IOException;


public class Test extends Application {

    private static final String FX_TEXT_FILL_WHITE = "-fx-text-fill:WHITE";
    private static final String ANIMATED_OPTION_BUTTON = "animated-option-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON = "animated-option-sub-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON2 = "animated-option-sub-button2";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Instantiate the FXMLLoader
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/layout/BoardPane.fxml"));
        fxmlLoader.setController(this);

        // Create Main Window
        Parent root = fxmlLoader.load();
        BoardPane boardPane = new BoardPane();
        boardPane.addColorRow(
                new ColorRow(
                    new ColorPeg("#FF5722"),
                    new ColorPeg("#795548"),
                    new ColorPeg("#8BC34A"),
                    new ColorPeg("#009688")),
                new ColorRow(
                        new ColorPeg("#8BC34A"),
                        new ColorPeg("#795548"),
                        new ColorPeg("#8BC34A"),
                        new ColorPeg("#FF5722")),
                new ColorRow(
                        new ColorPeg("#FF5722"),
                        new ColorPeg("#8BC34A"),
                        new ColorPeg("#009688"),
                        new ColorPeg("#009688")));

        boardPane.addControlRow(
                new ControlRow(4, 1, 0),
                new ControlRow(4, 1, 1),
                new ControlRow(4, 3, 1)
        );

        // Create Scene and show it
        Scene scene = new Scene(boardPane);
        stage.setTitle("Mastermind");
        stage.setScene(scene);
        stage.show();

    }
}