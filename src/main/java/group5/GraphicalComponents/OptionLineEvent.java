/*
 * Minesweeper Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package genericGraphicalComponents;

/**
 *
 * @author simonneau
 */
public class OptionLineEvent extends ObservationEvent{
    
    int value;
    
    /**
     *
     * @param source
     * @param value
     */
    public OptionLineEvent(CustomOptionLine source, int value){
        super(source);
        this.value = value;
    }
    
    /**
     *
     * @return
     */
    public int getValue(){
        return this.value;
    }
}
