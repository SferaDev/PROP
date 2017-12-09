package presentation.visual.view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ColorRow extends HBox {
    public ColorRow(ColorPeg... pegs) {
        setAlignment(Pos.CENTER);
        setSpacing(10);

        GridPane.setMargin(this, new Insets(10, 10, 10, 10));

        for (ColorPeg peg : pegs) {
            getChildren().add(peg);
        }
    }
}
