/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author nono
 */
public class Points extends ArrayList<Double> {

    public Points(Double... doubles) {
        for(Double value : doubles){
            this.add(value);
        }
    }

    public Points(Collection<? extends Double> c) {
        super(c);
    }

    
}
