package domain.model.peg;

public class ControlPeg extends Peg {
    public enum Type {
        BLACK, WHITE, EMPTY
    }

    private Type mType;

    public ControlPeg(Type type) {
        mType = type;
    }

    public Type getType() {
        return mType;
    }
}
