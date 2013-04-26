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
public class ProportionalRankingSelectionOperator extends SelectionOperator {

    private static ProportionalRankingSelectionOperator instance;
    private static String LABEL = "Proportional ranking selection";
    private ArrayList<Integer> ranking;
    private int poolRange;

    private ProportionalRankingSelectionOperator() {
        super(LABEL);
    }

    public static ProportionalRankingSelectionOperator getInstance() {
        if (ProportionalRankingSelectionOperator.instance == null) {
            instance = new ProportionalRankingSelectionOperator();
        }
        return instance;
    }

    @Override
    public Population buildNextGeneration(Population population, int survivorSize) {

        this.madeRanking(population);

        Population p = population.clone();
        Population nextPopulation = new Population(population.getObservableVolume());

        int survivorCount = 0;
        int i;
        int size;
        int initialSize = p.size();
        double adaptability;

        while (survivorCount < survivorSize) {

            i = 0;
            size = p.size();

            while (i < size && survivorCount < survivorSize) {
                
                adaptability = initialSize - this.ranking.get(i) + 1;
                
                if (Math.random() <= adaptability / this.poolRange) {

                    nextPopulation.add(p.remove(i));
                    this.ranking.remove(i);
                    this.poolRange -= adaptability;
                    size--;
                    survivorCount++;
                }
                i++;
            }


        }

        return nextPopulation;
    }

    private void madeRanking(Population population) {

        this.ranking = new ArrayList<>(population.size());
        this.poolRange = 0;
        population.sort();

        List<Individual> individuals = population.getIndividuals();
        int size = population.size();

        for (int i = 1; i <= size; i++) {

            this.ranking.add(i);
            this.poolRange += i;
        }
    }
}
