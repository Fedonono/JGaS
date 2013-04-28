/*
 * Minesweeper Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package Util.Tools;

import java.util.Date;

/**
 *
 * @author nono
 */
public class Chronometer {

    private Date date;
    private long currentSessionTimeCount = 0;
    private long previousSessionTimeCount = 0;
    private boolean stoped = true;

    
    public void reset(){
        this.stop();
        this.setTime(0);
    }    
    /**
     * restart 'this' chronometer.
     */
    public void restart() {
        this.setTime(0);
        this.start();
    }

    /**
     * start 'this' chronometer.
     */
    public void start() {
        this.date = new Date();
        this.stoped = false;
    }

    /**
     * stop 'this' chronometer.
     */
    public void stop() {
        if (!this.stoped) {
            this.previousSessionTimeCount += new Date().getTime() - this.date.getTime();
            this.stoped = true;
        }
    }

    /**
     * set 'this' current session time.
     * @param time
     */
    private void setTime(int time) {
        this.currentSessionTimeCount = time;
        this.previousSessionTimeCount = 0;
    }

    /**
     *
     * @return 'this' current session time.
     */
    public long getTime() {
        if (!this.stoped) {
            this.currentSessionTimeCount = new Date().getTime() - this.date.getTime();
        }
        return this.currentSessionTimeCount + this.previousSessionTimeCount;
    }
}
