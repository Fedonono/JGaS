/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Selection;

import geneticalgorithm.Population.Population;

/**
 *
 * @author simonneau
 */
public class TournamentSelectionOperator extends SelectionOperator{
    
    private static TournamentSelectionOperator instance;
    private static String LABEL = "Tournament selection";

    private TournamentSelectionOperator() {
        super(LABEL);
    }

    public static TournamentSelectionOperator getInstance() {
        if (TournamentSelectionOperator.instance == null) {
            instance = new TournamentSelectionOperator();
        }
        return instance;
    }

    @Override
    public Population buildNextGeneration(Population population, int size) {
        
        Population nextPopulation = new Population(population.getObservableVolume());
        
        //tournament
        
        return nextPopulation;       
    }
    
}
