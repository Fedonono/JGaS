/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author simonneau
 */
public class PauseButton extends ValidateButton{
    
    private static final String pauseLocation = "./ressources/pause.png";
    private static final String resumeLocation = "./ressources/resume.png";
    private Icon pauseIcon;
    private Icon resumeIcon;
    private boolean pause;
    
    public PauseButton(String Label){
        this(Label, false);
    }
    
    public PauseButton(String label, boolean pause){
        super(label);
        
        this.pauseIcon = new ImageIcon(pauseLocation);
        this.resumeIcon = new ImageIcon(resumeLocation);
        
        this.pause = pause;
        this.setPause(pause);
        
    }
    
    private void setPause(boolean pause){
        if(pause){
            this.button.setIcon(resumeIcon);
        }else{
            this.button.setIcon(pauseIcon);
        }
    }
    
    private void setPause(){
        this.pause = !this.pause;
        this.setPause(this.pause);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        super.actionPerformed(ae);
        this.setPause();
    }
    
    
    public static void main(String[] args){
        PauseButton pb = new PauseButton("",true);
        Frame frame = new Frame();
        frame.setVisible(true);
        frame.add(pb);
    }
}
