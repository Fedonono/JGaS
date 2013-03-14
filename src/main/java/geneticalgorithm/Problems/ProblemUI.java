/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems;

import GraphicalComponents.CustomSpinner;
import GraphicalComponents.IdentifiableComponent;
import GraphicalComponents.Observable;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import GraphicalComponents.OptionLine;
import GraphicalComponents.SelectMenu;
import GraphicalComponents.ValidateButton;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Operators.Evaluation.EvaluationOperator;
import geneticalgorithm.Operators.Mutation.MutationOperator;
import geneticalgorithm.Operators.Selection.SelectionOperator;
import javax.swing.JPanel;

/**
 *
 * @author simonneau
 */
public class ProblemUI extends JPanel implements View, Observer {

    private ProblemController controller;
    private static String mutationProbabilityLabel = "mutation Probability";
    private OptionLine mutationProbability;
    private int mutationProbabilityId;
    private static String crossProbabilityLabel = "cross Probability";
    private OptionLine crossProbability;
    private int crossProbabilityId;
    private static String poplationSizeLabel = "population size";
    private CustomSpinner populationSize;
    private int populationId;
    private static String maxStepCountLabel = "max number of generation steps";
    private CustomSpinner maxStepCount;
    private int maxStepCountId;
    private static String timeoutLabel = "timeout";
    private CustomSpinner timeout;
    private int timeoutId;
    private static String availableCrossOverOperatorsLabel = "available cross over operators";
    private SelectMenu<CrossOverOperator> availableCrossOverOperators;
    private int availableCrossOverOperatorsId;
    private static String availableMutationOperatorsLabel = "available mutation operators";
    private SelectMenu<MutationOperator> availableMutationOperators;
    private int availableMutationOperatorsId;
    private static String availableSelectionOperatorsLabel = "available selection operators";
    private SelectMenu<SelectionOperator> availableSelectionOperators;
    private int availableSelectionOperatorsId;
    private static String availableEvaluationOperatorsLabel = "available evaluation operators";
    private SelectMenu<EvaluationOperator> availableEvaluationOperators;
    private int availableEvaluationOperatorId;
    private static String validateButtonLabel = "validate";
    private ValidateButton validateButton;
    private int validateButtonId;

    public ProblemUI() {

        this.mutationProbability = new OptionLine(mutationProbabilityLabel, 0, 1, 0);
        this.mutationProbabilityId = this.mutationProbability.getId();
        this.add(this.mutationProbability);

        this.crossProbability = new OptionLine(crossProbabilityLabel, 0, 1, 0);
        this.crossProbabilityId = this.crossProbability.getId();
        this.add(this.crossProbability);

        this.availableCrossOverOperators = new SelectMenu(availableCrossOverOperatorsLabel);
        this.availableCrossOverOperatorsId = this.availableCrossOverOperators.getId();
        this.add(this.availableCrossOverOperators);

        this.availableEvaluationOperators = new SelectMenu(availableEvaluationOperatorsLabel);
        this.availableEvaluationOperatorId = this.availableEvaluationOperators.getId();
        this.add(this.availableEvaluationOperators);

        this.availableMutationOperators = new SelectMenu(availableMutationOperatorsLabel);
        this.availableMutationOperatorsId = this.availableMutationOperators.getId();
        this.add(this.availableMutationOperators);

        this.availableSelectionOperators = new SelectMenu(availableSelectionOperatorsLabel);
        this.availableSelectionOperatorsId = this.availableSelectionOperators.getId();
        this.add(this.availableSelectionOperators);

        this.maxStepCount = new CustomSpinner(1, Integer.MAX_VALUE);
        this.maxStepCountId = this.maxStepCount.getId();
        this.add(this.maxStepCount);

        this.timeout = new CustomSpinner(1, Integer.MAX_VALUE);
        this.timeoutId = this.timeout.getId();
        this.add(this.timeout);

        this.populationSize = new CustomSpinner(1, Integer.MAX_VALUE);
        this.populationId = this.populationSize.getId();
        this.add(this.populationSize);

        this.validateButton = new ValidateButton(validateButtonLabel);
        this.validateButtonId = this.validateButton.getId();
        this.add(this.validateButton);
    }

    @Override
    public void refresh(RefreshEvent ev) {
        //TODO
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        Observable source = ev.getSource();
        
        if(source instanceof IdentifiableComponent){
            
            IdentifiableComponent component = (IdentifiableComponent)source;
            int id = component.getId();
            
            if(id == this.validateButtonId){
                
                //TODO : send userEvent
            }
        }
    }

    public int getMutationProbabilityId() {
        return mutationProbabilityId;
    }

    public int getCrossProbabilityId() {
        return crossProbabilityId;
    }

    public int getPopulationId() {
        return populationId;
    }

    public int getMaxStepCountId() {
        return maxStepCountId;
    }

    public int getTimeoutId() {
        return timeoutId;
    }

    public int getAvailableCrossOverOperatorsId() {
        return availableCrossOverOperatorsId;
    }

    public int getAvailableMutationOperatorsId() {
        return availableMutationOperatorsId;
    }

    public int getAvailableSelectionOperatorsId() {
        return availableSelectionOperatorsId;
    }

    public int getAvailableEvaluationOperatorId() {
        return availableEvaluationOperatorId;
    }

    public int getValidateButtonId() {
        return validateButtonId;
    }
}
