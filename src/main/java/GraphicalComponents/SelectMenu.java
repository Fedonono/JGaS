/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nono
 */
public class SelectMenu extends JPanel implements Observable {

    public SelectMenu(String text, String[] modes) {
        super(new FlowLayout());
        this.add(new JLabel(text));
        final JComboBox comboBox = new JComboBox(modes);
        this.add(comboBox, FlowLayout.CENTER);
    }

    @Override
    public void addObserver(Observer o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void notifyObserver() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
