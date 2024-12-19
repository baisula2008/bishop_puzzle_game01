Bishops Swapping
Overview
This project implements a simulation to swap the positions of two black bishops and two white bishops on a 5x4 chessboard, adhering to chess movement rules. The project optimizes for clarity, efficiency, and adherence to constraints, offering an interactive and algorithmically robust solution.

Game Description
Board Configuration
Board Dimensions: 5 rows × 4 columns.
Initial Setup:
Top Row: 2 Black Bishops (B).
Bottom Row: 2 White Bishops (W).
Goal: Swap the positions of black and white bishops to achieve:

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
Bishop Movement: Bishops move diagonally, following standard chess rules.
No Conflicts: A bishop cannot move to a position under attack by the opposing bishop.
Flexibility: The sequence of moves is not turn-based; any bishop can be moved first.
Objective
Swap black and white bishops while respecting movement constraints, ensuring no invalid moves, and finding the optimal solution.

Features
Interactive Simulation: A graphical interface displays each bishop's movement step-by-step.
Rule Enforcement: Chess constraints are rigorously followed.
Optimal Algorithm: Uses Breadth-First Search (BFS) to minimize the number of moves.
Testing & Debugging: Ensures code quality using JUnit tests for corner cases.
Two-Phase Logic: Plans efficient, rule-compliant steps for swapping bishops.
Tools & Technologies
Programming
Language: Java
Frameworks/Libraries
JavaFX: For interactive visualization of the game board and movements.
TinyLog: Lightweight logging is used to monitor program execution.
Lombok: Reduces boilerplate code with annotations (@Getter, @Setter, @Data).
JUnit: Ensures robust functionality through unit tests.
Maven: Dependency integration and project management.
Architecture & Design
MVC Design Pattern: Ensures clean, maintainable, and scalable code organization.
Algorithms & Data Structures
Breadth-First Search (BFS): Computes the shortest path to swap positions while avoiding invalid states.
2D Array: Represents the chessboard layout and tracks bishop positions.
Coordinate System: Encodes positions and moves to facilitate calculations.
Usage Instructions

Clone the Repository:
git clone <repository-url>
Build the Project:
mvn compile
Run the Program:
mvn javafx:run
Execute Unit Tests:
mvn test
Interactive Experience: View the movement visualization in the graphical interface.
Example Gameplay
Move 1: Black bishop from (0,1) to (1,2).


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
Move 2: White bishop from (4,3) to (1,0).


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
Move 3: White bishop from (4,1) to (3,2).


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
(Repeat steps until the goal state is achieved.)

Applications
Chess Simulations: For educational and game-related purposes.
Algorithm Demonstrations: BFS application in problem-solving.
Path Optimization: Strategy development in constrained setups.
