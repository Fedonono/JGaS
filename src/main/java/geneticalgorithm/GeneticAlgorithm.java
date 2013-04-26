package geneticalgorithm;

import GraphicalComponents.CustomFrame;
import MvcPattern.Model;
import geneticalgorithm.Problems.Extremum.Extremum1D;
import geneticalgorithm.Problems.Extremum.Extremum2D;
import geneticalgorithm.Problems.Problem;
import geneticalgorithm.Problems.ProblemUI;
import geneticalgorithm.engine.GeneticEngine;
import geneticalgorithm.engine.GeneticEngineUI;
import java.awt.BorderLayout;
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
    private CustomFrame mainFrame;

    public GeneticAlgorithm() {
        this.problems = new LinkedList<>();

    }

    public void setSelectedProblem(Problem SelectedProblem) {
        this.SelectedProblem = SelectedProblem;
        this.start();
    }

    public void addProblem(Problem problem) {
        this.problems.add(problem);
        problem.addView(new ProblemUI(problem));
        if (this.SelectedProblem == null) {
            this.SelectedProblem = problem;
        }

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
        this.geneticEngine = new GeneticEngine(this.SelectedProblem);

        MainUI mainUI = new MainUI(this, gaController, (GeneticEngineUI) this.geneticEngine.getUI());
        this.addView(mainUI);

        this.mainFrame = new CustomFrame();
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.mainFrame.setSize(1280, 800);
        this.mainFrame.add(mainUI, BorderLayout.CENTER);
        this.mainFrame.setVisible(true);
        mainUI.addObserver(this.mainFrame);
    }

    protected void start() {
        this.geneticEngine = new GeneticEngine(this.SelectedProblem);
        this.notifyViews(new ReadyToStartEvent(this, (GeneticEngineUI) this.geneticEngine.getUI()));
    }

    public void quit() {
        this.mainFrame.setVisible(false);
        this.mainFrame = null;
        this.geneticEngine.pause();

    }

    public static void main(String[] args) {
        GeneticAlgorithm ga = new GeneticAlgorithm();
        ga.addProblem(new Extremum2D());
        ga.addProblem(new Extremum1D());
        ga.run();
    }
}
