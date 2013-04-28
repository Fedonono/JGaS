/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Individuals;

import MvcPattern.Model;

/**
 *
 * @param <T> 
 * @author simonneau
 */
public abstract class Individual<T extends Individual> extends Model implements Comparable<T> {

    private double score;

    /**
     * Overall properties of 'this' with 's' properties.
     *
     * @param s
     */
    protected abstract void set(Individual s);

    /**
     *
     * @return 'this' score.
     */
    public double getScore() {
        return this.score;
    }

    /**
     * set'this' score.
     * @param score
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     *
     * @return
     */
    @Override
    public IndividualUI getUI() {
        return (IndividualUI) super.getUI();
    }

    /**
     *
     * @return
     */
    public abstract String xmlSerialization();

    /**
     * compare this to t.
     * @param t
     * @return 1 if this.getScore() > t.getScore(). 0 if this.getScore() == t.getScore(). -1 otherwise.
     */
    @Override
    public int compareTo(Individual t) {
        
        double thisScore = this.getScore();
        double tScore = t.getScore();

        if (thisScore > tScore) {
            return 1;
        } else if (thisScore < tScore) {
            return -1;
        } else {
            return 0;
        }
    }
}
