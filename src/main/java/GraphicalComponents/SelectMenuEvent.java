/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

/**
 *
 * @author simonneau
 */
public class SelectMenuEvent<E> extends ObservationEvent {

    E selectedItem;
    
    public SelectMenuEvent(Observable source, E selectedItem) {
        super(source);
        this.selectedItem = selectedItem;
    }
    
    public E getSelectedItem(){
        return this.selectedItem;
    }
    
}
