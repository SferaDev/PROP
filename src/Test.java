import domain.controller.DomainController;
import javafx.application.Application;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import presentation.visual.controller.PresentationController;
import presentation.visual.controller.receiver.GameInterfaceReceiver;
import presentation.visual.view.BoardPane;
import presentation.visual.view.components.ColorPeg;
import presentation.visual.view.components.ColorRow;
import presentation.visual.view.components.ControlRow;

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
    public void start(Stage stage) {
        BoardPane boardPane = new BoardPane(5);
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

        // Create Scene and show it
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(boardPane);
        scrollPane.setVvalue(1.0); // Scroll

        //Scene scene = new Scene(new GameView(5));
        //stage.setTitle("Mastermind");
        //stage.getIcons().add(new Image(getClass().getResource("/resources/img/ic_launcher.png").toExternalForm()));
        //stage.setScene(scene);
        //stage.show();

        //PresentationController.getInstance().launchLoginForm(stage);
        PresentationController.getInstance().launchNebulaForm(stage);

        PresentationController.getInstance().getNebulaController().startGame(10);
        PresentationController.getInstance().setGameInterface(new GameInterfaceReceiver());
        new Thread(() -> DomainController.getInstance().getGameController().startNewGame("alexis", "FiveGuessComputer", "MAKER", 5, 6, 12)).start();
    }
}