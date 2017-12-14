import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import presentation.visual.view.*;

import java.io.IOException;
import java.util.ArrayList;


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
        BoardPane boardPane = new BoardPane();
         boardPane.addColorRow(
         new ColorRow(
         new ColorPeg("#FF5722"),
         new ColorPeg("#795548"),
         new ColorPeg("#8BC34A")),
         new ColorRow(
         new ColorPeg("#8BC34A"),
         new ColorPeg("#795548"),
         new ColorPeg("#8BC34A"),
         new ColorPeg("#FF5722")),
         new ColorRow(
         new ColorPeg("#FF5722"),
         new ColorPeg("#8BC34A"),
         new ColorPeg("#009688"),
         new ColorPeg("#8BC34A"),
         new ColorPeg("#009688")));

         boardPane.addControlRow(
         new ControlRow(3, 1, 0),
         new ControlRow(4, 1, 1),
         new ControlRow(5, 3, 1)
         );

        ArrayList<String> colors = new ArrayList<>();
        colors.add("BLUE");
        colors.add("YELLOW");
        colors.add("RED");
        SelectionPeg selectionPeg = new SelectionPeg(colors);

        // Create Scene and show it
        Scene scene = new Scene(boardPane);
        stage.setTitle("Mastermind");
        stage.getIcons().add(new Image(getClass().getResource("/resources/img/logo-small.png").toExternalForm()));
        stage.setScene(scene);
        stage.show();

    }
}