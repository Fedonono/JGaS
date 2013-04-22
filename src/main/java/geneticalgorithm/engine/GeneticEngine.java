/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.engine;

import MvcPattern.Model;
import MvcPattern.View;
import Tools.Chronometer;
import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Operators.Mutation.MutationOperator;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Population;
import geneticalgorithm.Population.PopulationUI;
import geneticalgorithm.Problems.Problem;
import geneticalgorithm.Problems.ProblemUI;
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
    private boolean pause = true;
    private int stepCount = 0;
    private double evolutionCriterion = 1;
    private double previousTotalScore = 0;
    private boolean firstStepDone = false;
    private Chronometer chronometer;
    private Problem problem;

    public GeneticEngine(Problem problem) {

        this.setProblem(problem);

        this.addView(new GeneticEngineUI(this, (PopulationUI) population.getUI()));
    }

    @Override
    public final void addView(View geUI) {

        super.addView(geUI);
        geUI.refresh(new EngineRefreshEvent(this, this.chronometer.getTime(), this.stepCount));
    }

    public boolean isPaused() {
        return this.pause;
    }

    private void init() {

        this.setPopulation(this.problem.createInitialPopulation());
        this.chronometer = new Chronometer();
    }

    public Population getPopulation() {
        return population;
    }

    public Problem getProblem() {
        return problem;
    }

    public final void setProblem(Problem problem) {

        this.problem = problem;
        this.pause = true;
        this.notifyViews(new EngineProblemRefreshEvent(this, (ProblemUI) this.problem.getUI()));
        this.init();
    }

    public int getStepCount() {
        return stepCount;
    }

    private void setPopulation(Population population) {

        this.population = population;
        this.initialSize = population.size();
        this.firstStepDone = false;

        this.evaluationStep();

        this.notifyViews(new EnginePopulationRefreshEvent(this, (PopulationUI) this.population.getUI()));

    }

    public void resume() {
        if (this.pause) {
            this.pause = false;
            this.chronometer.start();
            this.engine();
        }
    }

    public void pause() {
        this.pause = true;
        this.chronometer.stop();
    }

    private void engine() {
        while (!this.pause && !this.problem.stopCriteriaAreReached(this.stepCount, this.chronometer.getTime(), this.evolutionCriterion)) {
            System.out.println(this.stepCount +"/"+this.problem.getMaxStepCount());
            this.evolve();
        }
        this.pause();
    }

    public Individual getBestSolution() {

        return this.population.getAlphaIndividual();
    }

    /**
     * Evaluate all the individuals using their evaluation method.
     */
    private void evaluationStep() {

        ArrayList<Individual> individuals = this.population.getIndividuals();
        double currentTotalScore = 0;

        for (Individual individual : individuals) {
            this.problem.getSelectedEvaluationOperator().evaluate(individual);
            currentTotalScore += individual.getScore();
        }

        if (this.firstStepDone) {
            this.evolutionCriterion = Math.abs(this.previousTotalScore - currentTotalScore) / (Math.abs(this.previousTotalScore) + Math.abs(currentTotalScore));
        }else{
            this.firstStepDone = true;
        }
        this.previousTotalScore = currentTotalScore;
    }

    /**
     * Selects the survivals of the current generation using the selected
     * selection operator.
     */
    private void buildNextGeneration() {

        this.evaluationStep();
        Population pop = this.problem.getSelectedSelectionOperator().buildNextGeneration(this.population, this.initialSize);
        this.population.setSolutions(pop);
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

        MutationOperator mutationOperator = this.problem.getSelectedMutationOperator();

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
