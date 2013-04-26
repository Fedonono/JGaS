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
public class RandomSelectionOperator extends SelectionOperator{
    private static RandomSelectionOperator instance;
    private static String LABEL = "Random selection";
    

    private RandomSelectionOperator() {
        super(LABEL);
    }

    public static RandomSelectionOperator getInstance() {
        if (RandomSelectionOperator.instance == null) {
            instance = new RandomSelectionOperator();
        }
        return instance;
    }

    @Override
    public Population buildNextGeneration(Population population, int survivorSize) {
        
        if(population.size() == survivorSize){
            return population;
        }
        
        Population nextPopulation = new Population(population.getObservableVolume());
        Population p = population.clone();
        int survivorCount = 0;
        
        int size = p.size();
        
        while(survivorCount < survivorSize){
            
            
            
        }        
        
        return nextPopulation;
    }
}
