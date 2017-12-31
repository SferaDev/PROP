package domain.test.unit;

import domain.model.Game;
import domain.model.player.Player;
import domain.model.player.UserPlayer;
import domain.model.player.computer.DummyComputer;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * The type Game unit test.
 *
 * @author Oriol Borrell Roig
 */
public class GameUnitTest {
    private Game mGame;
    private Game.GameInfo gameInfo;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        gameInfo = new Game.GameInfo("TestUser2", Player.Role.Breaker, 4, 4, 12);
        mGame = new Game(new UserPlayer("TestUser1", Player.Role.Maker),
                new DummyComputer(Player.Role.Breaker),
                gameInfo);
    }

    /**
     * Start game.
     *
     * @throws Exception the exception
     */
    @Test
    public void startGame() throws Exception {
        Method method = Game.class.getDeclaredMethod("actionScore");
        method.setAccessible(true);
        method.invoke(mGame);
        //TerminalUtils.getInstance().printLine(mGame.getGameStatus());
        assertEquals(mGame.getGameStatus(), Game.Status.CORRECT.toString());
    }

    /**
     * Gets game status.
     *
     * @throws Exception the exception
     */
    @Test
    public void getGameStatus() {
        assertEquals(mGame.getGameStatus(), Game.Status.START.toString());
    }

    /**
     * Gets game title.
     *
     * @throws Exception the exception
     */
    @Test
    public void getGameTitle() throws Exception {
        Field field = Game.GameInfo.class.getDeclaredField("mStart");
        field.setAccessible(true);
        //TerminalUtils.getInstance().printLine(field.get(gameInfo).toString());
        String sStart = field.get(gameInfo).toString();

        Field field1 = Game.GameInfo.class.getDeclaredField("mUser");
        field1.setAccessible(true);
        //TerminalUtils.getInstance().printLine(field.get(gameInfo).toString());
        String sUser = field1.get(gameInfo).toString();

        Field field2 = Game.GameInfo.class.getDeclaredField("mRole");
        field2.setAccessible(true);
        //TerminalUtils.getInstance().printLine(field.get(gameInfo).toString());
        String sRole = field2.get(gameInfo).toString();

        Field field3 = Game.GameInfo.class.getDeclaredField("mPegs");
        field3.setAccessible(true);
        //TerminalUtils.getInstance().printLine(field.get(gameInfo).toString());
        String sPegs = field3.get(gameInfo).toString();

        Field field4 = Game.GameInfo.class.getDeclaredField("mColors");
        field4.setAccessible(true);
        //TerminalUtils.getInstance().printLine(field.get(gameInfo).toString());
        String sColors = field4.get(gameInfo).toString();

        assertEquals((sUser + "-" + sRole + "-" + sPegs + "-" + sColors + "-" + sStart).replace(":", "_"),
                mGame.getGameTitle());
    }

    /**
     * Finish game.
     *
     * @throws Exception the exception
     */
    @Test
    public void finishGame() {
        mGame.finishGame();
        assertEquals(mGame.getGameStatus(), Game.Status.FINISHED.toString());
    }

    /**
     * Prepare save.
     *
     * @throws Exception the exception
     */
    @Test
    public void prepareSave() throws Exception {
        Field field1 = Game.GameInfo.class.getDeclaredField("mTotalTime");
        field1.setAccessible(true);
        //TerminalUtils.getInstance().printLine(field1.get(gameInfo).toString());
        String sTotalTime1 = field1.get(gameInfo).toString();

        Thread.sleep(3000);
        mGame.prepareSave();

        Field field2 = Game.GameInfo.class.getDeclaredField("mTotalTime");
        field2.setAccessible(true);
        //TerminalUtils.getInstance().printLine(field2.get(gameInfo).toString());
        String sTotalTime2 = field2.get(gameInfo).toString();

        assertNotEquals(sTotalTime1, sTotalTime2);

    }

}
