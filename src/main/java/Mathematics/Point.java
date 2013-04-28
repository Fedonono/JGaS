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

    /**
     *
     * @param doubles
     */
    public Point(Double... doubles) {
        this.addAll(Arrays.asList(doubles));
    }

    /**
     *
     * @param c
     */
    public Point(Collection<? extends Double> c) {
        super(c);
    }

    /**
     * set 'this coordonate with average values between this and point.
     * @param point
     */
    public void average(Point point) {
        int size = this.size();
        for (int i = 0; i < size; i++) {
            this.set(i, (this.get(i) + point.get(i)) / 2);
        }
    }

    /**
     * reset 'this' with point values.
     * @param p
     */
    public void set(Point p){
        this.clear();
        this.addAll(p);
    }
}
