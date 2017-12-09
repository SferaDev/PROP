package presentation.visual.view;

public class ColorRow extends ViewItem {
    public ColorRow(ColorPeg... pegs) {
        super(ColorRow.class.getSimpleName());

        for (ColorPeg peg : pegs) {
            getChildren().add(peg);
        }
    }
}
