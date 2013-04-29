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
public class TSP extends Problem{

    private DestinationPool waypoints;
    
    public TSP(){
        this.waypoints = new DestinationPool();
    }
    
    
    @Override
    public Population createInitialPopulation() {
        
        TSPPopulation pop = new TSPPopulation(this.waypoints);
        int size = this.getPopulationSize();
        
        for (int i = 0; i < size; i++) {
            
            pop.add(TSPIndividual.createRandom(waypoints));
        }
        
        return pop;
    }
    
}
