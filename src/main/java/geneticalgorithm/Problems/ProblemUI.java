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
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JDialog;

/**
 *
 * @author simonneau
 */



public class ProblemUI extends JDialog implements View, Observer {

    private boolean initialized = false;
    private ProblemController controller;
    private static String mutationProbabilityLabel = "mutation Probability";
    private CustomSpinner mutationProbability;
    private int mutationProbabilityId;
    private static String crossProbabilityLabel = "cross Probability";
    private CustomSpinner crossProbability;
    private int crossProbabilityId;
    private static String populationSizeLabel = "population size";
    private OptionLine populationSize;
    private int populationId;
    private static String maxStepCountLabel = "limit steps";
    private OptionLine maxStepCount;
    private int maxStepCountId;
    private static String timeoutLabel = "timeout(ms)";
    private OptionLine timeout;
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
    
    public ProblemUI(){
        this(null);
    }

    public ProblemUI(ProblemController controller) {
        
        this.controller = controller;
        
        Container cp = this.getContentPane();
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));

        int heightPx = 410;
        int widthPx = 450;
        this.setMinimumSize(new Dimension(widthPx, heightPx));

        this.mutationProbability = new CustomSpinner(mutationProbabilityLabel, 0, 1,0,0.01);
        this.mutationProbabilityId = this.mutationProbability.getId();
        this.add(this.mutationProbability);

        this.crossProbability = new CustomSpinner(crossProbabilityLabel, 0, 1,0,0.01);
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

        this.maxStepCount = new OptionLine(maxStepCountLabel, 0, Integer.MAX_VALUE, 0);
        this.maxStepCountId = this.maxStepCount.getId();
        this.add(this.maxStepCount);

        this.timeout = new OptionLine(timeoutLabel, 0, Integer.MAX_VALUE, 0);
        this.timeoutId = this.timeout.getId();
        this.add(this.timeout);

        this.populationSize = new OptionLine(populationSizeLabel, 1, Integer.MAX_VALUE, 50);
        this.populationId = this.populationSize.getId();
        this.add(this.populationSize);

        this.validateButton = new ValidateButton(validateButtonLabel);
        this.validateButtonId = this.validateButton.getId();
        this.add(this.validateButton);
        this.validateButton.addObserver(this);
        
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    @Override
    public void refresh(RefreshEvent ev) {

        if (ev instanceof ProblemRefreshEvent) {
            Problem source = (Problem) ev.getSource();

            if (!this.initialized) {
                this.availableCrossOverOperators.addAll(source.getAvailableCrossOverOperators());
                this.availableEvaluationOperators.addAll(source.getAvailableEvaluationOperator());
                this.availableMutationOperators.addAll(source.getAvailableMutationOperators());
                this.availableSelectionOperators.addAll(source.getAvailableSelectionOperators());
                this.initialized = true;
            }
        }
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        Observable source = ev.getSource();

        if (source instanceof IdentifiableComponent) {

            IdentifiableComponent component = (IdentifiableComponent) source;
            int id = component.getId();

            if (id == this.validateButtonId) {
                this.setVisible(false);
                this.notifyController();
            }
        }
    }

    private void notifyController() {
        this.controller.applyChanges(new ProblemUserEvent(this));
    }

    private int getMutationProbabilityId() {
        return mutationProbabilityId;
    }

    private int getCrossProbabilityId() {
        return crossProbabilityId;
    }

    private int getPopulationId() {
        return populationId;
    }

    private int getMaxStepCountId() {
        return maxStepCountId;
    }

    private int getTimeoutId() {
        return timeoutId;
    }

    private int getAvailableCrossOverOperatorsId() {
        return availableCrossOverOperatorsId;
    }

    private int getAvailableMutationOperatorsId() {
        return availableMutationOperatorsId;
    }

    private int getAvailableSelectionOperatorsId() {
        return availableSelectionOperatorsId;
    }

    private int getAvailableEvaluationOperatorId() {
        return availableEvaluationOperatorId;
    }

    private int getValidateButtonId() {
        return validateButtonId;
    }

    public CrossOverOperator getSelectedCrossOverOperator() {
        return this.availableCrossOverOperators.getSelectedItem();
    }

    public MutationOperator getSelectedMutationOperator() {
        return this.availableMutationOperators.getSelectedItem();
    }

    public SelectionOperator getSelectedSelectionOperator() {
        return this.availableSelectionOperators.getSelectedItem();
    }

    public EvaluationOperator getSelectedEvaluationOperator() {
        return this.availableEvaluationOperators.getSelectedItem();
    }

    public double getMutationProbability() {
        return mutationProbability.getValue().doubleValue();
    }

    public double getCrossProbability() {
        return crossProbability.getValue().doubleValue();
    }

    public int getPopulationSize() {
        return populationSize.getValue();
    }

    public int getMaxStepCount() {
        return maxStepCount.getValue();
    }

    public int getTimeout() {
        return timeout.getValue();
    }

    // TODO : A delete par la suite. Utile pour les tests
    public static void main(String[] args) {
        ProblemUI pbUI = new ProblemUI();
        pbUI.setVisible(true);
    }

    public void setController(ProblemController controller) {
        this.controller = controller;
    }
    
    
}
