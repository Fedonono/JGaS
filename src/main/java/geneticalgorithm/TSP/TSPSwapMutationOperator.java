/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.TSP;

import Util.WorldMap.Destination;
import geneticalgorithm.Operators.Mutation.MutationOperator;
import geneticalgorithm.Population.Individuals.Individual;
import java.util.ArrayList;

/**
 *
 * @author simonneau
 */
public class TSPSwapMutationOperator extends MutationOperator {
    
    private static String LABEL =  "swap destinations";
    
    public TSPSwapMutationOperator(){
        super(LABEL);
    }

    @Override
    public Individual mutate(Individual individual) {
        
        
        TSPIndividual tspIndividual = (TSPIndividual) individual;
        ArrayList<Destination> path = new ArrayList<>(tspIndividual.getPath());
        
        int size = path.size();
        path.remove(size - 1);
        size--;

        int index1 = (int) Math.round(Math.random() * (path.size()-1));
        int index2 = (int) Math.round(Math.random() * (path.size()-1));

        if (index2 == index1) {
            if (index2 + 1 < size) {
                index2++;
            } else if (index2 - 1 >= 0) {
                index2--;
            }
        }
        
        Destination tmp = path.get(index1);
        path.set(index1, path.get(index2));
        path.set(index2, tmp);
        
        path.add(path.get(0));
        
        return new TSPIndividual(tspIndividual.getDestinations(), path);
    }
}
