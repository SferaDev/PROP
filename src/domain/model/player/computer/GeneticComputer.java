package domain.model.player.computer;

import domain.model.player.ComputerPlayer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

import java.util.ArrayList;

/**
 * The Genetic computer.
 *
 * @author Oriol Borrell Roig
 */
public class GeneticComputer extends ComputerPlayer implements java.io.Serializable {

    /**
     * Size of the population that will be generated.
     */
    private static final int POPULATION_SIZE = 10000;

    /**
     * Number of generations. If we don't have any feasible code
     * generations, a new guess with new generations and populations will be
     * made.
     */
    private static final int GENERATION_SIZE = 5000;

    /**
     * Max number of feasible codes. When the FEASIBLE_CODES_MAX is reached, we stop producing feasible codes
     * and we choose one randomly
     */
    private static final int FEASIBLE_CODES_MAX = 1;


    /**
     * Array with the GeneticComputer previous attempts
     */
    private final ArrayList<ColorRow> gameGuesses = new ArrayList<>();

    /**
     * Array with the GeneticComputer previous black results
     */
    private final ArrayList<Integer> turnBlacks = new ArrayList<>();

    /**
     * Array with the GeneticComputer previous white results
     */
    private final ArrayList<Integer> turnWhites = new ArrayList<>();
    /**
     * Array that contains the fitness of each population. Fitness[i] contains the fitness of population[i]
     */
    private final ArrayList<Integer> fitness = new ArrayList<>(POPULATION_SIZE);
    /**
     * Array with all the feasible codes found.
     */
    private final ArrayList<ColorRow> feasibleCodes = new ArrayList<>();
    /**
     * Array with the population generated
     */
    private ArrayList<ColorRow> population = new ArrayList<>(POPULATION_SIZE);
    /**
     * Integer used to choose 2 ColorRows for the crossover
     */
    private Integer parentPos = 0;

    /**
     * Instantiates a new Genetic computer.
     *
     * @param role Is the role of the computer
     */
    public GeneticComputer(Role role) {
        super(role);
    }

    /**
     * Return the name of the algorithm
     */
    @Override
    public String getName() {
        return "Genetic";
    }

    /**
     * Compute the next guess. To do it, initialises a random population, and calculates the fitness for that population.
     * After that, generates new population using crossover, mutation, inversion and permutation, until it has enough
     * feasible codes, and returns one of those randomly.
     *
     * @param pegs   is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @return the combination that is going to be tried
     */
    @Override
    public ColorRow breakerGuess(int pegs, int colors) {
        if (turnBlacks.size() == 0 && turnWhites.size() == 0) {
            ColorRow initialGuess = breakerInitialGuess(pegs, colors);
            gameGuesses.add(initialGuess);
            return initialGuess;
        }
        int generation = 0;
        boolean feasibleNotFull = true;
        initPopulation(pegs, colors);
        calculateFitness();
        sortFeasibleByFitness(fitness, population);
        while (feasibleCodes.isEmpty()) {
            generation = 0;
            while (feasibleNotFull && generation <= GENERATION_SIZE) {
                evolvePopulation(pegs, colors);
                calculateFitness();
                sortFeasibleByFitness(fitness, population);
                feasibleNotFull = addToFeasibleCodes();
                generation += 1;
            }

        }
        ColorRow guess = feasibleCodes.get((int) (Math.random() * feasibleCodes.size()));
        gameGuesses.add(guess);
        return guess;
    }

    @Override
    public void receiveColor(ColorRow guess) {
        // No action
    }

    /**
     * Updates the control information to the arrays of blacks and whites.
     *
     * @param control is the number of black and white pegs of the last attempt
     */
    @Override
    public void receiveControl(ControlRow control) {
        turnBlacks.add(control.getBlacks());
        turnWhites.add(control.getWhites());
    }

