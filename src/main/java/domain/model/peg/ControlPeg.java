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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ControlPeg controlPeg = (ControlPeg) o;

        return mType == controlPeg.mType;
    }
}
