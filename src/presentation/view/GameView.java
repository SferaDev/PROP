package presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import presentation.controller.PresentationController;
import presentation.view.components.ColorRow;
import presentation.view.components.RaisedButton;

public class GameView extends GridPane {
    private StackPane controlPane = new StackPane();
    private StackPane correctGuess = new StackPane();
    private ScrollPane scrollPane;

    public GameView(BoardPane boardPane, String role, int pegs, int colors) {
        scrollPane = new ScrollPane(boardPane);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");

        GridPane informationPane = new GridPane();
        informationPane.getColumnConstraints().addAll(createColumnConstraint(100));
        informationPane.getRowConstraints().addAll(createRowConstraint(50), createRowConstraint(50));

        getColumnConstraints().addAll(createColumnConstraint(30), createColumnConstraint(70));
        getRowConstraints().addAll(createRowConstraint(100));

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

    public void scrollDown() {
        scrollPane.setVvalue(1.0);
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

    private ColumnConstraints createColumnConstraint(int percent) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(percent);
        columnConstraints.setHgrow(Priority.SOMETIMES);
        return columnConstraints;
    }

    private RowConstraints createRowConstraint(int percent) {
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(percent);
        rowConstraints.setVgrow(Priority.SOMETIMES);
        return rowConstraints;
    }
}
