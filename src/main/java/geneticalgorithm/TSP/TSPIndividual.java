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

    public static TSPIndividual createRandom(DestinationPool dp) {

        ArrayList<Destination> destinations = new ArrayList<>(dp.getDestinations());
        ArrayList<Destination> path = new ArrayList<>();
        int size = destinations.size();

        for (int i = 0; i < size; i++) {

            int index = (int) Math.round(Math.random() * (size - 1));
            path.add(destinations.get(index));
        }
        path.add(path.get(0));

        return new TSPIndividual(dp, path);
    }

    public TSPIndividual(DestinationPool dp, ArrayList<Destination> path) {
        this.destinations = dp;
        this.path = path;
        this.addView(new TSPIndividualUI());
    }

    public DestinationPool getDestinations() {
        return destinations;
    }

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

    public double getTotalDistance() {
        double distance = 0;
        int size = this.path.size();

        for (int i = 0; i < size - 1; i++) {
            Destination d1 = this.path.get(i);
            Destination d2 = this.path.get(i + 1);

            double r = 6366;
            double lat1 = Math.toRadians(d1.getPosition().getLatitude());
            double lat2 = Math.toRadians(d2.getPosition().getLatitude());
            double lon1 = Math.toRadians(d1.getPosition().getLongitude());
            double lon2 = Math.toRadians(d2.getPosition().getLongitude());

            //calcul précis
            double dp = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin((lat1 - lat2) / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin((lon1 - lon2) / 2), 2)));

            //sortie en km
            double d = dp * r;
            return d;

        }

        return distance;
    }

    @Override
    public String xmlSerialization() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
