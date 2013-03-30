/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathematics.Function.Events;

import Mathematics.Points;
import MvcPattern.UserEvent;
import MvcPattern.View;

/**
 *
 * @author nono
 */
public class DomaineEvent extends UserEvent {

    Points domaine;
    
    public DomaineEvent(View source, Points domaine){
        super(source);
        this.domaine = domaine;
    }

    public Points getValue() {
        return domaine;
    }
}
