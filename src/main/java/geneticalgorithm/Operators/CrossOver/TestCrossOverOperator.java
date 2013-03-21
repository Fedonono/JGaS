/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.CrossOver;

import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author simonneau
 */
public class TestCrossOverOperator extends CrossOverOperator {
    
    private static final String LABEL = "testCrossOverOperator";
    private static final double defaultProba = 0.2;

    public TestCrossOverOperator(double prb,String label) {
        super(prb);
        this.label = label;
    }

    
    
    @Override
    public Individual cross(Individual male, Individual female) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public static TestCrossOverOperator getInstance() {
        if(instance == null){
            instance = new TestCrossOverOperator(defaultProba, LABEL);
        }
        
        return (TestCrossOverOperator)instance;
    }
}
