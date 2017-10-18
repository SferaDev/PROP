package domain.model.player.computer;

import domain.model.Role;
import domain.model.Row;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;
import domain.model.player.ComputerPlayer;

import java.util.Random;

public class GeneticComputer extends ComputerPlayer {
    private final int POPULATION_SIZE = 2000;

    private Row<ColorPeg> makerCorrectGuess;

    private int currentTurn = 1;
    private int[] blacks, whites;
    private int[] fitness = new int[POPULATION_SIZE];
    private Row<ColorPeg>[] population = new Row[POPULATION_SIZE];

    public GeneticComputer(Role role, int pegs, int colors, int turns) {
        super(role);

        blacks = new int[turns]; // blacks[i] = numero de negres en el torn i;
        whites = new int[turns]; // whites[i] = numero de blanques en el torn i;
    }

    @Override
    public String getName() {
        return "Genetic";
    }

    @Override
    public Row<ColorPeg> makerGuess(int pegs, int colors) {
        makerCorrectGuess = randomRow(pegs, colors);
        return makerCorrectGuess;
    }

    private Row<ColorPeg> randomRow(int pegs, int colors) {
        Row<ColorPeg> row = new Row<>();
        Random rand = new Random();
        for (int i = 0; i < pegs; ++i) {
            row.add(new ColorPeg(rand.nextInt(colors) + 1));
        }
        return row;
    }

    @Override
    public Row<ColorPeg> breakerInitialGuess(int pegs, int colors) {
        if (pegs == 4) {
            Row<ColorPeg> firstAttempt = new Row<>();
            firstAttempt.add(new ColorPeg(1));
            firstAttempt.add(new ColorPeg(1));
            firstAttempt.add(new ColorPeg(2));
            firstAttempt.add(new ColorPeg(3));
            return firstAttempt;
        } else {
            // Random entry
            return makerGuess(pegs, colors);
        }
    }

    @Override
    public Row<ColorPeg> breakerGuess(int pegs, int colors) {
        currentTurn += 1;
        // TODO
        return null;
    }

    @Override
    public Row<ControlPeg> scoreGuess(Row<ColorPeg> guess) {
        return ComputerPlayer.compareGuess(makerCorrectGuess, guess);
    }

    @Override
    public void receiveControl(Row<ControlPeg> control) {
        blacks[currentTurn] = whites[currentTurn] = 0;
        for (ControlPeg peg : control) {
            if (peg.getType() == ControlPeg.TYPE.BLACK) {
                blacks[currentTurn] += 1;
            } else if (peg.getType() == ControlPeg.TYPE.WHITE) {
                whites[currentTurn] += 1;
            }
        }
    }
}
