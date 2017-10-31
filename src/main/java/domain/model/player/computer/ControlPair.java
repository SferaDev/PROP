package domain.model.player.computer;

public class ControlPair {
    private int blacks;
    private int whites;

    public ControlPair(int blacks, int whites){
        this.blacks = blacks;
        this.whites = whites;
    }


    public boolean equals(ControlPair cp) {
        if (this == cp) return true;
        if (cp == null || getClass() != cp.getClass()) return false;

        ControlPair pair = (ControlPair) cp;
        return getFirst() == pair.getFirst() && getSecond() == pair.getSecond();
    }

    public void increaseblacks() {
        this.blacks = blacks + 1;
    }

    public void increasewhites() {
        this.whites = whites + 1;
    }

    public void setBlacks(int blacks) {
        this.blacks = blacks;
    }

    public void setWhites(int whites) {
        this.whites = whites;
    }

    public int getFirst() {
        return this.blacks;
    }

    public int getSecond() {
        return this.whites;
    }

}
