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
    
    private TruncationSelectionOperator(){
        this.label = "truncation selection operator";
    }
    
    public static TruncationSelectionOperator getInstance() {
        if(TruncationSelectionOperator.instance == null){
           instance = new TruncationSelectionOperator();
        }
        return instance;
    }

    @Override
    public Population buildNextGeneration(Population population, int survivorSize) {

        Population pop = new Population(population.getObservableVolume());
        Iterator<Individual> iterator = population.iterator();
        Individual individual;
        int i = 0;

        population.sort();
        while (iterator.hasNext() && i < survivorSize) {
            individual = iterator.next();
            pop.add(individual);
            i++;
        }

        return pop;
    }
}
