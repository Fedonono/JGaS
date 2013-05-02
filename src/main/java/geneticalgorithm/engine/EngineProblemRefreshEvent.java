/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.engine;

import MvcPattern.RefreshEvent;
import geneticalgorithm.Problem.ProblemUI;

/**
 *
 * @author simonneau
 */
public class EngineProblemRefreshEvent extends RefreshEvent {

    private ProblemUI problemUI;

    /**
     *
     * @param source
     * @param problemUI
     */
    public EngineProblemRefreshEvent(GeneticEngine source, ProblemUI problemUI) {
        super(source);
        this.problemUI = problemUI;
    }

    /**
     *
     * @return
     */
    public ProblemUI getProblemUI() {
        return problemUI;
    }
    
    
}
