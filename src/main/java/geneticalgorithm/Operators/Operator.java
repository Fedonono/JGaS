/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators;

/**
 *
 * @author simonneau
 */
public class Operator{

    protected String label;
    protected static Operator instance;
    
    public String getLabel() {
        return this.label;
    }
    
    @Override
    public String toString(){
        return this.getLabel();
    }
}