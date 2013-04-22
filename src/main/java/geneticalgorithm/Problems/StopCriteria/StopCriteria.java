/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems.StopCriteria;

import MvcPattern.Model;

/**
 *
 * @author simonneau
 */
public class StopCriteria extends Model{
     private int maxStepCount;
     private int timeout;
     private double evolutionCoeff;
     
     
     public StopCriteria(){
         this(0,0,0);
     }
     
     public StopCriteria(int maxStepCount, int timeout, double mineEvolutionCoeff ){
         this.maxStepCount = maxStepCount;
         this.timeout = timeout;
         this.evolutionCoeff = mineEvolutionCoeff;
         this.addView(new StopCriteriaUI(new StopCriteriaController(this)));
         this.notifyViews();
         
     }

    public int getMaxStepCount() {
        return maxStepCount;
    }

    @Override
    public final void notifyViews(){
        super.notifyViews(new StopCriteriaRefreshEvent(this));
    }
    
    public int getTimeout() {
        return timeout;
    }

    public double getEvolutionCriterion() {
        return evolutionCoeff;
    }

    public void setMaxStepCount(int maxStepCount) {
        this.maxStepCount = maxStepCount;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setMinEvolutionCriterion(double minEvolutionCoeff) {
        this.evolutionCoeff = minEvolutionCoeff;
    }
     
    public boolean areReached(int stepCount, int time, double evolutionCoeff){
        boolean areReached = false;
        
        if(this.maxStepCount != 0 && stepCount >= this.maxStepCount){
            areReached = true;
            
        }else if(this.timeout != 0 && time >= this.timeout){
            areReached = true;
            
        }else if(this.evolutionCoeff != 0 && evolutionCoeff <= this.evolutionCoeff){
            areReached = true;
        }
        
        return areReached;
    }
     
}
