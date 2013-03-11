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

    public TestCrossOverOperator(String label) {
        this.label = label;
    }

    
    
    @Override
    public Individual cross(Individual male, Individual female) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public static TestCrossOverOperator getInstance() {
        if(instance == null){
            instance = new TestCrossOverOperator(LABEL);
        }
        
        return (TestCrossOverOperator)instance;
    }
}
