package presentation.visual.view;

public class ColorPeg extends ViewItem {
    public ColorPeg(String color) {
        super(ColorPeg.class.getSimpleName());

        setStyle("-fx-background-color: " + color);
    }
}
