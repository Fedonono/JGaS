package geneticalgorithm;

import GraphicalComponents.CustomFrame;
import MvcPattern.Model;
import geneticalgorithm.Extremum.Extremum1D.Extremum1D;
import geneticalgorithm.Extremum.Extremum2D.Extremum2D;
import geneticalgorithm.Population.Population;
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
    private boolean UIvisible = false;

    /**
     *
     */
    public GeneticAlgorithm() {
        this.problems = new LinkedList<>();
        this.addProblem(new TSP());
        this.addProblem(new Extremum1D());
        this.addProblem(new Extremum2D());
        this.init();

    }

    /**
     *
     * @return
     */
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
        this.restart();
    }

    /**
     * add a problem to 'this'.
     *
     * @param problem
     */
    public final void addProblem(Problem problem) {
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
     * @return 'this' available problems.
     */
    public LinkedList<Problem> getProblems() {
        return this.problems;
    }

    /**
     *
     * @param index
     * @return the problem from index 'index in 'this' availables problems.
     */
    public Problem getProblem(int index) {
        return this.problems.get(index);
    }

    /**
     * run the genetic algorithm.
     */
    private void init() {
        if (this.geneticEngine == null) {
            this.geneticEngine = new GeneticEngine(this.selectedProblem);
        } else {
            this.geneticEngine.setProblem(selectedProblem);
        }

        if (this.mainFrame == null) {
            GAUserCtrl gaController = new GAUserCtrl(this);
            GeneticAlgorithmUI mainUI = new GeneticAlgorithmUI(this, gaController, (GeneticEngineUI) this.geneticEngine.getUI());
            this.addView(mainUI);
            this.mainFrame = new CustomFrame();
            this.mainFrame.setLayout(new BorderLayout());
            this.mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.mainFrame.setSize(1280, 800);
            this.mainFrame.add(mainUI, BorderLayout.CENTER);
            this.mainFrame.setVisible(this.UIvisible);
            mainUI.addObserver(this.mainFrame);
        }
    }

    /**
     * set the interface graphique visibility.
     *
     * @param visible
     */
    public void setUIVisible(boolean visible) {
        this.UIvisible = false;
        this.mainFrame.setVisible(visible);
    }

    /**
     * pause the engine.
     */
    public void pause() {
        if (this.geneticEngine != null && !this.geneticEngine.isPaused()) {
            this.geneticEngine.pause();
        }
    }

    /**
     * run the engine.
     */
    public void run() {
        if (this.geneticEngine != null && this.geneticEngine.isPaused()) {
            this.geneticEngine.resume();
        }
    }

    /**
     * return the currentPopulation.
     *
     * @return
     */
    public Population getCurrentPopulation() {
        if (this.geneticEngine != null) {
            return this.geneticEngine.getPopulation();
        } else {
            throw new NeitherSelectedProblemException();

        }
    }

    /**
     * restart the engine.
     */
    public void restart() {
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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GeneticAlgorithm ga = new GeneticAlgorithm();
        ga.setUIVisible(true);
        ga.run();
    }
}
