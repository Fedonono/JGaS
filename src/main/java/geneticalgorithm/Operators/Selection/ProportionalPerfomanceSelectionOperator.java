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
public class ProportionalPerfomanceSelectionOperator extends SelectionOperator {

    private static ProportionalPerfomanceSelectionOperator instance;
    private static String LABEL = "Proportional perfomance selection";

    private ProportionalPerfomanceSelectionOperator() {
        super(LABEL);
    }

    public static ProportionalPerfomanceSelectionOperator getInstance() {
        if (ProportionalPerfomanceSelectionOperator.instance == null) {
            instance = new ProportionalPerfomanceSelectionOperator();
        }
        return instance;
    }

    @Override
    public Population buildNextGeneration(Population population, int survivorSize) {

        int entirePerformance = 0;

        for (Individual individual : population.getIndividuals()) {
            entirePerformance += individual.getScore();
        }


        Population pop = new Population(population.getObservableVolume());
        Iterator<Individual> iterator = population.iterator();
        Individual individual;
        double individualScore;
        int survivorCount = 0;

        while (survivorCount < survivorSize) {
            //TODO
        }

        return pop;
    }
}
