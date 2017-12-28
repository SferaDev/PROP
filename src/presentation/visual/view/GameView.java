package presentation.visual.view;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

public class GameView extends GridPane {
    private GridPane informationPane = new GridPane();
    private VBox informationPlaceholder = new VBox();
    private StackPane controlPane = new StackPane();
    private ScrollPane scrollPane;

    public GameView(BoardPane boardPane) {
        scrollPane = new ScrollPane(boardPane);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");

        informationPane.getColumnConstraints().addAll(createColumnConstraint(100));
        informationPane.getRowConstraints().addAll(createRowConstraint(50), createRowConstraint(50));

        getColumnConstraints().addAll(createColumnConstraint(30), createColumnConstraint(70));
        getRowConstraints().addAll(createRowConstraint(100));

        informationPane.add(informationPlaceholder, 0, 0);
        informationPane.add(controlPane, 0, 1);
        add(informationPane, 0, 0);
        add(scrollPane, 1, 0);
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
