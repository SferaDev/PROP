package domain.model.row;

/**
 * The type Control row.
 */
public class ControlRow implements java.io.Serializable {
    private int mBlacks = 0;
    private int mWhites = 0;

    /**
     * Instantiates a new Control row
     *
     * @param blacks the number of black in the control combination
     * @param whites the number of whites in the control combination
     */
    public ControlRow(int blacks, int whites) {
        mBlacks = blacks;
        mWhites = whites;
    }

    /**
     * Checks if two ControlRows are equals, have the same value
     *
     * @param o is the object we want to know if is equal
     * @return true if it is equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ControlRow controlRow = (ControlRow) o;

        return mBlacks == controlRow.getBlacks() && mWhites == controlRow.getWhites();
    }

    /**
     * Gets the number of blacks in the control combination
     *
     * @return the number of blacks in the control combination
     */
    public int getBlacks() {
        return mBlacks;
    }

    /**
     * Gets the number of whites in the control combination
     *
     * @return the number of whites in the control combination
     */
    public int getWhites() {
        return mWhites;
    }

}
