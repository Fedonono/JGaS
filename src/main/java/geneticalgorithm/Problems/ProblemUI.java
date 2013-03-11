/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems;

import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import GraphicalComponents.OptionLine;
import GraphicalComponents.OptionLineEvent;
import GraphicalComponents.SelectMenu;
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
    private OptionLine mutationProbability;
    private static String mutationProbabilityLabel = "mutation Probability";
    private OptionLine crossProbability;
    private static String crossProbabilityLabel = "cross Probability";
    //private TrucGraphique populationSize;
    private static String poplationSizeLabel = "population size";
    //private trucGraphique maxStepCount;
    private static String maxStepCountLabel = "max number of generation steps";
    
    private SelectMenu<CrossOverOperator> availableCrossOverOperators;
    private static String availableCrossOverOperatorsLabel = "available cross over operators";
    
    private SelectMenu<MutationOperator> availableMutationOperators;
    private static String availableMutationOperatorsLabel = "available mutation operators";
    
    private SelectMenu<SelectionOperator> availableSelectionOperators;
    private static String availableSelectionOperatorsLabel = "available selection operators";
    
    private SelectMenu<EvaluationOperator> availableEvaluationOperators;
    private static String availableEvaluationOperatorsLabel = "available evaluation operators";
    

    public ProblemUI() {

        this.mutationProbability = new OptionLine(mutationProbabilityLabel, 0, 1, 0);
        this.crossProbability = new OptionLine(crossProbabilityLabel, 0, 1, 0);
        //Ajouter les SelectMenu

        this.mutationProbability.addObserver(this);
        this.crossProbability.addObserver(this);

        this.add(mutationProbability);
        this.add(crossProbability);

    }

    @Override
    public void refresh(RefreshEvent ev) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        
        if (ev instanceof OptionLineEvent) {
            
            OptionLineEvent event = (OptionLineEvent) ev;
            OptionLine option = (OptionLine) event.getSource();
            String label = option.getLabel();

            if (label.equals(mutationProbabilityLabel)) {
                this.controller.applyChanges(new MutationProbabilityUserEvent(this, event.getValue()));
                
            } else if(label.equals(crossProbabilityLabel)){
                this.controller.applyChanges(new CrossProbabilityUserEvent(this, event.getValue()));
                
            }
        }
    }
}
