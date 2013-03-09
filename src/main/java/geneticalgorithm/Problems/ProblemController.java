/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems;

import MvcPattern.Controller;
import MvcPattern.UserEvent;

/**
 *
 * @author simonneau
 */
public class ProblemController implements Controller{
    
    private Problem problem;

    @Override
    public void applyChanges(UserEvent event) {
        
        if(event instanceof MutationProbabilityUserEvent){
            MutationProbabilityUserEvent ev = (MutationProbabilityUserEvent)event;
            problem.setMutationProbability(ev.getValue());
            
        }else if(event instanceof CrossProbabilityUserEvent){
            CrossProbabilityUserEvent ev = (CrossProbabilityUserEvent)event;
            problem.setCrossProbability(ev.getValue());
            
        }
    }
    
}
