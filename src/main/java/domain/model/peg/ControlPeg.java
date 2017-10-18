package domain.model.peg;

public class ControlPeg extends Peg {
    public enum TYPE {
        BLACK, WHITE, EMPTY
    }

    private TYPE mType;

    public ControlPeg(TYPE type) {
        mType = type;
    }

    public TYPE getType() {
        return mType;
    }
}
