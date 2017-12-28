package presentation.visual.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ControlRow extends HBox {
    public ControlRow(int size, int blacks, int whites) {
        GridPane.setMargin(this, new Insets(10, 10, 10, 10));
        setAlignment(Pos.CENTER);
        setSpacing(10);

        ControlPeg blackPeg = new ControlPeg(ControlPeg.Type.black);
        blackPeg.setText(String.valueOf(blacks));
        getChildren().add(blackPeg);

        ControlPeg whitePeg = new ControlPeg(ControlPeg.Type.white);
        whitePeg.setText(String.valueOf(whites));
        getChildren().add(whitePeg);

        ControlPeg nonePeg = new ControlPeg(ControlPeg.Type.none);
        nonePeg.setText(String.valueOf(size - blacks - whites));
        getChildren().add(nonePeg);
    }
}
