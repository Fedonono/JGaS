/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.TSP;

import Util.WorldMap.DestinationPool;
import geneticalgorithm.Operators.Mutation.MutationOperator;

/**
 *
 * @author simonneau
 */
public class TSPRandomMutationOperator extends MutationOperator<TSPIndividual> {
    
    private static String LABEL ="Random path";
    
    /**
     *
     */
    public TSPRandomMutationOperator(){
        super(LABEL);
    }

    @Override
    public TSPIndividual mutate(TSPIndividual individual) {
        DestinationPool dp = individual.getDestinations();
        return TSPIndividual.createRandom(dp);
    }
}
