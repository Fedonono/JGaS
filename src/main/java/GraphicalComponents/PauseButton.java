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
    
    public PauseButton(){
        this( false);
    }
    
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
    public void notifyObserver(){
       for(Observer o: observers){
           o.reactToChanges(new PauseEvent(this, this.isPaused));
       }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        super.actionPerformed(ae);
        this.setPause();
        this.notifyObserver();
    }
    
    public boolean isPaused(){
        return this.isPaused;
    }
}
