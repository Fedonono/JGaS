/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators;

/**
 *
 * @author simonneau
 */
public abstract class Operator{

    protected String label;
    private static Operator instance;
    
    public static Operator getInstance(){
        return instance;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    @Override
    public String toString(){
        return this.getLabel();
    }
}