/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems;

import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import GraphicalComponents.OptionLine;
import GraphicalComponents.OptionLineEvent;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
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

    public ProblemUI() {

        this.mutationProbability = new OptionLine(mutationProbabilityLabel, 0, 1, 0);
        this.crossProbability = new OptionLine(crossProbabilityLabel, 0, 1, 0);

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
