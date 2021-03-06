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

    /**
     *
     */
    protected String label;
    
    /**
     *
     * @param label
     */
    public Operator(String label){
        this.label = label;
    }
    
    /**
     *
     * @return 'this' label.
     */
    public String getLabel() {
        return this.label;
    }
    
    /**
     *
     * @return 'this' label.
     */
    @Override
    public String toString(){
        return this.getLabel();
    }
}