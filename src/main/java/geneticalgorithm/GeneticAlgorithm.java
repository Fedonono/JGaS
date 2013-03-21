package geneticalgorithm;

import MvcPattern.Model;
import geneticalgorithm.Problems.Problem;
import java.awt.Frame;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Hello world!
 *
 */
public class GeneticAlgorithm extends Model{

    private LinkedList<Problem> problems;
    private Problem Selectedproblem;
    private GeneticEngine geneticEngine;
    private Frame mainFrame;

    public GeneticAlgorithm() {
        this.problems = new LinkedList<>();
        
    }

    
    
    public void addProblem(Problem problem) {
        this.problems.add(problem);
    }

    public void addAll(Collection<Problem> foreignProblems) {
        this.problems.addAll(foreignProblems);
    }
    
    public LinkedList<Problem> getProblems(){
        return this.problems;
    }
    
    public Problem getProblem(int i){
        return this.problems.get(i);
    }
    
    public void run(){
        
        GAcontroller gaController = new GAcontroller(this);
        MainUI mainUI = new MainUI(gaController);
        this.addView(mainUI)
                ;
        this.mainFrame = new Frame();
        this.mainFrame.add(mainUI);
        this.mainFrame.setVisible(true);        
    }
    
    private void start(){
        
    }
}
