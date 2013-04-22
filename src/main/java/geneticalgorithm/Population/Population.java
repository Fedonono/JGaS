/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import MvcPattern.Model;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Individuals.IndividualUI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public class Population extends Model {

    private ArrayList<Individual> individuals;
    private int observableVolume = 0;

    public Population() {
        this(0);
    }

    public Population(int observableVolume) {
        this.individuals = new ArrayList<>();
        this.observableVolume = observableVolume;
    }

    /**
     * Adds an individual to the 'this' Population.
     *
     * @param s
     */
    public void add(Individual s) {
        this.individuals.add(s);
    }

    public void addAll(Collection<? extends Individual> coll) {
        this.individuals.addAll(coll);
    }

    public void addAll(int index, Collection<? extends Individual> coll) {
        this.individuals.addAll(index, coll);
    }
    
    public Iterator<Individual> iterator(){
        return this.individuals.iterator();
    }

    public ArrayList<Individual> getIndividuals() {
        return individuals;
    }

    public void setObservableVolume(int observableVolume) {
        int size = this.individuals.size();
        if (observableVolume > size) {
            observableVolume = size;
        }
        this.observableVolume = observableVolume;
        super.notifyViews(new ObservableVolumeRefreshEvent(this, observableVolume));
    }

    public int getObservableVolume() {
        return observableVolume;
    }

    public void setSolutions(Population population) {
        this.individuals = new ArrayList<>();
        this.individuals.addAll(population.individuals);
    }

    @Override
    public void notifyViews() {

        LinkedList<IndividualUI> visualSample = new LinkedList<>();
        LinkedList<Individual> candidates = new LinkedList<>();
        candidates.addAll(this.individuals);

        int index;
        for (int i = 0; i < this.observableVolume; i++) {

            index = (int) (Math.random() * candidates.size());
            visualSample.add(candidates.remove(index).getUI());
        }

        super.notifyViews(new PopulationRefreshEvent(this, visualSample));
        //TODO BY ARNAUD -- CHECK IF LE BOUCLE EN DESSOUS FAIT PAS LE BOULOT de la ligne du dessus. EN PARLER A GUIOME
        for (Individual individual : individuals) {
            individual.notifyViews();
        }
    }
    
    public Individual getFirstIndividual() {
        if (this.individuals.size() > 0) {
            return this.individuals.get(0);
        }
        return null;
        // TODO EXCEPTION PAS INDIVIDU BY ARNAUD
        
    }

    public Individual getAlphaIndividual() {
        Iterator<Individual> individualIterator = this.individuals.iterator();
        Individual bestIndividual = individualIterator.next();
        Individual currentIndividual;

        while (individualIterator.hasNext()) {
            currentIndividual = individualIterator.next();

            if (currentIndividual.getScore() > bestIndividual.getScore()) {
                bestIndividual = currentIndividual;
            }
        }
        return bestIndividual;
    }
    
    public String xmlSerialisation() {
        String serialisedPopulation = "";
        //TODO
        return serialisedPopulation;
    }

    public void sort() {
        Collections.sort(individuals);
    }

    public int size() {
        return this.individuals.size();
    }
}
