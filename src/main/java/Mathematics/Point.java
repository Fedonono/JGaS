/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author nono
 */
public class Point extends ArrayList<Double> {

    public Point(Double... doubles) {
        this.addAll(Arrays.asList(doubles));
    }

    public Point(Collection<? extends Double> c) {
        super(c);
    }

    public void average(Point point) {
        int size = this.size();
        for (int i = 0; i < size; i++) {
            this.set(i, (this.get(i) + point.get(i)) / 2);
        }
    }
}
