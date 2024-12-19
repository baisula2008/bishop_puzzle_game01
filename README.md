Bishops Swapping

Project Overview:This project demonstrates the efficient swapping of the positions of two black bishops and two white bishops on a 5x4 chessboard using Java. The solution showcases the application of chess movement rules, strategic decision-making, and optimized pathfinding techniques in a restricted environment.

Game Description

Board Configuration

Dimensions: 5 rows x 4 columns.

Initial State:

Top row: 2 Black bishops (B).

Bottom row: 2 White bishops (W).

Goal State: Swap the positions of black and white bishops to achieve:

+---+---+---+---+
|   | W |   | W |
+---+---+---+---+
|   |   |   |   |
+---+---+---+---+
|   |   |   |   |
+---+---+---+---+
|   |   |   |   |
+---+---+---+---+
|   | B |   | B |
+---+---+---+---+

Rules

Movement: Bishops move diagonally, following standard chess rules.

Attack Restriction: Bishops cannot move to squares under attack by opposing pieces.

Turn Flexibility: Moves are not turn-based; any piece can be moved at any time.

Gameplay Mechanics

The game starts with bishops in the initial positions.

Players or the program moves the bishops step by step following the rules to reach the goal state.

The program ensures that no invalid moves are made and calculates the optimal sequence of moves.

Implementation Details

Features

Two-Phase Movement: The project identifies the most efficient sequence of moves to swap positions while adhering to the rules.

Rule Enforcement: Ensures all moves follow chess mechanics and avoid invalid states.

Optimized Solution: Utilizes the Breadth-First Search (BFS) algorithm to find the shortest path to the goal state.

Interactive Simulation: Clear step-by-step representation of bishop movements for visualization.

Test Coverage: Implements JUnit tests to ensure the correctness of the solution.

Technologies and Libraries Used

Programming Language: Java

Libraries/Frameworks:

TinyLog: Used for lightweight logging throughout the application.

Lombok: Reduces boilerplate code with annotations like @Getter, @Setter, and @Data.

JavaFX: Provides a graphical user interface for visualizing the board and movements.

JUnit: Facilitates unit testing to ensure the program works as expected.

Maven (mvn): Used for project management and dependency integration.

Design Pattern: MVC (Model-View-Controller) ensures a clean and maintainable code structure.

Algorithms and Data Structures

Breadth-First Search (BFS): Used to find the shortest path for bishop movements while avoiding conflicts.

2D Array: Represents the game board and tracks the positions of all bishops.

Coordinate Pairs: Represents the moves in a structured form.

Example Gameplay Sequence

Move 1: Black bishop from (0,1) to (1,2)

+---+---+---+---+
|   |   |   | B |
+---+---+---+---+
|   |   | B |   |
+---+---+---+---+
|   |   |   |   |
+---+---+---+---+
|   |   |   |   |
+---+---+---+---+
|   | W |   | W |
+---+---+---+---+

Move 2: White bishop from (4,3) to (1,0)

+---+---+---+---+
|   |   |   | B |
+---+---+---+---+
| W |   | B |   |
+---+---+---+---+
|   |   |   |   |
+---+---+---+---+
|   |   |   |   |
+---+---+---+---+
|   | W |   |   |
+---+---+---+---+

Move 3: White bishop from (4,1) to (3,2)

+---+---+---+---+
|   |   |   | B |
+---+---+---+---+
| W |   | B |   |
+---+---+---+---+
|   |   |   |   |
+---+---+---+---+
|   |   | W |   |
+---+---+---+---+
|   |   |   |   |
+---+---+---+---+

... (and so on until the goal state is achieved).
