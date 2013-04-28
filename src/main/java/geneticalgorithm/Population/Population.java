/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import MvcPattern.Model;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Individuals.IndividualComparator;
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
    private int observableVolume = 1;
    private boolean semaphoreAccess = true;
    
    private Individual alphaIndividual;

    /**
     *
     */
    public Population() {
        this(1);
    }

    /**
     *
     * @param observableVolume
     */
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

    /**
     * adds all individuals from coll to 'this'.
     * @param coll
     */
    public void addAll(Collection<? extends Individual> coll) {
        this.individuals.addAll(coll);
    }

    /**
     * adds all individuals from coll begining to the index 'index' to 'this'.
     * @param index
     * @param coll
     */
    public void addAll(int index, Collection<? extends Individual> coll) {
        this.individuals.addAll(index, coll);
    }

    /**
     *
     * @return an iterator on individuals.
     */
    public Iterator<Individual> iterator() {
        return this.individuals.iterator();
    }

    /**
     *
     * @return 'this' individuals.
     */
    public ArrayList<Individual> getIndividuals() {
        return individuals;
    }

    /**
     *
     * @param index
     * @return
     */
    public Individual get(int index) {
        return this.individuals.get(index);
    }

    /**
     * set the obserable volume.
     * @param observableVolume
     */
    public void setObservableVolume(int observableVolume) {
        int size = this.individuals.size();
        if (observableVolume > size) {
            observableVolume = size;
        } else if (observableVolume < 1) {
            observableVolume = 1;
        }
        this.observableVolume = observableVolume;
        super.notifyViews(new ObservableVolumeRefreshEvent(this, observableVolume, this.size()));
    }

    /**
     *
     * @return
     */
    public int getObservableVolume() {
        return observableVolume;
    }

    /**
     *
     * @param population
     */
    public void setIndividuals(Population population) {
        this.individuals = new ArrayList<>();
        this.individuals.addAll(population.individuals);
    }

    /**
     *
     */
    @Override
    public final void notifyViews() {
        if (this.semaphoreAccess) {
            this.semaphoreAccess = false;
            super.notifyViews(new ObservableVolumeRefreshEvent(this, this.observableVolume, this.size()));
            
            this.sort();
            int size = 0;
            LinkedList<Individual> sample = new LinkedList<>();

            Iterator<Individual> it = this.iterator();

            while (it.hasNext() && size < this.observableVolume) {

                Individual individual = it.next();
                sample.add(individual);
                size++;
            }
            super.notifyViews(new PopulationRefreshEvent(this, sample));
            this.semaphoreAccess = true;
        }

    }

    private void researchAlphaIndividual(){
        Iterator<Individual> individualIterator = this.iterator();
        Individual bestIndividual = individualIterator.next();
        Individual currentIndividual;

        while (individualIterator.hasNext()) {
            currentIndividual = individualIterator.next();

            if (currentIndividual.getScore() > bestIndividual.getScore()) {
                bestIndividual = currentIndividual;
            }
        }
        this.alphaIndividual= bestIndividual;
    }
    
    /**
     *
     * @return the best individuals;
     */
    public Individual getAlphaIndividual() {

       this.researchAlphaIndividual();
       return this.alphaIndividual;
    }

    /**
     * @deprecated .
     * @return
     */
    public String xmlSerialisation() {
        String serialisedPopulation = "";
        //TODO
        return serialisedPopulation;
    }

    /**
     * sort the individuals from the best to the worst.
     */
    public void sort() {
        Collections.sort(individuals, new IndividualComparator());
    }

    /**
     *
     * @return
     */
    public int size() {
        return this.individuals.size();
    }
    
    /**
     *
     * @return
     */
    @Override
    public Population clone(){
        
        Population pop = new Population(this.observableVolume);
        pop.addAll(this.individuals);
        
        return pop;
    }
    
    /**
     * remove the individual at the index 'index' in the individuals.
     * @param index
     * @return
     */
    public Individual remove(int index){
        return this.individuals.remove(index);
    }
    
    /**
     *
     * @return true if the popualtion does not contain any individual. False otherwise.
     */
    public boolean isEmpty(){
        return this.individuals.isEmpty();
    }
}
