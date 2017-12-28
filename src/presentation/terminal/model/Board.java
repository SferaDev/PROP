package presentation.terminal.model;

import java.util.ArrayList;

/**
 * The type Board.
 *
 * @author Elena Alonso Gonzalez
 */
public class Board {
    private final String gameTitle;
    private final ArrayList<Turn> mTurns = new ArrayList<>();
    private Turn mCorrectGuess = null;

    /**
     * Instantiates a new Board.
     *
     * @param title the title
     */
    public Board(String title) {
        gameTitle = title;
    }

    /**
     * Gets game title.
     *
     * @return the game title
     */
    public String getGameTitle() {
        return gameTitle;
    }

    /**
     * Add turn.
     *
     * @param turn the turn
     */
    public void addTurn(Turn turn) {
        if (mTurns.size() > 0) {
            Turn lastTurn = mTurns.get(mTurns.size() - 1);
            if (lastTurn.getBlacks() == -1 && lastTurn.getWhites() == -1)
                mTurns.remove(lastTurn);
        }
        mTurns.add(turn);
    }

    /**
     * Add correct guess.
     *
     * @param turn the turn
     */
    public void setCorrectGuess(Turn turn) {
        mCorrectGuess = turn;
    }

    /**
     * Add correct guess.
     */
    public Turn getCorrectGuess() {
        return mCorrectGuess;
    }

    /**
     * Gets turns.
     *
     * @return the turns
     */
    public ArrayList<Turn> getTurns() {
        return mTurns;
    }

    /**
     * Add control last turn.
     *
     * @param blacks the blacks
     * @param whites the whites
     */
    public void addControlLastTurn(int blacks, int whites) {
        addBlacksLastTurn(blacks);
        addWhitesLastTurn(whites);
    }

    /**
     * Add blacks last turn.
     *
     * @param result the result
     */
    private void addBlacksLastTurn(int result) {
        if (mTurns.size() == 0) return;
        mTurns.get(mTurns.size() - 1).setBlacks(result);
    }

    /**
     * Add whites last turn.
     *
     * @param result the result
     */
    private void addWhitesLastTurn(int result) {
        if (mTurns.size() == 0) return;
        mTurns.get(mTurns.size() - 1).setWhites(result);
    }
}
