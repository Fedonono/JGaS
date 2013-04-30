/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problem;

import MvcPattern.Model;
import MvcPattern.View;
import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Operators.Evaluation.EvaluationOperator;
import geneticalgorithm.Operators.Mutation.MutationOperator;
import geneticalgorithm.Operators.Operators;
import geneticalgorithm.Operators.Selection.ProportionalPerfomanceSelectionOperator;
import geneticalgorithm.Operators.Selection.ProportionalRankingSelectionOperator;
import geneticalgorithm.Operators.Selection.RandomSelectionOperator;
import geneticalgorithm.Operators.Selection.SelectionOperator;
import geneticalgorithm.Operators.Selection.TournamentSelectionOperator;
import geneticalgorithm.Operators.Selection.TruncationSelectionOperator;
import geneticalgorithm.Population.Population;
import geneticalgorithm.Problem.StopCriteria.StopCriteria;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public abstract class Problem extends Model {

    private LinkedList<MutationOperator> availableMutationOperators = new LinkedList<>();
    private LinkedList<CrossOverOperator> availableCrossOverOperators = new LinkedList<>();
    private LinkedList<SelectionOperator> availableSelectionOperators = new LinkedList<>();
    private LinkedList<EvaluationOperator> availableEvaluationOperator = new LinkedList<>();
    private int populationSize = 20;
    private String label;
    private double mutationProbability = 0.1;
    private double crossProbability = 0.2;
    private Operators operators = new Operators();
    private StopCriteria stopCriteria;

    /**
     *
     */
    public Problem() {
        this.stopCriteria = new StopCriteria();
        this.addSelectionOperator(RandomSelectionOperator.getInstance());
        this.addSelectionOperator(TruncationSelectionOperator.getInstance());
        this.addSelectionOperator(TournamentSelectionOperator.getInstance());
        this.addSelectionOperator(ProportionalPerfomanceSelectionOperator.getInstance());
        this.addSelectionOperator(ProportionalRankingSelectionOperator.getInstance());
        
        this.addView(new ProblemUI(this));
    }

    /**
     *
     * @return
     */
    public StopCriteria getStopCriteria() {
        return this.stopCriteria;
    }

    /**
     *
     * @return the timeout stop criterion.
     */
    public int getTimeout() {
        return stopCriteria.getTimeout();
    }

    /**
     *
     * @return
     */
    public LinkedList<MutationOperator> getAvailableMutationOperators() {
        return availableMutationOperators;
    }

    /**
     *
     * @return
     */
    public LinkedList<CrossOverOperator> getAvailableCrossOverOperators() {
        return availableCrossOverOperators;
    }

    /**
     *
     * @return
     */
    public LinkedList<SelectionOperator> getAvailableSelectionOperators() {
        return availableSelectionOperators;
    }

    /**
     *
     * @return
     */
    public LinkedList<EvaluationOperator> getAvailableEvaluationOperator() {
        return availableEvaluationOperator;
    }

    /**
     *
     * @return the max step count stop criterion.
     */
    public int getMaxStepCount() {
        return stopCriteria.getMaxStepCount();
    }

    /**
     *
     * @return 'this' label.
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @return 'this' mutation probability.
     */
    public double getMutationProbability() {
        return mutationProbability;
    }

    /**
     *
     * @return 'this' cross probability.
     */
    public double getCrossProbability() {
        return crossProbability;
    }

    /**
     *
     * @return 'this' selected MutationOperator.
     */
    public MutationOperator getSelectedMutationOperator() {
        return operators.getMutationOperator();
    }

    /**
     *
     * @return 'this' selected CrossOverOperator.
     */
    public CrossOverOperator getSelectedCrossOverOperator() {
        return operators.getCrossoverOperator();
    }

    /**
     *
     * @return 'this.selected SelectionOperator.
     */
    public SelectionOperator getSelectedSelectionOperator() {
        return operators.getSelectionOperator();
    }

    /**
     *
     * @return 'this' selected EvaluationOperator.
     */
    public EvaluationOperator getSelectedEvaluationOperator() {
        return operators.getEvaluationOperator();
    }

    /**
     *
     * @param mutationProbability
     */
    public void setMutationProbability(double mutationProbability) {
        this.mutationProbability = mutationProbability;
        this.notifyViews();
    }

    /**
     *
     * @param crossProbability
     */
    public void setCrossProbability(double crossProbability) {
        this.crossProbability = crossProbability;
        this.notifyViews();
    }

    /**
     *
     * @param selectedMutationOperator
     */
    public void setSelectedMutationOperator(MutationOperator selectedMutationOperator) {
        this.operators.setMutationOperator(selectedMutationOperator);
        this.notifyViews();
    }

    /**
     *
     * @param selectedCrossOverOperation
     */
    public void setSelectedCrossOverOperation(CrossOverOperator selectedCrossOverOperation) {
        this.operators.setCrossoverOperator(selectedCrossOverOperation);
        this.notifyViews();
    }

    /**
     *
     * @param selectedSelectionOperator
     */
    public void setSelectedSelectionOperator(SelectionOperator selectedSelectionOperator) {
        this.operators.setSelectionOperator(selectedSelectionOperator);
        this.notifyViews();
    }

    /**
     *
     * @param selectedEvaluationOperator
     */
    public void setSelectedEvaluationOperator(EvaluationOperator selectedEvaluationOperator) {
        this.operators.setEvaluationOperator(selectedEvaluationOperator);
        this.notifyViews();
    }

    /**
     *
     * @param populationSize
     */
    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
        this.notifyViews();
    }

    /**
     *
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     *
     * @return 'this' populatio size.
     */
    public int getPopulationSize() {
        return populationSize;
    }

    @Override
    public void notifyViews() {
        super.notifyViews(new ProblemRefreshEvent(this));
    }

    /**
     *
     * @return
     */
    public abstract Population createInitialPopulation();

    /**
     *
     * @return
     */
    public String xmlSerialize() {
        String serialisedPopulation = "";
        //TODO
        return serialisedPopulation;
    }

    /**
     *
     * @param operators
     */
    protected void setSelectedOperators(Operators operators) {
        this.operators = operators;
    }

    /**
     *
     * @return
     */
    public Operators getSelectedOperators() {
        return this.operators;
    }

    /**
     *
     * @param operator
     */
    public void addMutationOperator(MutationOperator operator) {
        this.availableMutationOperators.add(operator);
        this.setSelectedMutationOperator(operator);

    }

    /**
     *
     * @param operator
     */
    public void addCrossOverOperator(CrossOverOperator operator) {
        this.availableCrossOverOperators.add(operator);
        this.setSelectedCrossOverOperation(operator);

    }

    /**
     *
     * @param operator
     */
    public final void addSelectionOperator(SelectionOperator operator) {
        this.availableSelectionOperators.add(operator);
        this.setSelectedSelectionOperator(operator);

    }

    /**
     *
     * @param operator
     */
    public void addEvaluationOperator(EvaluationOperator operator) {
        this.availableEvaluationOperator.add(operator);
        this.setSelectedEvaluationOperator(operator);

    }

    @Override
    public final void addView(View v) {
        super.addView(v);
        v.refresh(new ProblemRefreshEvent(this));
    }

    /**
     *
     * @return
     */
    public double getEvolutionCriterion() {
        return this.stopCriteria.getEvolutionCriterion();
    }

    /**
     *
     * @param stepCount
     * @param time
     * @param evolutionCoeff
     * @return return true if the stop criteria are reached. false other wise.
     */
    public boolean stopCriteriaAreReached(int stepCount, long time, double evolutionCoeff) {
        return this.stopCriteria.areReached(stepCount, time, evolutionCoeff);
    }

    /**
     *
     * @return 'this' label.
     */
    @Override
    public final String toString() {
        return this.getLabel();
    }
}
