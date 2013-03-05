/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.MvcPattern;

/**
 *
 * @author simonneau
 */
public class UserEvent {

    private View source;
    
    public UserEvent(View source){
        this.source = source;
    }

    public View getSource(){
        return this.source;
    }
}
