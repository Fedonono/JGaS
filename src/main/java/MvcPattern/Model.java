/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MvcPattern;

import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public class Model {

    private LinkedList<View> views = new LinkedList<>();

    protected void notifyViews(RefreshEvent ev) {
        for (View v : views) {
            v.refresh(ev);
        }
    }

    public void notifyViews() {
        this.notifyViews(new RefreshEvent(this));
    }

    public void addView(View v) {
        this.views.add(v);
    }
    
    public View getUI(){
        return this.views.get(0);
    }
}
