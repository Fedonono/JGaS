/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author simonneau
 */
public class PauseButton extends ValidateButton{
    
    private static final String pauseLocation = "Resources/pause.png";
    private static final String resumeLocation = "Resources/resume.png";
    private static Image pauseImage;
    private static Image resumeImage;
    static{
        URL url = PauseButton.class.getResource(pauseLocation);
        pauseImage = Toolkit.getDefaultToolkit().getImage(url);
        url = PauseButton.class.getResource(resumeLocation);
        resumeImage = Toolkit.getDefaultToolkit().getImage(url);
        
    }    
    private ImageIcon pauseIcon;
    private ImageIcon resumeIcon;
    
    private boolean pause;
    
    public PauseButton(String Label){
        this(Label, false);
    }
    
    public PauseButton(String label, boolean pause){
        super(label);
        this.pauseIcon = new ImageIcon(pauseImage);
        this.resumeIcon = new ImageIcon(resumeImage);
        
        this.pause = pause;
        this.setImageIcon(pause);
        
    }
    
    private void setImageIcon(boolean pause){
        if(pause){
            this.button.setIcon(resumeIcon);
        }else{
            this.button.setIcon(pauseIcon);
        }
    }
    
    private void setPause(){
        this.pause = !this.pause;
        this.setImageIcon(this.pause);
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
