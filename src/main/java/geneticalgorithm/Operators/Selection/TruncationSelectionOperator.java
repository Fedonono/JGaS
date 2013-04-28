/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Selection;

import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Population;
import java.util.Iterator;

/**
 *
 * @author simonneau
 */
public class TruncationSelectionOperator extends SelectionOperator {

    private static TruncationSelectionOperator instance;
    private static String LABEL = "Truncation selection";

    private TruncationSelectionOperator() {
        super(LABEL);
    }

    public static TruncationSelectionOperator getInstance() {
        if (TruncationSelectionOperator.instance == null) {
            instance = new TruncationSelectionOperator();
        }
        return instance;
    }

    @Override
    public Population buildNextGeneration(Population population, int survivorSize) {

        Population nextPopulation = new Population(population.getObservableVolume());

        if (population.size() <= survivorSize) {
            nextPopulation.addAll(population.getIndividuals());
            
        } else {
            population.sort();
            Iterator<Individual> iterator = population.iterator();
            Individual individual;
            int i = 0;

            while (iterator.hasNext() && i < survivorSize) {
                individual = iterator.next();
                nextPopulation.add(individual);
                i++;
            }
        }

        return nextPopulation;
    }
}
