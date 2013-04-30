/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import GraphicalComponents.ValidateButton;
import geneticalgorithm.Population.Individuals.Individual;
import java.awt.event.ActionEvent;

/**
 *
 * @author simonneau
 */
public class IndividualButton extends ValidateButton {

    private Individual individual;

    public IndividualButton(String label, Individual individual) {
        this(label, false, individual);
    }

    public IndividualButton(String label, boolean selected, Individual individual) {
        super(label);
        this.individual = individual;
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        super.actionPerformed(ae);
        individual.notifyViews();
    }
}
