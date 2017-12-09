package presentation.visual.view;

public class ControlPeg extends ViewItem {
    public ControlPeg(Type type) {
        super(ControlPeg.class.getSimpleName());

        getStyleClass().add("control-peg-" + type);
    }

    public enum Type {
        black,
        white,
        none
    }
}
