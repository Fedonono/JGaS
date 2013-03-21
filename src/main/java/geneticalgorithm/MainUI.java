/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import GraphicalComponents.IdentifiableComponent;
import MvcPattern.Controller;
import MvcPattern.RefreshEvent;
import MvcPattern.View;

/**
 *
 * @author simonneau
 */
public class MainUI extends IdentifiableComponent implements View{
    
    private Controller controller;
    
    public MainUI(GAcontroller controller){
        
        this.controller = controller;
        
        //TODO_1 design
    }

    public void setController(GAcontroller controller){
        this.controller = controller;
    }
    
    @Override
    public void refresh(RefreshEvent ev) {
        //TODO_2 
    }
    
    public void notifyController(){
        //TODO_3
    }
    
}
