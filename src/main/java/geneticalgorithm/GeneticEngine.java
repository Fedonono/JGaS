/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import MvcPattern.Model;
import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Operators.Mutation.MutationOperator;
import geneticalgorithm.Operators.Operators;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Population;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public class GeneticEngine extends Model {

    private Population population;
    private boolean stop = true;
    private int stepCount = 0;
    private int maxStepCount;
    private Operators operators;

    public GeneticEngine() {
        this(null, null);
    }

    public GeneticEngine(Population pop) {
        this(pop, null);
    }

    public GeneticEngine(Operators op) {
        this(null, op);
    }

    public GeneticEngine(Population population, Operators operators) {
        this.population = population;
        this.operators = operators;
    }

    public Population getPopulation() {
        return population;
    }

    public int getStepCount() {
        return stepCount;
    }

    public int getMaxStepCount() {
        return maxStepCount;
    }

    public Operators getOperators() {
        return operators;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    public void setMaxStepCount(int maxStepCount) {
        this.maxStepCount = maxStepCount;
    }

    public void setOperators(Operators operators) {
        this.operators = operators;
    }

    public void start() {

        this.stop = false;
        this.engine();
    }

    public void stop() {

        this.stop = true;
    }

    private void engine() {

        while (!this.stop && this.stepCount <= maxStepCount) {

            this.stepCount++;
            this.evolve();
        }
    }

    public Individual getBestSolution() {

        return this.population.bestIndividual();
    }

    /**
     * Evaluate all the individuals using their evaluation method.
     */
    private void evaluationStep() {

        ArrayList<Individual> individuals = this.population.getIndividuals();

        for (Individual individual : individuals) {
            this.operators.getEvaluationOperator().evaluate(individual);
        }
    }

    /**
     * Selects the survivals of the current generation using the selected
     * selection operator.
     */
    private void buildNextGeneration() {

        this.evaluationStep();
        Population pop = this.operators.getSelectionOperator().buildNextGeneration(this.population);
        this.population.setSolutions(pop);
    }

    /**
     * Randomly crosses Individuals between them using the selected cross over
     * operator.
     */
    private void crossOverStep() {
        LinkedList<Individual> crossQueue = new LinkedList<>();
        ArrayList<Individual> individuals = this.population.getIndividuals();

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
            this.population.add(crossoverOperator.cross(male, female));
        }
    }
    
    
    /**
     * Randomly makes some individual victim of mutations using the selected
     * mutation operator.
     */
    private void mutationStep() {

        MutationOperator mutationOperator = this.operators.getMutationOperator();

        ArrayList<Individual> individuals = this.population.getIndividuals();
        
        for (Individual individual : individuals) {

            if (Math.random() < mutationOperator.getProbability()) {

                mutationOperator.mutate(individual);
            }
        }
    }
    
    
    
    private void evolve() {
        this.crossOverStep();
        this.mutationStep();
        this.buildNextGeneration();
    }
}
