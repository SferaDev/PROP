import domain.controller.DomainController;
import javafx.application.Application;
import javafx.stage.Stage;
import presentation.visual.controller.PresentationController;
import presentation.visual.controller.receiver.GameInterfaceReceiver;


public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        PresentationController.getInstance().setGameInterface(new GameInterfaceReceiver());

        PresentationController.getInstance().launchNebulaForm(stage);

        // Move this to PresentationController
        int pegs = 9;
        PresentationController.getInstance().getNebulaController().startGame("Maker", pegs, 9);
        new Thread(() -> DomainController.getInstance().getGameController().startNewGame("alexis", "GeneticComputer", "Maker", pegs, 9, 12)).start();
    }
}