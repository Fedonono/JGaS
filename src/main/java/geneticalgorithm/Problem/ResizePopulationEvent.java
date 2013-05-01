/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problem;

import GraphicalComponents.ObservationEvent;

/**
 *
 * @author simonneau
 */
public class ResizePopulationEvent extends ObservationEvent{
    
    /**
     *
     * @param source
     */
    public ResizePopulationEvent(ProblemUI source){
        super(source);
    }
}
