/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.engine;

import MvcPattern.RefreshEvent;
import geneticalgorithm.Problems.ProblemUI;

/**
 *
 * @author simonneau
 */
public class EngineProblemRefreshEvent extends RefreshEvent {

    private ProblemUI problemUI;

    public EngineProblemRefreshEvent(GeneticEngine source, ProblemUI problemUI) {
        super(source);
        this.problemUI = problemUI;
    }

    public ProblemUI getProblemUI() {
        return problemUI;
    }
    
    
}
