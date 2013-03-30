/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.JPanel;

/**
 *
 * @author simonneau
 */
public class IdentifiableComponent extends JPanel {
    private static int nextId = 0;
    private int id;

    public IdentifiableComponent() {
        this(new FlowLayout());
    }
    public IdentifiableComponent(LayoutManager lm){
        this.setLayout(lm);
        this.id = nextId++;
    }

    public int getId(){
        return this.id;
    }
}
