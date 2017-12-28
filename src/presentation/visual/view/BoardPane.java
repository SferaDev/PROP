package presentation.visual.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import presentation.visual.view.components.ColorRow;
import presentation.visual.view.components.ControlRow;

public class BoardPane extends GridPane {
    private int posColorRow, posControlRow, size;

    public BoardPane(int size) {
        this.size = size;
        getStylesheets().add(getClass().getResource("/resources/css/Board.css").toExternalForm());
        getStyleClass().add("board-background");
        setAlignment(Pos.CENTER);
        posColorRow = posControlRow = 0;
    }

    public void addColorRow(ColorRow... rows) {
        for (ColorRow row : rows) {
            TurnLabel label = new TurnLabel("Turn " + (posColorRow + 1));
            label.setRotate(-90);
            label.setMinSize(70, 100);
            add(label, 0, posColorRow);
            add(row, 1, posColorRow);
            posColorRow++;
        }
    }

    public void addControlRow(ControlRow... rows) {
        for (ControlRow row : rows) {
            VBox box = new VBox();
            box.setSpacing(10);
            box.setAlignment(Pos.CENTER);
            box.getChildren().add(new TurnLabel("Control"));
            box.getChildren().add(row);
            box.setMinSize(170, 100);
            add(box, 2, posControlRow);
            posControlRow++;
        }
    }

    public int getSize() {
        return size;
    }

    private class TurnLabel extends Label {
        private TurnLabel(String text) {
            setAlignment(Pos.CENTER);
            setTextAlignment(TextAlignment.CENTER);
            setTextFill(Color.WHITE);
            setFont(Font.font(14));
            setText(text);
        }
    }
}
