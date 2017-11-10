package domain.model.row;

import java.util.ArrayList;

/**
 * The type Color row.
 */
public class ColorRow extends ArrayList<ColorRow.ColorPeg> {
    /**
     * Instantiates a new Color row.
     */
    public ColorRow() {
        super();
    }

    /**
     * Instantiates a new Color row.
     *
     * @param size the size
     */
    public ColorRow(int size) {
        super(size);
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

    /**
     * The type Color peg.
     */
    public static class ColorPeg {
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
