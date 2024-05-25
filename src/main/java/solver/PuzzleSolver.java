package solver;

import models.Position;
import puzzle.State;
import puzzle.TwoPhaseMoveState;
import puzzle.solver.BreadthFirstSearch;
import states.BoardGameModule;

public class PuzzleSolver {

    public static void main(String[] args) {
        State temp = new BoardGameModule();
        var bfs = new BreadthFirstSearch<TwoPhaseMoveState.TwoPhaseMove<Position>>();
        bfs.solveAndPrintSolution(temp);
    }
}