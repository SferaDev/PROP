package domain.model.row;

/**
 * The type Control row.
 */
public class ControlRow implements java.io.Serializable {
    private int mBlacks = 0;
    private int mWhites = 0;

    /**
     * Instantiates a new Control row.
     *
     * @param blacks the blacks
     * @param whites the whites
     */
    public ControlRow(int blacks, int whites) {
        mBlacks = blacks;
        mWhites = whites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ControlRow controlRow = (ControlRow) o;

        return mBlacks == controlRow.getBlacks() && mWhites == controlRow.getWhites();
    }

    /**
     * Gets blacks.
     *
     * @return the blacks
     */
    public int getBlacks() {
        return mBlacks;
    }

    /**
     * Sets blacks.
     *
     * @param blacks the blacks
     */
    public void setBlacks(int blacks) {
        mBlacks = blacks;
    }

    /**
     * Gets whites.
     *
     * @return the whites
     */
    public int getWhites() {
        return mWhites;
    }

    /**
     * Sets whites.
     *
     * @param whites the whites
     */
    public void setWhites(int whites) {
        mWhites = whites;
    }
}
