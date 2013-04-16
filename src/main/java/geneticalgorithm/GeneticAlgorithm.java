package geneticalgorithm;

import MvcPattern.Model;
import geneticalgorithm.Problems.Min1D.Min1D;
import geneticalgorithm.Problems.Problem;
import geneticalgorithm.Problems.ProblemController;
import geneticalgorithm.Problems.ProblemUI;
import java.util.Collection;
import java.util.LinkedList;
import javax.swing.JFrame;

/**
 * Hello world!
 *
 */
public class GeneticAlgorithm extends Model {

    private LinkedList<Problem> problems;
    private Problem SelectedProblem;
    private GeneticEngine geneticEngine;
    private JFrame mainFrame;

    public GeneticAlgorithm() {
        this.problems = new LinkedList<>();

    }

    public void setSelectedProblem(Problem SelectedProblem) {
        this.SelectedProblem = SelectedProblem;
        this.start();
    }

    public void addProblem(Problem problem) {
        problem.addView(new ProblemUI(new ProblemController(problem)));
        this.problems.add(problem);

    }

    public void addAll(Collection<Problem> foreignProblems) {
        for (Problem pb : foreignProblems) {
            this.addProblem(pb);
        }
    }

    public LinkedList<Problem> getProblems() {
        return this.problems;
    }

    public Problem getProblem(int i) {
        return this.problems.get(i);
    }

    public void run() {

        GAUserCtrl gaController = new GAUserCtrl(this);
        MainUI mainUI = new MainUI(this, gaController);
        this.addView(mainUI);

        this.mainFrame = new JFrame();
        this.mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.mainFrame.setBounds(0, 0, 1280, 800);
        this.mainFrame.add(mainUI);
        this.mainFrame.setVisible(true);
    }    

    protected void start() {
        this.geneticEngine = new GeneticEngine(SelectedProblem);
        
        this.notifyViews(new ReadyToStartEvent(this, (GeneticEngineUI)this.geneticEngine.getUI()));
    }
    
    public void quit(){
        this.mainFrame.setVisible(false);
        this.mainFrame = null;
        this.geneticEngine.stop();
    }

    public static void main(String[] args) {
        GeneticAlgorithm ga = new GeneticAlgorithm();
        ga.addProblem(new Min1D());
        ga.run();
    }
}
