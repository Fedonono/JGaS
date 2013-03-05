/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Individuals;


import MvcPattern.Model;
import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Operators.Evaluation.EvaluationOperator;
import geneticalgorithm.Operators.Mutation.MutationOperator;

/**
 *
 * @author simonneau
 */
public abstract class Individual extends Model {

    private CrossOverOperator crossOverOperator;
    private MutationOperator mutationOperator;
    private EvaluationOperator evaluationOperator;
    private double score;
    private double mutationProbability;
    private double crossOverProbability;

    /**
     * Cross over operator provided by adapter design pattern.
     *
     * @param companion
     * @return a new Individual
     */
    public Individual cross(Individual companion) {
        return this.crossOverOperator.cross(this, companion);
    }

    /**
     * Mutation operator provided by adapter design pattern.
     */
    public void mutate() {
        Individual s = this.mutationOperator.mutate(this);
        this.set(s);
        this.notifyViews();
    }

    /**
     * Overall properties of 'this' with 's' properties.
     *
     * @param s
     */
    protected abstract void set(Individual s);

    public double getScore() {
        return this.score;
    }
    
    public double getCrossOverProbability(){
        return this.crossOverProbability;
    }
    
    public double getMutationProbability(){
        return this.mutationProbability;
    }
    
    
    public void evaluate(){
        this.score = this.evaluationOperator.evaluate(this);
    }
    
    @Override
    public IndividualUI getUI(){
        return (IndividualUI)super.getUI();
    }
}
