package presentation.model;

import java.util.ArrayList;

public class Board {
    private final String gameTitle;
    private final ArrayList<Turn> mTurns = new ArrayList<>();

    public Board(String title) {
        gameTitle = title;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void addTurn(Turn turn) {
        if (mTurns.size() > 0) {
            Turn lastTurn = mTurns.get(mTurns.size() - 1);
            if (lastTurn.getBlacks() == -1 && lastTurn.getWhites() == -1)
                mTurns.remove(lastTurn);
        }
        mTurns.add(turn);
    }

    public ArrayList<Turn> getTurns() {
        return mTurns;
    }

    public void addControlLastTurn(int blacks, int whites) {
        addBlacksLastTurn(blacks);
        addWhitesLastTurn(whites);
    }

    public void addBlacksLastTurn(int result) {
        if (mTurns.size() == 0) return;
        mTurns.get(mTurns.size() - 1).setBlacks(result);
    }

    public void addWhitesLastTurn(int result) {
        if (mTurns.size() == 0) return;
        mTurns.get(mTurns.size() - 1).setWhites(result);
    }
}
