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
import presentation.view.components.ColorRow;
import presentation.view.components.RaisedButton;

public class GameView extends GridPane {
    private StackPane controlPane = new StackPane();
    private StackPane correctGuess = new StackPane();
    private ScrollPane scrollPane;

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

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        RaisedButton saveButton = new RaisedButton("Save");
        saveButton.setOnMouseClicked(event -> PresentationController.getInstance().requestSaveCurrentGame());
        buttonBox.getChildren().add(saveButton);

        RaisedButton helpButton = new RaisedButton("Help");
        helpButton.setOnMouseClicked(event -> PresentationController.getInstance().requestHelpCurrentGame());
        buttonBox.getChildren().add(helpButton);

        VBox informationPlaceholder = new VBox();
        informationPlaceholder.setAlignment(Pos.CENTER);
        informationPlaceholder.setSpacing(10);
        informationPlaceholder.getChildren().add(createNewLabel("Role: " + role));
        informationPlaceholder.getChildren().add(createNewLabel("Pegs: " + pegs + " | Colors: " + colors));
        informationPlaceholder.getChildren().add(correctGuess);
        informationPlaceholder.getChildren().add(buttonBox);

        setMargin(informationPlaceholder, new Insets(15, 10, 0, 10));

        informationPane.add(informationPlaceholder, 0, 0);
        informationPane.add(controlPane, 0, 1);
        add(informationPane, 0, 0);
        add(scrollPane, 1, 0);
    }

    private Node createNewLabel(String s) {
        Label label = new Label(s);
        label.setFont(new Font(18));
        label.setTextFill(Color.WHITE);
        return label;
    }

    public void addActionPane(Node pane) {
        controlPane.getChildren().clear();
        controlPane.getChildren().add(pane);
    }

    public void removeActionPane() {
        controlPane.getChildren().clear();
    }

    public void addCorrectRow(ColorRow correctRow) {
        correctGuess.getChildren().clear();
        correctGuess.getChildren().add(correctRow);
    }
}
