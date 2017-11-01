package domain.model.row;

import java.util.ArrayList;

public class ColorRow extends ArrayList<ColorRow.ColorPeg> {
    public ColorRow() {
        super();
    }

    public ColorRow(int size) {
        super(size);
    }

    public ColorRow(ColorRow original) {
        super(original);
    }

    public static class ColorPeg {
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
}