/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import java.awt.Rectangle;
import javax.swing.JFrame;

/**
 *
 * @author simonneau
 */
public class CustomFrame extends JFrame implements Observer {

    @Override
    public void reactToChanges(ObservationEvent ev) {
        if(ev instanceof RepaintEvent){
            Rectangle bounds = this.getBounds();
            this.setBounds(0, 0, 0, 0);
            this.setBounds(bounds);
        }
    }
    
}
