/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import GraphicalComponents.CustomRadioButton;
import geneticalgorithm.Population.Individuals.Individual;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author simonneau
 */
public class IndividualRadioButton extends CustomRadioButton {

    private Individual individual;
    
    public IndividualRadioButton(String label, Individual individual){
        this(label, false, individual);
    }

    public IndividualRadioButton(String label, boolean selected, Individual individual) {
        super(label);
        this.individual = individual;
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        this.individual.notifyViews();
    }
}
