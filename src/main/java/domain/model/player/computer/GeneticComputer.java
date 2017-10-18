package domain.model.player.computer;

import domain.model.Role;
import domain.model.Row;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;
import domain.model.player.ComputerPlayer;

import java.util.ArrayList;
import java.util.Random;

public class GeneticComputer extends ComputerPlayer {
    private final int POPULATION_SIZE = 2000;
    private final int GENERATION_SIZE = 500;
    private final int FEASIBLE_CODES_MAX = 1;

    private Row<ColorPeg> makerCorrectGuess;

    private int currentTurn = 1;
    private int[] blacks, whites;

    private Row<ColorPeg>[] population = new Row[POPULATION_SIZE];
    private int[] fitness = new int[POPULATION_SIZE];

    private ArrayList<Row<ColorPeg>> feasibleCodes = new ArrayList<>();
    private ArrayList<Row<ColorPeg>> gameGuesses = new ArrayList<>();

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

    public Row<ColorPeg> breakerInitialGuess(int pegs, int colors) {
        if (pegs == 4 && colors >= 3) {
            Row<ColorPeg> firstAttempt = new Row<>();
            firstAttempt.add(new ColorPeg(1));
            firstAttempt.add(new ColorPeg(1));
            firstAttempt.add(new ColorPeg(2));
            firstAttempt.add(new ColorPeg(3));
            gameGuesses.add(firstAttempt);
            return firstAttempt;
        } else {
            // Random entry
            Row<ColorPeg> firstAttempt = randomRow(pegs, colors);
            gameGuesses.add(firstAttempt);
            return firstAttempt;
        }
    }

    @Override
    public Row<ColorPeg> breakerGuess(int pegs, int colors) {
        if (currentTurn == 1) return breakerInitialGuess(pegs, colors);
        int generation = 1;

        initPopulation(pegs, colors);
        calculateFitness();
        sortFeasibleByFitness(fitness, population);
        while (feasibleCodes.isEmpty()) {
            while (generation <= GENERATION_SIZE && feasibleCodes.size() <= FEASIBLE_CODES_MAX) {
                // evolvePopulation
                // calculateFitness
                // sort
                // addToFeasible
                generation++;
            }
        }
        Row<ColorPeg> guess = feasibleCodes.get((int) (Math.random() * feasibleCodes.size()));
        gameGuesses.add(guess);
        return guess;
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
        currentTurn += 1;
    }

    private void initPopulation(int pegs, int colors) {
        feasibleCodes.clear();
        for (int i = 0; i < POPULATION_SIZE; ++i) {
            population[i] = randomRow(pegs, colors);
        }
    }

    private void calculateFitness() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            int x = 0;
            int y = 0;
            for (int j = 0; j < gameGuesses.size(); j++) {
                Row<ControlPeg> compare = ComputerPlayer.compareGuess(population[i], gameGuesses.get(j));
                int black = 0;
                int white = 0;
                for (ControlPeg peg : compare) {
                    if (peg.getType() == ControlPeg.TYPE.BLACK) black += 1;
                    if (peg.getType() == ControlPeg.TYPE.WHITE) white += 1;
                }
                x += Math.abs(black - blacks[j]);
                y += Math.abs(white - whites[j]);
            }
            fitness[i] = (x + y);
        }
    }

    private void sortFeasibleByFitness(int[] fitness, Row[] pop) {
        sort(fitness, pop, 0, fitness.length - 1);
    }

    private void sort(int[] fitness, Row[] pop, int low, int up) {
        int p = (low + up) / 2;
        if (up > low) {
            int pn = divide(fitness, pop, low, up, p);
            sort(fitness, pop, low, pn - 1);
            sort(fitness, pop, pn + 1, up);
        }
    }

    private int divide(int[] fitness, Row[] pop, int low, int up, int pivot) {
        int pn = low;
        int pv = fitness[pivot];
        swap(fitness, pivot, up);
        swap(pop, pivot, up);
        for (int i = low; i < up; i++) {
            if (fitness[i] <= pv) {
                swap(fitness, pn, i);
                swap(pop, pn++, i);
            }
            swap(fitness, up, pn);
            swap(pop, up, pn);
        }
        return pn;
    }

    private void swap(int[] fitness, int a, int b) {
        int tmp = fitness[a];
        fitness[a] = fitness[b];
        fitness[b] = tmp;
    }

    private void swap(Row[] pop, int a, int b) {
        Row tmp = pop[a];
        pop[a] = pop[b];
        pop[b] = tmp;
    }

    /**private void evolvePopulation(int pegs) {
        Row[] newPopulation = new Row[POPULATION_SIZE];
        for (int i = 0; i < POPULATION_SIZE; i++) {
            newPopulation[i] = new Row<ColorPeg>(pegs);
        }
        for (int i = 0; i < POPULATION_SIZE; i += 2) {
            if ((int) (Math.random() * 2) == 0) {
                xOver1(newPopulation, i, i + 1);
            } else {
                xOver2(newPopulation, i, i + 1);
            }
        }

        for (int i = 0; i < POPULATION_SIZE; i++) {
            if ((int) (Math.random() * 100) < 3) {
                mutation(newPopulation, i);
            } else if ((int) (Math.random() * 100) < 3) {
                permutation(newPopulation, i);
            } else if ((int) (Math.random() * 100) < 2) {
                inversion(newPopulation, i);
            }
        }

        doubleToRnd(newPopulation);

        population = newPopulation;
    }**/
}
