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
public class RouletteSelectionOperator extends SelectionOperator {
    
    private static RouletteSelectionOperator instance;
    
    private RouletteSelectionOperator() {
        this.label = "Roulette selection operator";
    }

    public static RouletteSelectionOperator getInstance() {
        if (instance == null) {
            instance = new RouletteSelectionOperator();
        }
        return instance;
    }

    @Override
    public Population buildNextGeneration(Population population, int size) {
        population.sort();
        //TODO
        
        return null;
    }
    
    @Override
    public String toString(){
        return this.label;
    }
}
