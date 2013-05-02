/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import java.awt.event.ActionEvent;

/**
 *
 * @author simonneau
 */
public class PauseButton extends ValidateButton{
    
    
    private static String pause = "pause";
    private static String resume = "resume";
    
    private boolean isPaused;
    
    /**
     *
     */
    public PauseButton(){
        this( false);
    }
    
    /**
     *
     * @param isPaused
     */
    public PauseButton(boolean isPaused){
        super("");
        this.isPaused = isPaused;
        String label;
        if(isPaused){
            label = resume;
        }else{
            label = pause;
        }
        this.setText(label);        
    }
    
    /**
     * 
     * @param isPaused
     */
    public void setState(boolean isPaused){
        if(isPaused){
            this.button.setText(resume);
        }else{
            this.button.setText(pause);
        }
    }
    
    private void setPause(){
        this.isPaused = !this.isPaused;
        this.setState(this.isPaused);
    }
    
    @Override
    public void notifyObservers(){
       for(Observer o: observers){
           o.reactToChanges(new PauseEvent(this, this.isPaused));
       }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        this.setPause();
        this.notifyObservers();
    }
    
    /**
     *
     * @return
     */
    public boolean isPaused(){
        return this.isPaused;
    }
}
