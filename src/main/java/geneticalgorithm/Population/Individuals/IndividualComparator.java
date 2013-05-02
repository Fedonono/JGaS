/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Individuals;

import java.util.Comparator;

/**
 *
 * @author simonneau
 */
public class IndividualComparator implements Comparator<Individual>{

    /**
     *
     * @param t
     * @param t1
     * @return
     */
    @Override
    public int compare(Individual t, Individual t1) {
        return -1*t.compareTo(t1);
    }
    
}
