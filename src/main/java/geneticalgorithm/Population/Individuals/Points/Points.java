/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Individuals.Points;

import geneticalgorithm.Population.Individuals.Individual;
import java.util.ArrayList;

/**
 *
 * @author nono
 */
public class Points extends Individual {
    
    private ArrayList<Number> values;

    public Points(ArrayList<Number> newList) {
        values = new ArrayList<>(newList);
    }

    public ArrayList<Number> getValues() {
        return values;
    }
    
    public int getDimension() {
        return values.size();
    }
    
    @Override
    protected void set(Individual s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String xmlSerialization() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}
