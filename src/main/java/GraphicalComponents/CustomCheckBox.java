/*
 * Genetic Algorithm Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package GraphicalComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JCheckBox;

/**
 *
 * @author simonneau
 */
public class CustomCheckBox extends IdentifiableObservableComponent implements ActionListener{
    
    private JCheckBox checkBox;
    
    /**
     *
     * @param text
     * @param checked
     */
    public CustomCheckBox(String text, boolean checked) {        
        this.checkBox = new JCheckBox(text, checked);
        this.checkBox.addActionListener(this);
        this.add(this.checkBox);
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observers){
            o.reactToChanges(new CheckBoxEvent(this, this.checkBox.isSelected()));
        }
    }

    /**
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        this.notifyObservers();
    }
    
}

