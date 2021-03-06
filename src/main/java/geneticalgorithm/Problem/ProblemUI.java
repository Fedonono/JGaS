/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problem;

import GraphicalComponents.CustomSpinner;
import GraphicalComponents.IdentifiableComponent;
import GraphicalComponents.Observable;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import GraphicalComponents.SelectMenu;
import GraphicalComponents.ValidateButton;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Operators.Evaluation.EvaluationOperator;
import geneticalgorithm.Operators.Mutation.MutationOperator;
import geneticalgorithm.Operators.Selection.SelectionOperator;
import geneticalgorithm.Problem.StopCriteria.StopCriteriaUI;
import java.awt.Container;
import java.awt.Dimension;
import java.util.LinkedList;
import javax.swing.BoxLayout;
import javax.swing.JDialog;

/**
 *
 * @author simonneau
 */
public class ProblemUI extends JDialog implements View, Observer, Observable {

    private boolean initialized = false;
    private ProblemController controller;
    private static String mutationProbabilityLabel = "mutation Probability";
    private CustomSpinner mutationProbability;
    private int mutationProbabilityId;
    private static String crossProbabilityLabel = "cross Probability";
    private CustomSpinner crossProbability;
    private int crossProbabilityId;
    private static String populationSizeLabel = "population size";
    private CustomSpinner populationSize;
    private int populationId;
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
    /**
     *
     */
    protected ValidateButton validateButton;
    private int validateButtonId;
    private StopCriteriaUI stopCriteriaUI;
    private double previousPopulationSize = 0;
    /**
     *
     */
    protected LinkedList<Observer> observers;

    /**
     *
     * @param problem
     */
    public ProblemUI(Problem problem) {

        this.observers = new LinkedList<>();

        this.stopCriteriaUI = (StopCriteriaUI) problem.getStopCriteria().getUI();

        this.controller = new ProblemController(problem);

        Container cp = this.getContentPane();
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));

        int heightPx = 450;
        int widthPx = 600;
        this.setMinimumSize(new Dimension(widthPx, heightPx));

        this.mutationProbability = new CustomSpinner(mutationProbabilityLabel, 0, 1, 0, 0.01);
        this.mutationProbabilityId = this.mutationProbability.getId();
        this.add(this.mutationProbability);

        this.crossProbability = new CustomSpinner(crossProbabilityLabel, 0, 1, 0, 0.01);
        this.crossProbabilityId = this.crossProbability.getId();
        this.add(this.crossProbability);

        this.populationSize = new CustomSpinner(populationSizeLabel, 1, Integer.MAX_VALUE, 50, 1);
        this.populationId = this.populationSize.getId();
        this.add(this.populationSize);

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

        this.add(this.stopCriteriaUI);

        this.validateButton = new ValidateButton(validateButtonLabel);
        this.validateButtonId = this.validateButton.getId();
        this.add(this.validateButton);
        this.validateButton.addObserver(this);



        this.setDefaultCloseOperation(HIDE_ON_CLOSE);

    }

    private void init(Problem pb) {

        this.availableCrossOverOperators.removeAllItems();
        this.availableCrossOverOperators.addAll(pb.getAvailableCrossOverOperators());
        this.availableCrossOverOperators.setSelectedItem(pb.getSelectedCrossOverOperator());

        this.availableEvaluationOperators.removeAllItems();
        this.availableEvaluationOperators.addAll(pb.getAvailableEvaluationOperator());
        this.availableEvaluationOperators.setSelectedItem(pb.getSelectedEvaluationOperator());

        this.availableMutationOperators.removeAllItems();
        this.availableMutationOperators.addAll(pb.getAvailableMutationOperators());
        this.availableMutationOperators.setSelectedItem(pb.getSelectedMutationOperator());

        this.availableSelectionOperators.removeAllItems();
        this.availableSelectionOperators.addAll(pb.getAvailableSelectionOperators());
        this.availableSelectionOperators.setSelectedItem(pb.getSelectedSelectionOperator());

        this.mutationProbability.setValue(pb.getMutationProbability());
        this.crossProbability.setValue(pb.getCrossProbability());
        this.previousPopulationSize = pb.getPopulationSize();
        this.populationSize.setValue(this.previousPopulationSize);
    }

    @Override
    public void refresh(RefreshEvent ev) {

        if (ev instanceof ProblemRefreshEvent) {
            Problem source = (Problem) ev.getSource();
            this.init(source);
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

                if (this.previousPopulationSize < this.populationSize.getValue().doubleValue()) {
                    this.notifyObservers();
                }
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

    /**
     *
     * @return
     */
    public CrossOverOperator getSelectedCrossOverOperator() {
        return this.availableCrossOverOperators.getSelectedItem();
    }

    /**
     *
     * @return
     */
    public MutationOperator getSelectedMutationOperator() {
        return this.availableMutationOperators.getSelectedItem();
    }

    /**
     *
     * @return
     */
    public SelectionOperator getSelectedSelectionOperator() {
        return this.availableSelectionOperators.getSelectedItem();
    }

    /**
     *
     * @return
     */
    public EvaluationOperator getSelectedEvaluationOperator() {
        return this.availableEvaluationOperators.getSelectedItem();
    }

    /**
     *
     * @return
     */
    public double getMutationProbability() {
        return mutationProbability.getValue().doubleValue();
    }

    /**
     *
     * @return
     */
    public double getCrossProbability() {
        return crossProbability.getValue().doubleValue();
    }

    /**
     *
     * @return
     */
    public int getPopulationSize() {
        return populationSize.getValue().intValue();
    }

    /**
     *
     * @param controller
     */
    public void setController(ProblemController controller) {
        this.controller = controller;
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.reactToChanges(new ResizePopulationEvent(this));
        }
    }
}
