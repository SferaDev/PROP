package presentation.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import presentation.controller.LocaleController;

public class ColorInput extends VBox {
    private final ColorRow colorRow;

    public ColorInput(String title, int pegs, int colors) {
        setAlignment(Pos.CENTER);
        setSpacing(20);

        Label titleLabel = new Label(title);
        titleLabel.setFont(new Font(18));
        titleLabel.setTextFill(Color.WHITE);
        getChildren().add(titleLabel);

        StringBuilder row = new StringBuilder();
        for (int i = 0; i < pegs; ++i) row.append("1 ");
        colorRow = new ColorRow(row.toString().trim());
        colorRow.setSelectionActionListener(colors);
        setMargin(colorRow, new Insets(0, 30, 0, 30));
        getChildren().add(colorRow);

        RaisedButton button = new RaisedButton(LocaleController.getInstance().getString("SEND"));
        button.setOnMouseClicked(event -> {
            synchronized (this) {
                notify();
            }
        });
        getChildren().add(button);
    }

    public ColorRow getResult() {
        return colorRow;
    }
}
