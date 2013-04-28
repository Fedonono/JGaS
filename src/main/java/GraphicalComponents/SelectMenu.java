/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import java.awt.FlowLayout;
import java.util.Collection;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @param <E> 
 * @author nono
 */
public class SelectMenu<E> extends IdentifiableComponent implements Observable {

    private LinkedList<Observer> observers = new LinkedList<>();
    private JComboBox<E> comboBox;

    /**
     *
     * @param label
     */
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

    /**
     *
     * @param item
     */
    public void addItem(E item) {
        this.comboBox.addItem(item);
    }

    /**
     *
     * @param collection
     */
    public void addAll(Collection<E> collection) {
        for (E item : collection) {
            comboBox.addItem(item);
        }
    }

    /**
     *
     * @return 'this' current selected Item.
     */
    public E getSelectedItem() {
        return (E) (this.comboBox.getModel().getSelectedItem());
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.reactToChanges(new SelectMenuEvent<>(this, this.getSelectedItem()));
        }
    }
}
