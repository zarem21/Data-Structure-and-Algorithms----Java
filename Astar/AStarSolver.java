package astar;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;


/**
 * @see ShortestPathsSolver for more method documentation
 */
public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private TreeMapMinPQ<Vertex> fringe = new TreeMapMinPQ<>();
    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private int numStatesExplored;
    private double explorationTime;
    private Map<Vertex, Double> distTo;
    private Map<Vertex, Vertex> edgeTo;

    /**
     * Immediately solves and stores the result of running memory optimized A*
     * search, computing everything necessary for all other methods to return
     * their results in constant time. The timeout is given in seconds.
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        solution = new ArrayList<>();
        Stopwatch sw = new Stopwatch();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        distTo.put(start, 0.0);
        edgeTo.put(start, null);
        fringe.add(start, input.estimatedDistanceToGoal(start, end));

        while (sw.elapsedTime() <= timeout && !fringe.isEmpty()) {
            Vertex popped = fringe.removeSmallest();
            if (popped.equals(end)) {
                Vertex current = end;
                solution.add(current);
                while (!current.equals(start)) {
                    current = edgeTo.get(current);
                    solution.add(current);
                }
                Collections.reverse(solution);
                solutionWeight = distTo.get(end);
                outcome = SolverOutcome.SOLVED;
                explorationTime = sw.elapsedTime();
                return;
            }
            numStatesExplored++;
            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(popped);
            for (WeightedEdge<Vertex> edge : neighborEdges) {
                double potentialDist = distTo.get(popped) + edge.weight();
                double childHeuristic = input.estimatedDistanceToGoal(edge.to(), end);
                if (!fringe.contains(edge.to()) && !distTo.containsKey(edge.to())) {
                    edgeTo.put(edge.to(), popped);
                    distTo.put(edge.to(), potentialDist);
                    fringe.add(edge.to(), potentialDist + childHeuristic);
                } else if (potentialDist < distTo.get(edge.to())) {
                    distTo.replace(edge.to(), potentialDist);
                    edgeTo.replace(edge.to(), popped);
                    fringe.changePriority(edge.to(), potentialDist + childHeuristic);
                }
            }
        }

            if (sw.elapsedTime() > timeout) {
                outcome = SolverOutcome.TIMEOUT;
            } else {
                outcome = SolverOutcome.UNSOLVABLE;
            }
            solutionWeight = Double.POSITIVE_INFINITY;
            explorationTime = sw.elapsedTime();

    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    /** The total number of priority queue removeSmallest operations. */
    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return explorationTime;
    }
}
