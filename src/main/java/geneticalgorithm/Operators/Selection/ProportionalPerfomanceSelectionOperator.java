/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Selection;

import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Population;
import java.util.ArrayList;
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

        ArrayList<Double> scores = new ArrayList<>();
        double minScore = Double.MAX_VALUE;
        double score;

        for (Individual individual : population.getIndividuals()) {

            score = individual.getScore();
            scores.add(new Double(score));

            if (score < minScore) {
                minScore = score;
            }
        }

        double totalScore = 0;
        int size = scores.size();

        for (int i = 0; i < size; i++) {
            score = scores.get(i) - minScore + 1;
            scores.set(i, score);
            totalScore += score;
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
