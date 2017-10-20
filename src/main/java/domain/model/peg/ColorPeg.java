package domain.model.peg;

public class ColorPeg extends Peg {
    private int mColor;

    public ColorPeg(int color) {
        mColor = color;
    }

    public int getColor() {
        return mColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColorPeg colorPeg = (ColorPeg) o;

        return mColor == colorPeg.mColor;
    }
}
