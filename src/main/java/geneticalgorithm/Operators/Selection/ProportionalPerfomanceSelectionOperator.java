/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Selection;

import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Population;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simonneau
 */
public class ProportionalPerfomanceSelectionOperator extends SelectionOperator {

    private static ProportionalPerfomanceSelectionOperator instance;
    private static String LABEL = "Proportional perfomance selection";
    private ArrayList<Double> scores;

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

        Population nextPopulation = new Population(population.getObservableVolume());

        if (population.size() <= survivorSize) {
            nextPopulation.addAll(population.getIndividuals());

        } else {

            double totalScore = this.getTotalScore(population);

            Population p = population.clone();

            double score;

            int survivorCount = 0;
            int i;
            int size;

            while (survivorCount < survivorSize) {

                i = 0;
                size = p.size();

                while (i < size && survivorCount < survivorSize) {

                    score = this.scores.get(i);

                    if (Math.random() <= score / totalScore) {

                        nextPopulation.add(p.remove(i));
                        this.scores.remove(i);
                        size--;

                        totalScore -= score;
                        survivorCount++;
                    }
                    i++;
                }


            }
        }

        return nextPopulation;
    }

    private double getTotalScore(Population population) {

        this.scores = new ArrayList<>(population.size());
        double minScore = this.getminScore(population);
        double totalScore = 0;
        double score;
        List<Individual> individuals = population.getIndividuals();

        for (Individual individual : individuals) {

            score = individual.getScore() - minScore + 1;
            this.scores.add(score);
            totalScore += score;
        }

        return totalScore;
    }

    private double getminScore(Population population) {

        List<Individual> individuals = population.getIndividuals();

        double minScore = Double.MAX_VALUE;
        double score;

        for (Individual individual : individuals) {

            score = individual.getScore();

            if (score < minScore) {
                minScore = score;
            }
        }
        return minScore;
    }
}
