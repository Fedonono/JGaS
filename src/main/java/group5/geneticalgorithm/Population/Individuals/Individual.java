/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.geneticalgorithm.Population.Individuals;

import group5.geneticalgorithm.MvcPattern.Model;
import group5.geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import group5.geneticalgorithm.Operators.Evaluation.EvaluationOperator;
import group5.geneticalgorithm.Operators.Mutation.MutationOperator;

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
}
