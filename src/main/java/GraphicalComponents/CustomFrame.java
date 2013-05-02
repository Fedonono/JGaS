/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author simonneau
 */
public class CustomFrame extends JFrame implements Observer {

    @Override
    public void reactToChanges(ObservationEvent ev) {
        if(ev instanceof RepaintEvent){
             Dimension size = this.getSize();
            this.setSize(0, 0);
            this.setSize(size);
        }
    }
    
}
