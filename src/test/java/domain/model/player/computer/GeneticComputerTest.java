package domain.model.player.computer;

import domain.model.player.Player;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GeneticComputerTest {
    GeneticComputer gc;

    @Before
    public void setUp () {
        gc = new GeneticComputer(Player.Role.MAKER);
    }

    /*Related with getName()*/
    @Test
    public void shouldReturnGenetic() {
        assertEquals("Should return Genetic when executing getName()", "Genetic", gc.getName());
    }

    /*Related with breakerGuess()*/
    /*Function breakerGuess() not tested yet*/

    /*Related with receiveControl()*/
    @Test
    public void shouldAddToBlacks() {
        ControlRow c = new ControlRow(1,0);
        gc.receiveControl(c);
        assertEquals("Should add 1 to blacks", "1", gc.getTurnBlacks().get(0).toString());
    }

    @Test
    public void shouldAddToWhites() {
        ControlRow c = new ControlRow(0,1);
        gc.receiveControl(c);
        assertEquals("Should add 1 to whites", "1", gc.getTurnWhites().get(0).toString());
    }

    @Test
    public void shouldAddToBlacksAndWhites() {
        ControlRow c = new ControlRow(2,3);
        gc.receiveControl(c);
        assertEquals("Should add 1 to whites", "2 3",
                gc.getTurnBlacks().get(0).toString() + " " + gc.getTurnWhites().get(0).toString());
    }

    /*Related with breakerInitialGuess()*/
    @Test
    public void shouldReturn1123FirstGuess() {
        ColorRow expected = new ColorRow(1, 1, 2, 3);
        assertEquals("Should return [1, 1, 2, 3] initial guess",
                expected, gc.breakerGuess(4, 3));
    }

    @Test
    public void shouldReturn11234FirstGuess() {
        ColorRow expected = new ColorRow(1, 1, 2, 3, 4);
        assertEquals("Should return [1, 1, 2, 3, 4] initial guess",
                expected, gc.breakerGuess(5, 4));
    }

    @Test
    public void shouldReturn112234FirstGuess() {
        ColorRow expected = new ColorRow(1, 1, 2, 2, 3, 4);
        assertEquals("Should return [1, 1, 2, 2, 3, 4] initial guess",
                expected, gc.breakerGuess(6, 4));
    }

    @Test
    public void shouldReturn11223345FirstGuess() {
        ColorRow expected = new ColorRow(1, 1, 2, 2, 3, 3, 4, 5);
        assertEquals("Should return [1, 1, 2, 2, 3, 3, 4, 5] initial guess",
                expected, gc.breakerGuess(8, 5));
    }






}