/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import MvcPattern.Model;
import Tools.Chronometer;
import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Operators.Mutation.MutationOperator;
import geneticalgorithm.Operators.Operators;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Population;
import geneticalgorithm.Population.PopulationUI;
import geneticalgorithm.Problems.Problem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public class GeneticEngine extends Model {

    private Population population;
    private int initialSize;
    private boolean stop = true;
    private int stepCount = 0;
    private Chronometer chronometer;
    private Problem problem;
    private Operators operators;

    public GeneticEngine(Problem problem) {

        this.setPopulation(problem.createInitialPopulation());
        this.problem = problem;
        this.operators = problem.getSelectedOperators();

        this.chronometer = new Chronometer();

        this.addView(new GeneticEngineUI(this, (PopulationUI) population.getUI()));
    }

    public Population getPopulation() {
        return population;
    }

    public Problem getProblem() {
        return problem;
    }

    public int getStepCount() {
        return stepCount;
    }

    public final void setPopulation(Population population) {
        this.population = population;
        this.initialSize = population.size();
    }

    public void start() {
        this.stop = false;
        this.chronometer.start();
        this.engine();
    }

    public void stop() {

        this.stop = true;
        this.chronometer.stop();
        this.notifyViews(new EngineStopedRefreshEvent(this));
    }

    private boolean timeIsOut() {
        if (this.problem.getTimeout() == 0) {
            return false;
        } else {
            return (this.problem.getTimeout() - this.chronometer.getTime() <= 0);
        }
    }

    private void engine() {
        while (!this.stop && this.stepCount <= this.problem.getMaxStepCount() && !this.timeIsOut()) {
            this.evolve();
        }
        this.stop();
    }

    public Individual getBestSolution() {

        return this.population.getAlphaIndividual();
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
        Population pop = this.operators.getSelectionOperator().buildNextGeneration(this.population, this.initialSize);
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

    public void evolve() {

        this.stepCount++;
        this.crossOverStep();
        this.mutationStep();
        this.buildNextGeneration();
        this.notifyViews();

    }

    public void step() {
        this.chronometer.start();
        this.evolve();
        this.chronometer.stop();
    }

    @Override
    public void notifyViews() {
        super.notifyViews(new EngineRefreshEvent(this, this.problem.getTimeout(), this.stepCount));
    }
}
