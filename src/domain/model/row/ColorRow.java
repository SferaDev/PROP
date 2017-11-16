package domain.model.row;

import java.util.ArrayList;

/**
 * The type Color row.
 *
 * @author Elena Alonso Gonzalez
 */
public class ColorRow extends ArrayList<ColorRow.ColorPeg> implements java.io.Serializable {
    /**
     * Instantiates a new Color row.
     */
    public ColorRow() {
        super();
    }

    /**
     * Instantiates a new Color row with its values
     *
     * @param values the numeric values of the pegs
     */
    public ColorRow(int... values) {
        for (int i : values) {
            add(new ColorPeg(i));
        }
    }

    /**
     * Instantiates a new Color row with another ColorRow values
     *
     * @param original the original combination we want to copy
     */
    public ColorRow(ColorRow original) {
        super(original);
    }

    /**
     * Checks if the input is valid
     *
     * @param input  is the input of the player
     * @param pegs   is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @return true if its valid, false otherwise
     */
    public static boolean isValid(ColorRow input, int pegs, int colors) {
        if (input.size() != pegs) return false;
        for (ColorPeg peg : input) {
            if (peg.mColor < 0 || peg.mColor > colors) return false;
        }
        return true;
    }

    /**
     * Converts the ColorRow to a string
     *
     * @return the ColorRow converted to string
     */
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
    public static class ColorPeg implements java.io.Serializable {
        private final int mColor;

        /**
         * Instantiates a new Color peg
         *
         * @param color the numeric value of the color
         */
        public ColorPeg(int color) {
            mColor = color;
        }

        /**
         * Gets color
         *
         * @return the color
         */
        public int getColor() {
            return mColor;
        }

        /**
         * Checks if two ColorRow are equals, have the same value
         *
         * @param o is the object we want to know if is equal
         * @return true if it is equal, false otherwise
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ColorPeg colorPeg = (ColorPeg) o;

            return mColor == colorPeg.mColor;
        }
    }
}
