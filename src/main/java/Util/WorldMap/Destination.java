/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.WorldMap;

/**
 *
 * @author simonneau
 */
public class Destination {
    
    private static int availableId = 0;
    
    private double x;
    private double y;
    private String Label;
    
    private int id = availableId++;
    
    /**
     *
     * @param label
     * @param x
     * @param y
     */
    public Destination(String label,int x, int y){
        this.Label = label;
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return the x coordonate.
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @return the y coordonate.
     */
    public double getY() {
        return y;
    }

    /**
     *
     * @return 'this' label.
     */
    public String getLabel() {
        return Label;
    }

    /**
     *
     * @return 'this' id.
     */
    public int getId() {
        return id;
    }

    /**
     * set the x coordonate.
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * set the ycoordonate.
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * set 'this' label.
     * @param Label
     */
    public void setLabel(String Label) {
        this.Label = Label;
    }
    
    
}