    private ColorRow breakerInitialGuess(int pegs, int colors) {
        ColorRow firstAttempt;
        if (pegs == 4 && colors >= 3) {
            firstAttempt = new ColorRow(1, 1, 2, 3);
        } else if (pegs == 5 && colors >= 4) {
            firstAttempt = new ColorRow(1, 1, 2, 3, 4);
        } else if (pegs == 6 && colors >= 4) {
            firstAttempt = new ColorRow(1, 1, 2, 2, 3, 4);
        } else if (pegs == 8 && colors >= 5) {
            firstAttempt = new ColorRow(1, 1, 2, 2, 3, 3, 4, 5);
        } else {
            return randomRow(pegs, colors);
        }
        return firstAttempt;
    }

    private void initPopulation(int pegs, int colors) {
        feasibleCodes.clear();
        population.clear();
        fitness.clear();
        for (int i = 0; i < POPULATION_SIZE; ++i) {
            population.add(i, randomRow(pegs, colors));
            fitness.add(i, 0);
        }
    }

    private void calculateFitness() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            int x = 0;
            int y = 0;
            for (int j = 0; j < gameGuesses.size(); j++) {
                ControlRow compare = compareGuess(population.get(i), gameGuesses.get(j));
                x += Math.abs(compare.getBlacks() - turnBlacks.get(j));
                y += Math.abs(compare.getWhites() - turnWhites.get(j));
            }
            fitness.set(i, x + y);
        }

    }

    private void sortFeasibleByFitness(ArrayList<Integer> fitness, ArrayList<ColorRow> pop) {
        sort(fitness, pop, 0, fitness.size() - 1);
    }

    private void sort(ArrayList<Integer> fitness, ArrayList<ColorRow> pop, int low, int up) {
        int p = (low + up) / 2;
        if (up > low) {
            int pn = divide(fitness, pop, low, up, p);
            sort(fitness, pop, low, pn - 1);
            sort(fitness, pop, pn + 1, up);
        }
    }

    private int divide(ArrayList<Integer> fitness, ArrayList<ColorRow> pop, int low, int up, int pivot) {
        int pn = low;
        int pv = fitness.get(pivot);
        swapInteger(fitness, pivot, up);
        swapColorRow(pop, pivot, up);
        for (int i = low; i < up; i++) {
            if (fitness.get(i) <= pv) {
                swapInteger(fitness, pn, i);
                swapColorRow(pop, pn++, i);
            }
            swapInteger(fitness, up, pn);
            swapColorRow(pop, up, pn);
        }
        return pn;
    }

    private void swapInteger(ArrayList<Integer> fitness, int a, int b) {
        int tmp = fitness.get(a);
        fitness.set(a, fitness.get(b));
        fitness.set(b, tmp);
    }

    private void swapColorRow(ArrayList<ColorRow> pop, int a, int b) {
        ColorRow tmp = pop.get(a);
        pop.set(a, pop.get(b));
        pop.set(b, tmp);
    }

    private void evolvePopulation(int pegs, int colors) {/**/
        ArrayList<ColorRow> newPopulation = new ArrayList<>(POPULATION_SIZE);
        for (int i = 0; i < POPULATION_SIZE; i++) {
            newPopulation.add(i, new ColorRow());
        }
        for (int i = 0; i < POPULATION_SIZE; i += 2) {
            if ((int) (Math.random() * 2) == 0) {
                crossover1Point(newPopulation, i, i + 1, pegs);
            } else {
                crossover2Points(newPopulation, i, i + 1, pegs);
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

        repetitionsToRandom(newPopulation, pegs, colors);

        population = newPopulation;
    }

    private boolean addToFeasibleCodes() {
        outer:
        for (int i = 0; i < POPULATION_SIZE; i++) {
            for (int j = 0; j < turnBlacks.size() && j < turnWhites.size(); j++) {
                ControlRow compare = compareGuess(population.get(i), gameGuesses.get(j));
                if (compare.getBlacks() != turnBlacks.get(j) || compare.getWhites() != turnWhites.get(j)) {
                    continue outer;
                }
            }

            if (feasibleCodes.size() < FEASIBLE_CODES_MAX) {
                if (!feasibleCodes.contains(population.get(i))) {
                    feasibleCodes.add(population.get(i));
                    if (feasibleCodes.size() < FEASIBLE_CODES_MAX) {
                        return false;
                    }
                }
            } else {
                // E is full.
                return false;
            }
        }
        return true;
    }

    private void crossover1Point(ArrayList<ColorRow> newPopulation, int child1Pos, int child2Pos, int pegs) {
        int mother = getParentPos();
        int father = getParentPos();
        int sep = ((int) (Math.random() * pegs)) + 1;

        for (int j = 0; j < pegs; j++) {
            if (j <= sep) {
                newPopulation.get(child1Pos).add(j, population.get(mother).get(j));
                newPopulation.get(child2Pos).add(j, population.get(father).get(j));
            } else {
                newPopulation.get(child1Pos).add(j, population.get(father).get(j));
                newPopulation.get(child2Pos).add(j, population.get(mother).get(j));
            }
        }
    }

    private void crossover2Points(ArrayList<ColorRow> newPopulation, int child1Pos, int child2Pos, int pegs) {
        int mother = getParentPos();
        int father = getParentPos();
        int sep1 = ((int) (Math.random() * pegs)) + 1;
        int sep2 = ((int) (Math.random() * pegs)) + 1;

        if (sep1 > sep2) {
            int temp = sep1;
            sep1 = sep2;
            sep2 = temp;
        }

        for (int i = 0; i < pegs; i++) {
            if (i <= sep1 || i > sep2) {
                newPopulation.get(child1Pos).add(i, population.get(mother).get(i));
                newPopulation.get(child2Pos).add(i, population.get(father).get(i));

            } else {
                newPopulation.get(child1Pos).add(i, population.get(father).get(i));
                newPopulation.get(child2Pos).add(i, population.get(mother).get(i));
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

    private void mutation(ArrayList<ColorRow> newPopulation, int popPos, int pegs, int colors) {
        int pos = (int) (Math.random() * pegs);
        newPopulation.get(popPos).remove(pos);
        newPopulation.get(popPos).add(pos, new ColorRow.ColorPeg((int) (Math.random() * colors)));
    }

    private void permutation(ArrayList<ColorRow> newPopulation, int popPos, int pegs) {
        int pos1 = (int) (Math.random() * pegs);
        int pos2 = (int) (Math.random() * pegs);
        ColorRow.ColorPeg tmp = new ColorRow.ColorPeg(newPopulation.get(popPos).get(pos1).getColor());
        ColorRow tempRow = new ColorRow();
        for (int aux = 0; aux < pegs; ++aux) {
            tempRow.add(new ColorRow.ColorPeg(newPopulation.get(popPos).get(aux).getColor()));
        }
        newPopulation.get(popPos).remove(pos1);
        newPopulation.get(popPos).add(pos1,
                tempRow.get(pos2));
        newPopulation.get(popPos).remove(pos2);
        newPopulation.get(popPos).add(pos2, tmp);
    }

    private void inversion(ArrayList<ColorRow> newPopulation, int popPos, int pegs) {
        int pos1 = (int) (Math.random() * pegs);
        int pos2 = (int) (Math.random() * pegs);

        if (pos2 < pos1) {
            int tmp = pos2;
            pos2 = pos1;
            pos1 = tmp;
        }

        for (int i = 0; i < (pos2 - pos1) / 2; i++) {
            ColorRow.ColorPeg tmp = new ColorRow.ColorPeg(newPopulation.get(popPos).get(pos1 + i).getColor());
            newPopulation.get(popPos).add(pos1 + i, newPopulation.get(popPos).get(pos2 - i));
            newPopulation.get(popPos).remove(pos1 + i + 1);
            newPopulation.get(popPos).add(pos2 - i, tmp);
            newPopulation.get(popPos).remove(pos2 - i + 1);
        }
    }

    private void repetitionsToRandom(ArrayList<ColorRow> newPopulation, int pegs, int colors) {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (isARepetition(newPopulation, i)) {
                newPopulation.set(i, randomRow(pegs, colors));
            }
        }
    }

    private boolean isARepetition(ArrayList<ColorRow> newPopulation, int popPos) {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (population.get(popPos).equals(newPopulation.get(popPos))) {
                return true;
            }
        }
        return false;
    }

}
