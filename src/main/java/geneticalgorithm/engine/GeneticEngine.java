/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.engine;

import MvcPattern.Model;
import MvcPattern.View;
import Util.Tools.Chronometer;
import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Operators.Mutation.MutationOperator;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Population;
import geneticalgorithm.Population.PopulationUI;
import geneticalgorithm.Problem.Problem;
import geneticalgorithm.Problem.ProblemUI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public class GeneticEngine extends Model implements Runnable {

    private Population population;
    private boolean pause = true;
    private int stepCount = 0;
    private double evolutionCriterion = 1;
    private double previousBestScore = 0;
    private boolean firstStepDone = false;
    private Chronometer chronometer;
    private Problem problem;
    private Thread engine;

    /**
     *
     * @param problem
     */
    public GeneticEngine(Problem problem) {

        this.setProblem(problem);

        this.addView(new GeneticEngineUI(this, (PopulationUI) population.getUI()));
    }

    /**
     *
     * @param geUI
     */
    @Override
    public final void addView(View geUI) {

        super.addView(geUI);
        geUI.refresh(new EngineRefreshEvent(this, this.chronometer.getTime(), this.stepCount, this.evolutionCriterion));
    }

    /**
     *
     * @return true if the engine is paused. false otherwise.
     */
    public boolean isPaused() {
        return this.pause;
    }

    /**
     *
     */
    public void refreshPopulation()  {
        if (this.pause) {
            this.population.notifyViews();
        } else {
            this.pause();
            this.resume();
        }
    }

    public void reset(){
        this.pause();
        this.chronometer.reset();
        this.evolutionCriterion = 1;
        this.stepCount = 0;
        this.setPopulation(this.problem.createInitialPopulation());
        
        this.notifyViews();
        
    }
    
    private void init() {

        this.setPopulation(this.problem.createInitialPopulation());
        this.chronometer = new Chronometer();
    }

    /**
     *
     * @return
     */
    public Population getPopulation() {
        return population;
    }

    /**
     *
     * @return the treated problem.
     */
    public Problem getProblem() {
        return problem;
    }

    /**
     *
     * @param problem
     */
    public final void setProblem(Problem problem) {

        this.problem = problem;
        this.pause = true;
        this.notifyViews(new EngineProblemRefreshEvent(this, (ProblemUI) this.problem.getUI()));
        this.init();
    }

    /**
     *
     * @return
     */
    public int getStepCount() {
        return stepCount;
    }

    private void setPopulation(Population population) {

        this.population = population;
        this.firstStepDone = false;

        this.evaluationStep();

        this.population.notifyViews();
        this.notifyViews(new EnginePopulationRefreshEvent(this, (PopulationUI) this.population.getUI()));

    }

    /**
     * resume this.
     */
    public void resume() {
        if (this.pause) {
            this.pause = false;
            this.chronometer.start();
            this.engine();
        }
    }

    /**
     * pause this.
     */
    public void pause(){
        this.pause = true;
        this.chronometer.stop();

        if (this.engine != null) {
            try {
                this.engine.join();
            } catch (InterruptedException e) {
                //TODO
            }
        }

        this.population.notifyViews();
    }

    /**
     * run the engine in an independant thread.
     */
    @Override
    public void run() {
        while (!this.pause && !this.problem.stopCriteriaAreReached(this.stepCount, this.chronometer.getTime(), this.evolutionCriterion)) {

            this.evolve();
        }

        //this.population.notifyViews();
    }

    
    private void engine() {
        this.engine = new Thread(this);
        engine.start();
    }

    /**
     *
     * @return
     */
    public Individual getBestSolution() {

        return this.population.getAlphaIndividual();
    }

    /**
     * Evaluate all the individuals using their evaluation method.
     */
    private void evaluationStep() {

        ArrayList<Individual> individuals = this.population.getIndividuals();

        for (Individual individual : individuals) {
            this.problem.getSelectedEvaluationOperator().evaluate(individual);
        }

        this.population.sort();
        this.computeEvolutionCriterion();
    }

    private void computeEvolutionCriterion() {

        double bestScore = this.population.getAlphaIndividual().getScore();

        if (this.firstStepDone) {
            this.evolutionCriterion = Math.abs(this.previousBestScore - bestScore) / Math.abs(this.previousBestScore);
        } else {
            this.firstStepDone = true;
        }
        this.previousBestScore = bestScore;


    }

    /**
     * Selects the survivals of the current generation using the selected
     * selection operator.
     */
    private void buildNextGeneration() {

        this.evaluationStep();
        Population pop = this.problem.getSelectedSelectionOperator().buildNextGeneration(this.population, this.problem.getPopulationSize());
        this.population.setIndividuals(pop);
    }

    /**
     * Randomly crosses Individuals between them using the selected cross over
     * operator.
     */
    private void crossOverStep() {

        LinkedList<Individual> crossQueue = new LinkedList<>();
        ArrayList<Individual> individuals = this.population.getIndividuals();

        CrossOverOperator crossoverOperator = this.problem.getSelectedCrossOverOperation();
        for (Individual individual : individuals) {

            if (Math.random() < this.problem.getCrossProbability()) {

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

        MutationOperator mutationOperator = this.problem.getSelectedMutationOperator();
        ArrayList<Individual> mutants = new ArrayList<>();
        ArrayList<Individual> individuals = this.population.getIndividuals();

        for (Individual individual : individuals) {

            if (Math.random() < this.problem.getMutationProbability()) {

                mutants.add(mutationOperator.mutate(individual));
            }
        }
        this.population.addAll(mutants);
    }

    /**
     * process all the steps of genetic algorithms.
     */
    public void evolve() {

        this.crossOverStep();
        this.mutationStep();
        this.buildNextGeneration();
        this.stepCount++;

        this.notifyViews();
    }

    /**
     * process only one step generation.
     */
    public void step() {
        if (!this.problem.stopCriteriaAreReached(this.stepCount, this.chronometer.getTime(), this.evolutionCriterion)) {
            this.chronometer.start();
            this.evolve();
            this.chronometer.stop();
            this.population.notifyViews();
        }
    }

    /**
     *
     */
    @Override
    public void notifyViews() {
        long time = this.chronometer.getTime();
        super.notifyViews(new EngineRefreshEvent(this, time, this.stepCount, this.evolutionCriterion));
    }
}
