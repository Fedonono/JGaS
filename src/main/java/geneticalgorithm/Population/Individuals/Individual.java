/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Individuals;


import MvcPattern.Model;

/**
 *
 * @author simonneau
 */
public abstract class Individual extends Model {

    
   
    private double score;

    

    /**
     * Overall properties of 'this' with 's' properties.
     *
     * @param s
     */
    protected abstract void set(Individual s);

    public double getScore() {
        return this.score;
    }
    
    
    public void setScore(double score){
        this.score = score;
    }
    
    @Override
    public IndividualUI getUI(){
        return (IndividualUI)super.getUI();
    }
    
    public abstract String xmlSerialize();
}
