/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators;

import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Operators.Evaluation.EvaluationOperator;
import geneticalgorithm.Operators.Mutation.MutationOperator;
import geneticalgorithm.Operators.Selection.SelectionOperator;

/**
 *
 * @author simonneau
 */
public class Operators {
    
    private CrossOverOperator crossoverOperator;
    private EvaluationOperator evaluationOperator;
    private MutationOperator mutationOperator;
    private SelectionOperator selectionOperator;
    

    /**
     *
     * @return 'this' selected CrossoverOperator;
     */
    public CrossOverOperator getCrossoverOperator() {
        return crossoverOperator;
    }

    /**
     *
     * @return 'this' selected EvaluationOperator.
     */
    public EvaluationOperator getEvaluationOperator() {
        return evaluationOperator;
    }

    /**
     *
     * @return 'this' selected MutationOperator.
     */
    public MutationOperator getMutationOperator() {
        return mutationOperator;
    }

    /**
     *
     * @return 'this' selected SelectionOperator.
     */
    public SelectionOperator getSelectionOperator() {
        return selectionOperator;
    }

    /**
     * set 'this' selected CrossoverOperator.
     * @param crossoverOperator
     */
    public void setCrossoverOperator(CrossOverOperator crossoverOperator) {
        this.crossoverOperator = crossoverOperator;
    }

    /**
     * set this' selected EvaluationOperator.
     * @param evaluationOperator
     */
    public void setEvaluationOperator(EvaluationOperator evaluationOperator) {
        this.evaluationOperator = evaluationOperator;
    }

    /**
     * set 'this' selected MutationOperator.
     * @param mutationOperator
     */
    public void setMutationOperator(MutationOperator mutationOperator) {
        this.mutationOperator = mutationOperator;
    }

    /**
     * set 'this' selected SelectionOperator.
     * @param selectionOperator
     */
    public void setSelectionOperator(SelectionOperator selectionOperator) {
        this.selectionOperator = selectionOperator;
    }
    
    
    
    
}
