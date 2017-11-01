package domain.model.player.computer;

public class ControlScore {
    private ControlPair control;
    private int score;

    public ControlScore (ControlPair control, int score){
        this.control = new ControlPair(control.getFirst(), control.getSecond());
        this.score = score;
    }

    public void increasescore() {
        this.score = score + 1;
    }

    public ControlPair getFirst() {
        return this.control;
    }

    public int getSecond() {
        return this.score;
    }
}


