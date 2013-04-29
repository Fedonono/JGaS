/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.TSP;

import Util.WorldMap.DestinationPool;
import geneticalgorithm.Population.Population;
import geneticalgorithm.Problem.Problem;

/**
 *
 * @author simonneau
 */
public class TSP extends Problem {

    private DestinationPool dp;

    public TSP() {
        this.setLabel("TSP");
        this.dp = new DestinationPool();
        
        this.addCrossOverOperator(new TSPCrossOverOperator());
        this.addEvaluationOperator(new TSPEvaluationOperator());
        this.addMutationOperator(new TSPMutationOperator());
    }

    @Override
    public Population createInitialPopulation() {

        TSPPopulation pop = new TSPPopulation(this.dp);
        int size = this.getPopulationSize();

        for (int i = 0; i < size; i++) {

            pop.add(TSPIndividual.createRandom(dp));
        }

        return pop;
    }
}
