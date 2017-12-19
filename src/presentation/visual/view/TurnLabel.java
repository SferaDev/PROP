package presentation.visual.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

class TurnLabel extends Label {
    TurnLabel(String text) {
        setAlignment(Pos.CENTER);
        setRotate(-90);
        setTextAlignment(TextAlignment.CENTER);
        setTextFill(Color.WHITE);
        setFont(Font.font(14));

        GridPane.setMargin(this, new Insets(2, 2, 2, 2));

        setText(text);
    }
}
