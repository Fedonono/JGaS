/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

/**
 *
 * @author simonneau
 */
public class NumberTextField extends CustomTextField{
    
    private double value;
    private double maxValue;
    private double minValue;
    
    /**
     *
     */
    public NumberTextField(){
        this("");
    }
    
    /**
     *
     * @param label
     */
    public NumberTextField(String label){
        this(label, 0);
    }
    
    /**
     *
     * @param label
     * @param value
     */
    public NumberTextField(String label, double value){
        this(label,value, Double.MIN_VALUE, Double.MAX_VALUE);
    }
    
    /**
     *
     * @param label
     * @param value
     * @param minValue
     * @param maxValue
     */
    public NumberTextField(String label, double value, double minValue, double maxValue){
        this(label, value, minValue, maxValue, 10);
    }
    
    /**
     *
     * @param label
     * @param value
     * @param minValue
     * @param maxValue
     * @param columns
     */
    public NumberTextField(String label, double value, double minValue, double maxValue, int columns){
        super(Double.toString(value), columns);
        
        if(maxValue < minValue){
            throw new MinMaxValueException(maxValue, minValue);
        }
        this.setValue(value);
        
        this.value = value;
        this.maxValue = maxValue;
        this.minValue = minValue;
        
        this.removeAll();
        this.setLayout(new FlowLayout());
        this.add(new JLabel(label));
        this.add(this.textField);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        
        
        try{
            
            Double d = Double.valueOf(this.textField.getText());
           this.setValue(d);
            
        }catch(Exception e){
            this.textField.setText(Double.toString(this.value));
        }
    }
    
    /**
     *
     * @param value
     */
    public final void setValue(double value){
        
        if(value < this.minValue){
            value = this.minValue;
        }else if(value > this.maxValue){
            value = this.maxValue;
        }
        this.value = value;
        this.textField.setText(Double.toString(value));
    }
    
    /**
     *
     * @return
     */
    public double getValue(){
        return this.value;
    }
    
}
