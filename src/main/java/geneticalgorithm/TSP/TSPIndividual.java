/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.TSP;

import Util.WorldMap.Destination;
import Util.WorldMap.DestinationPool;
import geneticalgorithm.Population.Individuals.Individual;
import java.util.ArrayList;

/**
 *
 * @author simonneau
 */
public class TSPIndividual extends Individual<Individual> {

    protected DestinationPool destinations;
    protected ArrayList<Destination> path;

    public TSPIndividual(DestinationPool dp, ArrayList<Destination> path) {
        this.destinations = dp;
        this.path = path;
    }

    public DestinationPool getDestinations() {
        return destinations;
    }

    public ArrayList<Destination> getPath() {
        return path;
    }

       
    

    @Override
    protected void set(Individual s) {
        if(s instanceof TSPIndividual){
            TSPIndividual i = (TSPIndividual)s;
            this.destinations = i.destinations;
            this.path = i.path;
        }
    }

    @Override
    public String xmlSerialization() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
