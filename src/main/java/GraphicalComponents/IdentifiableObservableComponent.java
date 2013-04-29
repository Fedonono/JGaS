/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import java.util.LinkedList;

/**
 *
 * @author nono
 */
public abstract class IdentifiableObservableComponent extends IdentifiableComponent implements Observable {

    protected LinkedList<Observer> observers = new LinkedList<>();

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public abstract void notifyObservers();
    
}
