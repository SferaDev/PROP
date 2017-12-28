package presentation.terminal.view;

import presentation.terminal.model.Board;
import presentation.terminal.model.Turn;
import presentation.terminal.utils.Constants;
import presentation.terminal.utils.TerminalMenuBuilder;

/**
 * The type Board view.
 *
 * @author Elena Alonso Gonzalez
 */
public class BoardView {
    /**
     * Print terminal.
     *
     * @param board the board
     */
    public void printTerminal(Board board) {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(board.getGameTitle());
        builder.addDescription(Constants.GAME_HELP_INGAMEHELP);
        builder.addDescription(Constants.GAME_HELP_SAVE);
        builder.addDescription(Constants.GAME_HELP_QUIT);
        if (board.getCorrectGuess() != null)
            builder.addDescription("\nCombinació correcte: " + board.getCorrectGuess());
        builder.addDescription("\nTorns:\n");
        for (Turn turn : board.getTurns()) {
            String black = "";
            String white = "";
            if (turn.getBlacks() != -1 && turn.getWhites() != -1) {
                black = String.valueOf(turn.getBlacks());
                white = String.valueOf(turn.getWhites());
            }
            builder.addDescription(String.format("%15.15s %16.16s %16.16s",
                    turn.getGuess(), "Negres: " + black, "Blanques: " + white));
        }
        builder.execute();
    }
}
