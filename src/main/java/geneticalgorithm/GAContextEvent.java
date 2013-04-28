/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import MvcPattern.UserEvent;
import geneticalgorithm.Problems.Problem;

/**
 *
 * @author simonneau
 */
public class GAContextEvent extends UserEvent{
    
    Problem selectedProblem;
    
    /**
     * Fired by a GeneticAlgorithmUI.
     * @param source
     * @param problem
     */
    public GAContextEvent(GeneticAlgorithmUI source, Problem problem){
        super(source);
        this.selectedProblem = problem;
    }
    
    /**
     *
     * @return the source selected Problem.
     */
    public Problem getSelectedProblem(){
        return this.selectedProblem;
    }
}
