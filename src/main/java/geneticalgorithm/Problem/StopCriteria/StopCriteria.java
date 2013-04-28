/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problem.StopCriteria;

import MvcPattern.Model;

/**
 *
 * @author simonneau
 */
public class StopCriteria extends Model{
     private int maxStepCount;
     private int timeout;
     private double evolutionCoeff;
     
     
     /**
     *
     */
    public StopCriteria(){
         this(0,0,0);
     }
     
     /**
     *
     * @param maxStepCount
     * @param timeout
     * @param mineEvolutionCoeff
     */
    public StopCriteria(int maxStepCount, int timeout, double mineEvolutionCoeff ){
         this.maxStepCount = maxStepCount;
         this.timeout = timeout;
         this.evolutionCoeff = mineEvolutionCoeff;
         this.addView(new StopCriteriaUI(new StopCriteriaController(this)));
         this.notifyViews();
         
     }

    /**
     *
     * @return
     */
    public int getMaxStepCount() {
        return maxStepCount;
    }

    @Override
    public final void notifyViews(){
        super.notifyViews(new StopCriteriaRefreshEvent(this));
    }
    
    /**
     *
     * @return
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     *
     * @return
     */
    public double getEvolutionCriterion() {
        return evolutionCoeff;
    }

    /**
     *
     * @param maxStepCount
     */
    public void setMaxStepCount(int maxStepCount) {
        this.maxStepCount = maxStepCount;
    }

    /**
     *
     * @param timeout
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     *
     * @param minEvolutionCoeff
     */
    public void setMinEvolutionCriterion(double minEvolutionCoeff) {
        this.evolutionCoeff = minEvolutionCoeff;
    }
     
    /**
     *
     * @param stepCount
     * @param time
     * @param evolutionCoeff
     * @return
     */
    public boolean areReached(int stepCount, long time, double evolutionCoeff){
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
