/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MvcPattern;

/**
 *
 * @author simonneau
 */
public class UserEvent {

    private View source;
    
    /**
     * Fired by a View
     * @param source
     */
    public UserEvent(View source){
        this.source = source;
    }

    /**
     * 
     * @return the source view.
     */
    public View getSource(){
        return this.source;
    }
}
