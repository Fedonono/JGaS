/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Selection;

import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Population;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public class TournamentSelectionOperator extends SelectionOperator {

    private static TournamentSelectionOperator instance;
    private static String LABEL = "Tournament selection";
    private LinkedList<Individual> draft;

    private TournamentSelectionOperator() {
        super(LABEL);
    }

    /**
     *
     * @return
     */
    public static TournamentSelectionOperator getInstance() {
        if (TournamentSelectionOperator.instance == null) {
            instance = new TournamentSelectionOperator();
        }
        return instance;
    }

    /**
     * select survivorSize individuals form population. each individuals selected win a tournament.
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

            ArrayList<Individual> individuals = new ArrayList<>();
            this.draft = new LinkedList<>();
            this.draft.addAll(population.getIndividuals());



            int survivorCount = 0;
            int size;

            while (survivorCount < survivorSize) {

                individuals.clear();
                individuals.addAll(this.draft);
                this.draft.clear();

                size = individuals.size();
                if (size == 1) {
                    nextPopulation.add(individuals.get(0));
                    survivorCount++;
                }
                while (size > 1 && survivorCount < survivorSize) {

                    int firstChallengerIndex = (int) Math.round(Math.random() * (size - 1));
                    Individual firstChallenger = individuals.remove(firstChallengerIndex);
                    size--;
                    double firstScore = firstChallenger.getScore();

                    int secondChallengerIndex = (int) Math.round(Math.random() * (size - 1));
                    Individual secondChallenger = individuals.remove(secondChallengerIndex);
                    size--;
                    double secondScore = secondChallenger.getScore();

                    if (firstScore > secondScore) {

                        nextPopulation.add(firstChallenger);
                        survivorCount++;
                        this.draft.add(secondChallenger);

                    } else if (firstScore < secondScore) {

                        nextPopulation.add(secondChallenger);
                        survivorCount++;
                        this.draft.add(firstChallenger);


                    } else {

                        nextPopulation.add(firstChallenger);
                        survivorCount++;

                        if (survivorCount < survivorSize) {

                            nextPopulation.add(secondChallenger);
                            survivorCount++;
                        }
                    }
                }
                if (size == 1) {
                    this.draft.add(individuals.get(0));
                }
            }

        }

        return nextPopulation;
    }
}
