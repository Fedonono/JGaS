/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.TSP;

import Util.WorldMap.DestinationPool;
import geneticalgorithm.Operators.Mutation.MutationOperator;
import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author simonneau
 */
public class TSPRandomMutationOperator extends MutationOperator {
    
    private static String LABEL ="Random path";
    
    /**
     *
     */
    public TSPRandomMutationOperator(){
        super(LABEL);
    }

    @Override
    public Individual mutate(Individual individual) {
        DestinationPool dp = ((TSPIndividual)individual).getDestinations();
        return TSPIndividual.createRandom(dp);
    }
}
