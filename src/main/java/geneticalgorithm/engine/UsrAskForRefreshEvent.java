/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.engine;

import MvcPattern.UserEvent;
import MvcPattern.View;

/**
 *
 * @author simonneau
 */
public class UsrAskForRefreshEvent extends UserEvent {

    boolean needingRefresh;

    /**
     *
     * @param source
     * @param needingRefresh
     */
    public UsrAskForRefreshEvent(View source, boolean needingRefresh) {
        super(source);
        this.needingRefresh = needingRefresh;
    }

    /**
     *
     * @return
     */
    public boolean isNeedingRefresh() {
        return this.needingRefresh;
    }
}
