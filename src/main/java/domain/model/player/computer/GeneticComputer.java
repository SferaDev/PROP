package domain.model.player.computer;

import domain.model.Role;
import domain.model.Row;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;
import domain.model.player.ComputerPlayer;

import java.util.ArrayList;

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

    private int parentPos = 0;

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
        makerCorrectGuess = breakerGuess(pegs, colors);
        return makerCorrectGuess;
    }

    private Row<ColorPeg> breakerInitialGuess(int pegs, int colors) {
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
                evolvePopulation(pegs, colors);
                calculateFitness();
                sortFeasibleByFitness(fitness, population);
                addToFeasibleCodes();
                generation++;
            }
        }
        Row<ColorPeg> guess = feasibleCodes.get((int) (Math.random() * feasibleCodes.size()));
        gameGuesses.add(guess);
        ++currentTurn;
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
            if (peg.getType() == ControlPeg.Type.BLACK) {
                blacks[currentTurn] += 1;
            } else if (peg.getType() == ControlPeg.Type.WHITE) {
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
                Row<ControlPeg> compare = compareGuess(population[i], gameGuesses.get(j));
                int black = 0;
                int white = 0;
                for (ControlPeg peg : compare) {
                    if (peg.getType() == ControlPeg.Type.BLACK) black += 1;
                    if (peg.getType() == ControlPeg.Type.WHITE) white += 1;
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

    private void evolvePopulation(int pegs, int colors) {/**/

        Row<ColorPeg>[] newPopulation = new Row[POPULATION_SIZE];
        for (int i = 0; i < POPULATION_SIZE; i++) {
            newPopulation[i] = new Row<ColorPeg>(pegs);
        }
        for (int i = 0; i < POPULATION_SIZE; i += 2) {
            if ((int) (Math.random() * 2) == 0) {
                xOver1(newPopulation, i, i + 1, pegs);
            } else {
                xOver2(newPopulation, i, i + 1, pegs);
            }
        }

        for (int i = 0; i < POPULATION_SIZE; i++) {
            if ((int) (Math.random() * 100) < 3) {
                mutation(newPopulation, i, pegs, colors);
            } else if ((int) (Math.random() * 100) < 3) {
                permutation(newPopulation, i, pegs);
            } else if ((int) (Math.random() * 100) < 2) {
                inversion(newPopulation, i, pegs);
            }
        }

        doubleToRnd(newPopulation, pegs, colors);

        population = newPopulation;
    }

    private void addToFeasibleCodes() {
        outer:
        for (int i = 0; i < POPULATION_SIZE; i++) {
            for (int j = 0; j < currentTurn; j++) {
                Row<ControlPeg> compare = compareGuess(population[i], gameGuesses.get(j));
                int black = 0;
                int white = 0;
                for (ControlPeg peg : compare) {
                    if (peg.getType() == ControlPeg.Type.BLACK) black += 1;
                    if (peg.getType() == ControlPeg.Type.WHITE) white += 1;
                }

                if (black != blacks[j] || white != whites[j]) {
                    continue outer;
                }
            }
        }
    }

    private void xOver1(Row<ColorPeg>[] newPopulation, int child1Pos, int child2Pos, int pegs) {
        int mother = getParentPos();
        int father = getParentPos();
        int sep = ((int) (Math.random() * pegs)) + 1;

        for (int j = 0; j < pegs; j++) {
            if (j <= sep) {
                newPopulation[child1Pos].setColorAtPos(j,
                        population[mother].get(j).getColor());
                newPopulation[child2Pos].setColorAtPos(j,
                        population[father].get(j).getColor());
            } else {
                newPopulation[child1Pos].setColorAtPos(j,
                        population[father].get(j).getColor());
                newPopulation[child2Pos].setColorAtPos(j,
                        population[mother].get(j).getColor());
            }
        }
    }

    private void xOver2(Row[] newPopulation, int child1Pos, int child2Pos, int pegs) {
        int mother = getParentPos();
        int father = getParentPos();
        int sep1;
        int sep2;

        sep1 = ((int) (Math.random() * pegs)) + 1;
        sep2 = ((int) (Math.random() * pegs)) + 1;

        if (sep1 > sep2) {
            int temp = sep1;
            sep1 = sep2;
            sep2 = temp;
        }

        for (int i = 0; i < pegs; i++) {
            if (i <= sep1 || i > sep2) {
                newPopulation[child1Pos].setColorAtPos(i,
                        population[mother].get(i).getColor());
                newPopulation[child2Pos].setColorAtPos(i,
                        population[father].get(i).getColor());
            } else {
                newPopulation[child1Pos].setColorAtPos(i,
                        population[father].get(i).getColor());
                newPopulation[child2Pos].setColorAtPos(i,
                        population[mother].get(i).getColor());
            }
        }

    }

    private int getParentPos() {
        parentPos += (int) (Math.random() * 7);
        if (parentPos < POPULATION_SIZE / 5) {
            return parentPos;
        } else {
            parentPos = 0;
        }
        return parentPos;
    }

    private void mutation(Row[] newPopulation, int popPos, int pegs, int colors) {
        newPopulation[popPos].setColorAtPos((int) (Math.random() * pegs),
                (int) (Math.random() * colors));
    }

    private void permutation(Row[] newPopulation, int popPos, int pegs) {
        int pos1 = (int) (Math.random() * pegs);
        int pos2 = (int) (Math.random() * pegs);
        int tmp = newPopulation[popPos].getColorAtPos(pos1);
        newPopulation[popPos].setColorAtPos(pos1,
                newPopulation[popPos].getColorAtPos(pos2));
        newPopulation[popPos].setColorAtPos(pos2, tmp);
    }

    private void inversion(Row[] newPopulation, int popPos, int pegs) {
        int pos1 = (int) (Math.random() * pegs);
        int pos2 = (int) (Math.random() * pegs);

        if (pos2 < pos1) {
            int tmp = pos2;
            pos2 = pos1;
            pos1 = tmp;
        }

        for (int i = 0; i < (pos2 - pos1)/2; i++) {
            int tmp = newPopulation[popPos].getColorAtPos(pos1 + i);
            newPopulation[popPos].setColorAtPos(pos1 + i,
                    newPopulation[popPos].getColorAtPos(pos2 - i));
            newPopulation[popPos].setColorAtPos(pos2 - i, tmp);
        }
    }

    private void doubleToRnd(Row[] newPopulation, int pegs, int colors) {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (lookForSame(newPopulation, i)) {
                newPopulation[i] = randomRow(pegs, colors);
            }
        }
    }

    private boolean lookForSame(Row[] newPopulation, int popPos) {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (population[popPos].equals(newPopulation[popPos])) {
                return true;
            }
        }
        return false;
    }

}
