/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import MvcPattern.Model;
import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Operators.Mutation.MutationOperator;
import geneticalgorithm.Operators.Operators;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Individuals.IndividualUI;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public class Population extends Model {

    private LinkedList<Individual> individuals;
    private int observableVolume = 0;
    private Operators operators;

    public Population() {
        this(null);
    }

    public Population(Operators operators) {
        this(operators, 0);
    }

    public Population(Operators operators, int observableVolume) {
        this.operators = operators;
        this.individuals = new LinkedList<>();
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

    public void setOperators(Operators operators) {
        this.operators = operators;
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

    /**
     * Evaluate all the individuals using their evaluation method.
     */
    private void evaluationStep() {

        for (Individual individual : individuals) {

            this.operators.getEvaluationOperator().evaluate(individual);
        }
    }

    /**
     * Selects the survivals of the current generation using the selected
     * selection operator.
     */
    public void buildNextGeneration() {

        this.evaluationStep();
        Population population = this.operators.getSelectionOperator().buildNextGeneration(this);
        this.setSolutions(population);
    }

    protected void setSolutions(Population population) {
        this.individuals = new LinkedList<>();
        this.individuals.addAll(population.individuals);
    }

    /**
     * Randomly crosses Individuals between them using the selected cross over
     * operator.
     */
    public void crossOverStep() {
        LinkedList<Individual> crossQueue = new LinkedList<>();
        CrossOverOperator crossoverOperator = this.operators.getCrossoverOperator();
        for (Individual individual : individuals) {

            if (Math.random() < crossoverOperator.getProbability()) {

                crossQueue.add(individual);
            }
        }

        int queueSize = crossQueue.size();
        Individual male;
        Individual female = null;
        int nbCandidates;
        double sexAppeal;

        while (queueSize > 1) {

            male = crossQueue.remove(0);
            queueSize--;

            nbCandidates = queueSize;
            boolean done = false;

            while (nbCandidates > 0 && !done) {

                Iterator<Individual> solutionIterator = crossQueue.iterator();
                female = solutionIterator.next();
                sexAppeal = 1 / nbCandidates;

                if (Math.random() < sexAppeal) {

                    solutionIterator.remove();
                    queueSize--;
                    done = true;

                } else {

                    nbCandidates--;
                }
            }
            crossoverOperator.cross(male,female);
        }
    }

    /**
     * Randomly makes some individual victim of mutations using the selected
     * mutation operator.
     */
    public void mutationStep() {

        MutationOperator mutationOperator = this.operators.getMutationOperator();
        
        for (Individual individual : individuals) {

            if (Math.random() < mutationOperator.getProbability()) {

                mutationOperator.mutate(individual);
            }
        }
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
    }

    public Individual bestIndividual() {

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

    public String xmlSerialise() {
        String serialisedPopulation = "";
        //TODO
        return serialisedPopulation;
    }
    
    public void evolve(){
        this.crossOverStep();
        this.mutationStep();
        this.buildNextGeneration();
    }
}
