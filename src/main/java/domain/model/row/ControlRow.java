package domain.model.row;

public class ControlRow {
    private int mBlacks = 0;
    private int mWhites = 0;

    public ControlRow() {
        // Empty constructor
    }

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

    public void setBlacks(int blacks) {
        mBlacks = blacks;
    }

    public void setWhites(int whites) {
        mWhites = whites;
    }

    public int getBlacks() {
        return mBlacks;
    }

    public int getWhites() {
        return mWhites;
    }
}
