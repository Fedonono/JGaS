/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.Tools;

/**
 * Extrait de la librairie qui ne contenait que cette méthode utile.
 *
 * @author Yann RICHET
 */
public class DoubleArray {
    /**
     * Generates an array of successive values from <em>begin</em> to <em>end</em> with step
     * size <em>pitch</em>.
     * @param begin First value in sequence
     * @param pitch Step size of sequence
     * @param end Last value of sequence
     * @return Array of successive values
     */
    public static double[] increment(double begin, double pitch, double end) {
        double[] array = new double[(int) (((end - begin) / pitch)+1)];
        for (int i = 0; i < array.length; i++) {
            array[i] = begin + i * pitch;
        }
        return array;
    }
}
