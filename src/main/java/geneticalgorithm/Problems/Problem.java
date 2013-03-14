/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems;

import MvcPattern.Model;
import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Operators.Evaluation.EvaluationOperator;
import geneticalgorithm.Operators.Mutation.MutationOperator;
import geneticalgorithm.Operators.Selection.SelectionOperator;
import geneticalgorithm.Population.Population;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public abstract class Problem extends Model {

    private LinkedList<MutationOperator> availableMutationOperators;
    private LinkedList<CrossOverOperator> availableCrossOverOperators;
    private LinkedList<SelectionOperator> availableSelectionOperators;
    private LinkedList<EvaluationOperator> availableEvaluationOperator;
    private int maxStepCount;
    private int populationSize;
    private String label;
    private double mutationProbability;
    private double crossProbability;
    private MutationOperator selectedMutationOperator;
    private CrossOverOperator selectedCrossOverOperation;
    private SelectionOperator selectedSelectionOperator;
    private EvaluationOperator selectedEvaluationOperator;

    public LinkedList<MutationOperator> getAvailableMutationOperators() {
        return availableMutationOperators;
    }

    public LinkedList<CrossOverOperator> getAvailableCrossOverOperators() {
        return availableCrossOverOperators;
    }

    public LinkedList<SelectionOperator> getAvailableSelectionOperators() {
        return availableSelectionOperators;
    }

    public LinkedList<EvaluationOperator> getAvailableEvaluationOperator() {
        return availableEvaluationOperator;
    }

    public int getMaxStepCount() {
        return maxStepCount;
    }

    public String getLabel() {
        return label;
    }

    public double getMutationProbability() {
        return mutationProbability;
    }

    public double getCrossProbability() {
        return crossProbability;
    }

    public MutationOperator getSelectedMutationOperator() {
        return selectedMutationOperator;
    }

    public CrossOverOperator getSelectedCrossOverOperation() {
        return selectedCrossOverOperation;
    }

    public SelectionOperator getSelectedSelectionOperator() {
        return selectedSelectionOperator;
    }

    public EvaluationOperator getSelectedEvaluationOperator() {
        return selectedEvaluationOperator;
    }

    public void setMaxStepCount(int maxStepCount) {
        this.maxStepCount = maxStepCount;
    }

    public void setMutationProbability(double mutationProbability) {
        this.mutationProbability = mutationProbability;
    }

    public void setCrossProbability(double crossProbability) {
        this.crossProbability = crossProbability;
    }

    public void setSelectedMutationOperator(MutationOperator selectedMutationOperator) {
        this.selectedMutationOperator = selectedMutationOperator;
    }

    public void setSelectedCrossOverOperation(CrossOverOperator selectedCrossOverOperation) {
        this.selectedCrossOverOperation = selectedCrossOverOperation;
    }

    public void setSelectedSelectionOperator(SelectionOperator selectedSelectionOperator) {
        this.selectedSelectionOperator = selectedSelectionOperator;
    }

    public void setSelectedEvaluationOperator(EvaluationOperator selectedEvaluationOperator) {
        this.selectedEvaluationOperator = selectedEvaluationOperator;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getPopulationSize() {
        return populationSize;
    }
    
    @Override
    public void notifyViews(){
       super.notifyViews(new ProblemRefreshEvent(this)); 
    }

    public abstract Population createInitialPopulation();

    public String xmlSerialize() {
        String serialisedPopulation = "";
        //TODO
        return serialisedPopulation;
    }
}
