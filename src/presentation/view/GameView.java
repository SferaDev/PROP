package presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import presentation.controller.PresentationController;
import presentation.utils.ComponentUtils;
import presentation.utils.LocaleUtils;
import presentation.view.components.ColorRow;
import presentation.view.components.RaisedButton;

/**
 * The Game View
 *
 * @author Alexis Rico Carreto
 */
public class GameView extends GridPane {
    private final StackPane controlPane = new StackPane();
    private final ScrollPane scrollPane;

    private ColorRow correctGuess;

    /**
     * Loads the GameView to nebula.
     *
     * @param boardPane is where GameView will be loaded.
     * @param role      is the role of the user in the game.
     * @param computer  is the computer who the user plays against.
     * @param pegs      is the number of pegs of the game.
     * @param colors    is the number of colors of the game.
     */
    public GameView(BoardPane boardPane, String role, String computer, int pegs, int colors) {
        scrollPane = new ScrollPane(boardPane);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");
        boardPane.heightProperty().addListener(((observable, oldValue, newValue) -> scrollPane.setVvalue(1.0)));

        GridPane informationPane = new GridPane();
        informationPane.getColumnConstraints().addAll(ComponentUtils.createColumnConstraint(100));
        informationPane.getRowConstraints().addAll(ComponentUtils.createRowConstraint(50), ComponentUtils.createRowConstraint(50));

        getColumnConstraints().addAll(ComponentUtils.createColumnConstraint(30), ComponentUtils.createColumnConstraint(70));
        getRowConstraints().addAll(ComponentUtils.createRowConstraint(100));

        VBox informationPlaceholder = new VBox();
        informationPlaceholder.setAlignment(Pos.CENTER);
        informationPlaceholder.setSpacing(10);
        if (role.equals("Maker")) {
            informationPlaceholder.getChildren().add(createNewLabel(computer));
        } else {
            informationPlaceholder.getChildren().add(createNewLabel(LocaleUtils.getInstance().getString("ROLE") + ": " + role));
        }
        informationPlaceholder.getChildren().add(createNewLabel(LocaleUtils.getInstance().getString("PEGS") + ": " + pegs + " " + LocaleUtils.getInstance().getString("COLORS") + ": " + colors));

        HBox buttonBoxHelp = new HBox();
        buttonBoxHelp.setAlignment(Pos.CENTER);
        buttonBoxHelp.setSpacing(5);
        VBox.setMargin(buttonBoxHelp, new Insets(5));

        RaisedButton correctButton = new RaisedButton(LocaleUtils.getInstance().getString("CORRECT"));
        correctButton.setOnMouseClicked(event -> ComponentUtils.showCustomDialog(LocaleUtils.getInstance().getString("CORRECT_GUESS"), correctGuess));
        if (role.equals("Maker")) buttonBoxHelp.getChildren().add(correctButton);

        RaisedButton helpButton = new RaisedButton(LocaleUtils.getInstance().getString("HELP"));
        helpButton.setOnMouseClicked(event -> PresentationController.getInstance().requestHelpCurrentGame());
        buttonBoxHelp.getChildren().add(helpButton);

        HBox buttonBoxQuit = new HBox();
        buttonBoxQuit.setAlignment(Pos.CENTER);
        buttonBoxQuit.setSpacing(5);
        VBox.setMargin(buttonBoxQuit, new Insets(5));

        RaisedButton saveButton = new RaisedButton(LocaleUtils.getInstance().getString("SAVE"));
        saveButton.setOnMouseClicked(event -> PresentationController.getInstance().requestSaveCurrentGame());
        buttonBoxQuit.getChildren().add(saveButton);

        RaisedButton quitButton = new RaisedButton(LocaleUtils.getInstance().getString("QUIT"));
        quitButton.setOnMouseClicked(event -> PresentationController.getInstance().requestQuitCurrentGame());
        buttonBoxQuit.getChildren().add(quitButton);

        informationPlaceholder.getChildren().addAll(buttonBoxHelp, buttonBoxQuit);

        setMargin(informationPlaceholder, new Insets(15, 10, 0, 10));

        informationPane.add(informationPlaceholder, 0, 0);
        GridPane.setMargin(controlPane, new Insets(30, 10, 10, 10));
        informationPane.add(controlPane, 0, 1);
        add(informationPane, 0, 0);
        add(scrollPane, 1, 0);
    }

    private Node createNewLabel(String s) {
        Label label = new Label(s);
        label.setFont(new Font(15));
        label.setTextFill(Color.WHITE);
        VBox.setMargin(label, new Insets(10));
        return label;
    }

    /**
     * Adds a node to the controlPane.
     *
     * @param pane is the node that will be added.
     */
    public void addActionPane(Node pane) {
        controlPane.getChildren().clear();
        controlPane.getChildren().add(pane);
    }

    /**
     * Clears the actionPane.
     */
    public void removeActionPane() {
        controlPane.getChildren().clear();
    }

    /**
     * Adds the correct guess.
     *
     * @param correctRow is the corrct guess.
     */
    public void addCorrectRow(ColorRow correctRow) {
        correctGuess = correctRow;
    }
}
