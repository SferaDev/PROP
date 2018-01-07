package presentation.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import presentation.utils.LocaleUtils;

public class ColorInput extends VBox {
    private final ColorRow colorRow;

    /**
     * Creates a colorInput with th given parameters.
     *
     * @param title  the title of the label in the colorInput.
     * @param pegs   the number of pegs.
     * @param colors the number of colors.
     */
    public ColorInput(String title, int pegs, int colors) {
        setAlignment(Pos.CENTER);
        setSpacing(20);

        Label titleLabel = new Label(title);
        titleLabel.setWrapText(true);
        titleLabel.setTextAlignment(TextAlignment.CENTER);
        titleLabel.setFont(new Font(18));
        titleLabel.setTextFill(Color.WHITE);
        getChildren().add(titleLabel);

        StringBuilder row = new StringBuilder();
        for (int i = 0; i < pegs; ++i) row.append("1 ");
        colorRow = new ColorRow(row.toString().trim());
        colorRow.setSelectionActionListener(colors);
        setMargin(colorRow, new Insets(0, 30, 0, 30));
        getChildren().add(colorRow);

        RaisedButton button = new RaisedButton(LocaleUtils.getInstance().getString("SEND"));
        button.setOnMouseClicked(event -> {
            synchronized (this) {
                notify();
            }
        });
        getChildren().add(button);
    }

    /**
     * Gets the colorRow of a colorInput.
     *
     * @return a colorRow
     */
    public ColorRow getResult() {
        return colorRow;
    }
}
