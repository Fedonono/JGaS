/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

/**
 *
 * @param <E> 
 * @author simonneau
 */
public class SelectMenuEvent<E> extends ObservationEvent {

    E selectedItem;
    
    /**
     * Fired by a SelectMenu.
     * @param source
     * @param selectedItem
     */
    public SelectMenuEvent(Observable source, E selectedItem) {
        super(source);
        this.selectedItem = selectedItem;
    }
    
    /**
     *
     * @return the source selectedItem.
     */
    public E getSelectedItem(){
        return this.selectedItem;
    }
    
}
