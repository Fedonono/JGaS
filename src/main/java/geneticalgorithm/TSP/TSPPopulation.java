/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.TSP;

import Util.WorldMap.DestinationPool;
import geneticalgorithm.Population.Population;
import geneticalgorithm.Population.PopulationController;

/**
 *
 * @author simonneau
 */
public class TSPPopulation extends Population{
    
    private DestinationPool destinationPool;
    
    /**
     *
     * @param destinationPool
     */
    public TSPPopulation(DestinationPool destinationPool){
        
        this.destinationPool = destinationPool;
        this.addView(new TSPPopulationUI(this, new PopulationController(this)));
    }

    /**
     *
     * @return
     */
    public DestinationPool getDestinationPool() {
        return destinationPool;
    }
    
    
}
