/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Function;

import Mathematics.Point;
import MvcPattern.UserEvent;
import MvcPattern.View;

/**
 *
 * @author nono
 */
public class DomaineEvent extends UserEvent {

    Point domaine;
    
    public DomaineEvent(View source, Point domaine){
        super(source);
        this.domaine = domaine;
    }

    public Point getValue() {
        return domaine;
    }
}
