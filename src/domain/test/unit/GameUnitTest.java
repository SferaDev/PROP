package domain.test.unit;

import domain.model.Game;
import domain.model.player.Player;
import domain.model.player.UserPlayer;
import org.junit.Before;
import org.junit.Test;

/**
 * The type Game unit test.
 *
 * @author Oriol Borrell Roig
 */
public class GameUnitTest {
    private Game mGame;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        mGame = new Game(new UserPlayer("TestUser1", Player.Role.MAKER),
                new UserPlayer("TestUser2", Player.Role.BREAKER),
                new Game.GameInfo("TestUser2", Player.Role.BREAKER, 4, 4, 12));
    }

    /**
     * Start game.
     *
     * @throws Exception the exception
     */
    @Test
    public void startGame() throws Exception {
    }

    /**
     * Gets game status.
     *
     * @throws Exception the exception
     */
    @Test
    public void getGameStatus() throws Exception {
    }

    /**
     * Gets game title.
     *
     * @throws Exception the exception
     */
    @Test
    public void getGameTitle() throws Exception {
    }

    /**
     * Finish game.
     *
     * @throws Exception the exception
     */
    @Test
    public void finishGame() throws Exception {
    }

    /**
     * Provide help.
     *
     * @throws Exception the exception
     */
    @Test
    public void provideHelp() throws Exception {
    }

    /**
     * Prepare save.
     *
     * @throws Exception the exception
     */
    @Test
    public void prepareSave() throws Exception {
    }

    /**
     * Restore saved game.
     *
     * @throws Exception the exception
     */
    @Test
    public void restoreSavedGame() throws Exception {
    }

}