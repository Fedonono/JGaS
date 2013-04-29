/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.TSP;

import Util.WorldMap.Destination;
import Util.WorldMap.DestinationPool;
import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Population.Individuals.Individual;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public class TSPCrossOverOperator extends CrossOverOperator {

    private static String LABEL = "Cross paths";

    public TSPCrossOverOperator() {
        super(LABEL);
    }

    @Override
    public Individual cross(Individual male, Individual female) {

        TSPIndividual tspMale = (TSPIndividual) male;
        TSPIndividual tspFemale = (TSPIndividual) female;

        ArrayList<Destination> malePath = tspMale.getPath();
        ArrayList<Destination> femalePath = tspFemale.getPath();

        ArrayList<Destination> childPath = new ArrayList<>();

        int size = malePath.size();
        int middle = (int) Math.round(size / 2);

        for (int i = 0; i < middle; i++) {
            childPath.add(malePath.get(i));
        }
        for (int i = middle; i < size; i++) {
            childPath.add(femalePath.get(i));
        }

        DestinationPool dp = tspMale.getDestinations();

        return new TSPIndividual(dp, this.repairPath(childPath, dp));
    }

    private ArrayList<Destination> repairPath(ArrayList<Destination> path, DestinationPool dp) {

        //supression des doublons
        ArrayList<Integer> destinationId = new ArrayList<>();
        int size = path.size();
        for (int i = 0; i < size; i++) {

            int id = path.get(i).getId();
            Iterator<Integer> ids = destinationId.iterator();
            boolean stop = false;

            while (ids.hasNext() && !stop) {

                if (id == ids.next()) {
                    path.remove(i);
                    stop = true;
                }
                destinationId.add(id);
            }
        }

        //rajout des destinations manquabtes
        ArrayList<Destination> destinations = new ArrayList<>(dp.getDestinations());
        size = destinations.size();

        for (int i = 0; i < size; i++) {
            
            int destId = destinations.get(i).getId();
            Iterator<Destination> waypoints = path.iterator();
            boolean stop = false;

            while (waypoints.hasNext() && !stop) {
                int id = waypoints.next().getId();
                
                if (id == destId) {
                    destinations.remove(i);
                    stop = true;
                }
            }
        }
        
        for (Destination destination : destinations) {
            path.add(destination);
        }
        
        return path;
    }
}
