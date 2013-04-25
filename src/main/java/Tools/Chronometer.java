/*
 * Minesweeper Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package Tools;

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

    /**
     *
     */
    public void restart() {
        this.setTime(0);
        this.start();
    }

    /**
     *
     */
    public void start() {
        this.date = new Date();
        this.stoped = false;
    }

    /**
     *
     */
    public void stop() {
        if (!this.stoped) {
            this.previousSessionTimeCount += new Date().getTime() - this.date.getTime();
            this.stoped = true;
        }
    }

    /**
     *
     * @param time
     */
    private void setTime(int time) {
        this.currentSessionTimeCount = time;
    }

    /**
     *
     * @return
     */
    public long getTime() {
        if (!this.stoped) {
            this.currentSessionTimeCount = new Date().getTime() - this.date.getTime();
        }
        return this.currentSessionTimeCount + this.previousSessionTimeCount;
    }
}
