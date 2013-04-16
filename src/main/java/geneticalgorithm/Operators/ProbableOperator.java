/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators;

/**
 *
 * @author simonneau
 */
public class ProbableOperator extends Operator {
    double probability;
    
    public ProbableOperator(double prb, String label){
        super(label);
        this.setProbability(prb);
    }
    
    public double getProbability() {
        return this.probability;
    }

    public void setProbability(double prb) {
        if (prb < 0) {
            prb = 0;
        } else if (prb > 1) {
            prb = 1;
        }

        this.probability = prb;
    }
}
