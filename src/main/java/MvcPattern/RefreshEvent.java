/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MvcPattern;

/**
 *
 * @author simonneau
 */
public class RefreshEvent {
    
    private Model source;
    
    /**
     * Fired by a Model
     * @param source
     */
    public RefreshEvent(Model source){
        this.source = source;
    }

    /**
     * return the source model.
     * @return
     */
    public Model getSource(){
        return this.source;
    }
}
