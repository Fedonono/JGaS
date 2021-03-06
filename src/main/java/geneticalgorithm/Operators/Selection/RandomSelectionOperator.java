/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Selection;

import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Population;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public class RandomSelectionOperator extends SelectionOperator {

    private static RandomSelectionOperator instance;
    private static String LABEL = "Random selection";

    private RandomSelectionOperator() {
        super(LABEL);
    }

    /**
     *
     * @return
     */
    public static RandomSelectionOperator getInstance() {
        if (RandomSelectionOperator.instance == null) {
            instance = new RandomSelectionOperator();
        }
        return instance;
    }

    /**
     * select survivorSize individuals randomly from population.
     * @param population
     * @param survivorSize
     * @return
     */
    @Override
    public Population buildNextGeneration(Population population, int survivorSize) {

        Population nextPopulation = new Population(population.getObservableVolume());

        if (population.size() <= survivorSize) {
            nextPopulation.addAll(population.getIndividuals());

        } else {
            LinkedList<Individual> individuals = new LinkedList<>(population.getIndividuals());
            int survivorCount = 0;

            int size = individuals.size();

            while (survivorCount < survivorSize) {

                int index = (int) Math.round(Math.random() * (size - 1));
                nextPopulation.add(individuals.remove(index));
                survivorCount++;
                size--;
            }
        }
        return nextPopulation;
    }
}
