package presentation.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ControlRow extends HBox {
    /**
     * Creates a control row.
     *
     * @param blacks is the number of blacks of the row.
     * @param whites is the number of whites of the row.
     */
    public ControlRow(int blacks, int whites) {
        GridPane.setMargin(this, new Insets(10, 10, 10, 10));
        setAlignment(Pos.CENTER);
        setSpacing(10);

        ControlPeg blackPeg = new ControlPeg(ControlPeg.Type.black);
        blackPeg.setText(String.valueOf(blacks));
        getChildren().add(blackPeg);

        ControlPeg whitePeg = new ControlPeg(ControlPeg.Type.white);
        whitePeg.setText(String.valueOf(whites));
        getChildren().add(whitePeg);
    }
}
