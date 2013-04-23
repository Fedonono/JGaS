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
    private int timeCount = 0;
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
        this.timeCount += new Date().getTime() - this.date.getTime();
        this.stoped = true;
    }

    /**
     *
     * @param time
     */
    private void setTime(int time) {
        this.timeCount = time;
    }

    /**
     *
     * @return
     */
    public int getTime() {
        if (!this.stoped) {
            this.timeCount += new Date().getTime() - this.date.getTime();
        }
        return this.timeCount;
    }
}
