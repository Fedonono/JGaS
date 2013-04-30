package geneticalgorithm;

import GraphicalComponents.CustomFrame;
import MvcPattern.Model;
import geneticalgorithm.Extremum.Extremum1D.Extremum1D;
import geneticalgorithm.Extremum.Extremum2D.Extremum2D;
import geneticalgorithm.Problem.Problem;
import geneticalgorithm.TSP.TSP;
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
    private Problem selectedProblem;
    private GeneticEngine geneticEngine;
    private CustomFrame mainFrame;

    /**
     *
     */
    public GeneticAlgorithm() {
        this.problems = new LinkedList<>();

    }

    public Problem getSelectedProblem() {
        return selectedProblem;
    }

    /**
     * set the selected problem with selectedProblem.
     *
     * @param SelectedProblem
     */
    public void setSelectedProblem(Problem SelectedProblem) {
        this.selectedProblem = SelectedProblem;
        this.start();
    }

    /**
     * add a problem to 'this'.
     *
     * @param problem
     */
    public void addProblem(Problem problem) {
        this.problems.add(problem);
        this.selectedProblem = problem;
    }

    /**
     * add all problems from foreignproblems to 'this'.
     *
     * @param foreignProblems
     */
    public void addAll(Collection<Problem> foreignProblems) {
        for (Problem pb : foreignProblems) {
            this.addProblem(pb);
        }
    }

    /**
     * return 'this' problems.
     *
     * @return
     */
    public LinkedList<Problem> getProblems() {
        return this.problems;
    }

    /**
     *
     * @param index
     * @return
     */
    public Problem getProblem(int index) {
        return this.problems.get(index);
    }

    /**
     * run the genetic algorithm.
     */
    public void run() {

        GAUserCtrl gaController = new GAUserCtrl(this);
        this.geneticEngine = new GeneticEngine(this.selectedProblem);

        GeneticAlgorithmUI mainUI = new GeneticAlgorithmUI(this, gaController, (GeneticEngineUI) this.geneticEngine.getUI());
        this.addView(mainUI);

        this.mainFrame = new CustomFrame();
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.mainFrame.setSize(1280, 800);
        this.mainFrame.add(mainUI, BorderLayout.CENTER);
        this.mainFrame.setVisible(true);
        mainUI.addObserver(this.mainFrame);
    }

    /**
     *
     */
    protected void start() {
        this.geneticEngine.setProblem(this.selectedProblem);
        this.notifyViews(new ReadyToStartEvent(this, (GeneticEngineUI) this.geneticEngine.getUI()));
    }

    /**
     * quit the geneticAlgorithm application.
     */
    public void quit() {
        this.mainFrame.setVisible(false);
        this.mainFrame = null;
        this.geneticEngine.pause();

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        GeneticAlgorithm ga = new GeneticAlgorithm();
        ga.addProblem(new TSP());
        ga.addProblem(new Extremum1D());
        ga.addProblem(new Extremum2D());
        ga.run();
    }
}
