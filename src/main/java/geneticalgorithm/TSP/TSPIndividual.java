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

    /**
     *
     */
    protected DestinationPool destinations;
    /**
     *
     */
    protected ArrayList<Destination> path;

    /**
     *
     * @param dp
     * @return
     */
    public static TSPIndividual createRandom(DestinationPool dp) {

        ArrayList<Destination> destinations = new ArrayList<>(dp.getDestinations());
        ArrayList<Destination> path = new ArrayList<>();
        int size = destinations.size();
        int destinationSize = size;

        if (size > 0) {
            for (int i = 0; i < size; i++) {

                int index = (int) Math.round(Math.random() * (destinationSize - 1));
                path.add(destinations.remove(index));
                destinationSize--;
            }
            path.add(path.get(0));
        }

        return new TSPIndividual(dp, path);
    }

    /**
     *
     * @param dp
     * @param path
     */
    public TSPIndividual(DestinationPool dp, ArrayList<Destination> path) {
        this.destinations = dp;
        this.path = path;
        this.addView(new TSPIndividualUI());
    }

    /**
     *
     * @return
     */
    public DestinationPool getDestinations() {
        return destinations;
    }

    /**
     *
     * @return
     */
    public ArrayList<Destination> getPath() {
        return path;
    }

    @Override
    protected void set(Individual s) {
        if (s instanceof TSPIndividual) {
            TSPIndividual i = (TSPIndividual) s;
            this.destinations = i.destinations;
            this.path = i.path;
        }
    }

    /**
     *
     * @return
     */
    public double getTotalDistance() {
        double distance = 0;
        int size = this.path.size();

        for (int i = 0; i < size - 1; i++) {
            Destination d1 = this.path.get(i);
            Destination d2 = this.path.get(i + 1);

            
            double lat1 = Math.toRadians(d1.getPosition().getLatitude());
            double lat2 = Math.toRadians(d2.getPosition().getLatitude());
            double lon1 = Math.toRadians(d1.getPosition().getLongitude());
            double lon2 = Math.toRadians(d2.getPosition().getLongitude());

            //calcul précis
            double dp = 2 * Math.sqrt(Math.pow((lat1 - lat2),2 ) + Math.pow(lon1-lon2, 2));

            //sortie en km
            distance+=dp;

        }

        return distance;
    }

    @Override
    public String xmlSerialization() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
