/*
 * Genetic Algorithm Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package GraphicalComponents;

/**
 *
 * @author simonneau
 */
public class MinMaxValueException extends RuntimeException{

    /**
     *
     * @param max
     * @param min
     */
    public MinMaxValueException(Number max, Number min) {
        super("Max value : "+max+" must be lower than min value : "+min);
    }
    
}
