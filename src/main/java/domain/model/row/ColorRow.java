package domain.model.row;

import java.util.ArrayList;

/**
 * The type Color row.
 */
public class ColorRow extends ArrayList<ColorRow.ColorPeg> implements java.io.Serializable {
    /**
     * Instantiates a new Color row.
     */
    public ColorRow() {
        super();
    }

    /**
     * Instantiates a new Color row.
     *
     * @param values the values
     */
    public ColorRow(int... values) {
        for (int i : values) {
            add(new ColorPeg(i));
        }
    }

    /**
     * Instantiates a new Color row.
     *
     * @param original the original
     */
    public ColorRow(ColorRow original) {
        super(original);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (ColorPeg peg : this) {
            output.append(peg.getColor()).append(" ");
        }
        return output.toString();
    }

    public static boolean isValid(ColorRow input, int pegs, int colors) {
        if (input.size() != pegs) return false;
        for (ColorPeg peg : input) {
            if (peg.mColor < 0 || peg.mColor > colors) return false;
        }
        return true;
    }

    /**
     * The type Color peg.
     */
    public static class ColorPeg implements java.io.Serializable {
        private int mColor;

        /**
         * Instantiates a new Color peg.
         *
         * @param color the color
         */
        public ColorPeg(int color) {
            mColor = color;
        }

        /**
         * Gets color.
         *
         * @return the color
         */
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
