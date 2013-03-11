/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import java.awt.FlowLayout;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author nono
 */
public class SelectMenu<E> extends IdentifiableComponent implements Observable {
    
    private LinkedList<Observer> observers = new LinkedList<>();
    private JComboBox<E> comboBox;

    public SelectMenu(String label) {

        super(new FlowLayout());
        this.add(new JLabel(label));
        
        comboBox = new JComboBox<>();
        
        this.add(comboBox, FlowLayout.CENTER);
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }
    
    public void addItem(E item){
        this.comboBox.addItem(item);
    }
    
    public E getSelectedItem(){
        return (E)(this.comboBox.getModel().getSelectedItem());
    }
    
    

    @Override
    public void notifyObserver() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
