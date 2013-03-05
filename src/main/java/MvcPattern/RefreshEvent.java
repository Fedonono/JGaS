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
    
    public RefreshEvent(Model source){
        this.source = source;
    }

    public Model getSource(){
        return this.source;
    }
}
