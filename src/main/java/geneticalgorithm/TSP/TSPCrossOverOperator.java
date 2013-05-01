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

    /**
     *
     */
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

        for (int i = 0; i < size - 1; i += 2) {
            childPath.add(malePath.get(i));
            if (i + 1 < size) {
                childPath.add(femalePath.get(i + 1));
            }
        }

        DestinationPool dp = tspMale.getDestinations();


        return new TSPIndividual(dp, this.repairPath(childPath, dp));
    }

    private ArrayList<Destination> removeDoublons(ArrayList<Destination> path) {

        ArrayList<Destination> newPath = new ArrayList<>();

        //supression des doublons        
        for (Destination destination : path) {

            int id = destination.getId();

            Iterator<Destination> destinations = newPath.iterator();
            boolean stop = false;

            while (destinations.hasNext() && !stop) {
                if (id == destinations.next().getId()) {
                    stop = true;
                }
            }
            if (stop == false) {
                newPath.add(destination);
            }
        }
        return newPath;

    }

    private ArrayList<Destination> addMissingDestinations(ArrayList<Destination> path, DestinationPool dp) {
        //rajout des destinations manquabtes
        ArrayList<Destination> destinations = new ArrayList<>(dp.getDestinations());
        int size = destinations.size();

        for (int i = 0; i < size; i++) {

            int destId = destinations.get(i).getId();
            Iterator<Destination> waypoints = path.iterator();
            boolean stop = false;

            while (waypoints.hasNext() && !stop) {
                int id = waypoints.next().getId();

                if (id == destId) {
                    destinations.remove(i);
                    size--;
                    i--;
                    stop = true;
                }
            }
        }

        for (Destination destination : destinations) {
            path.add(destination);
        }

        path.add(path.get(0));
        return path;
    }

    private ArrayList<Destination> repairPath(ArrayList<Destination> path, DestinationPool dp) {
        return this.addMissingDestinations(this.removeDoublons(path), dp);
    }
}
