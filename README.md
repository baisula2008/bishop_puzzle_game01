Bishops Swapping

Project Overview:
This project involves creating a simulation to swap the positions of two black bishop and two white bishop chess pieces on a 5x4 game board. The program demonstrates the steps and rules required to achieve the goal state efficiently, considering chess movement rules and restrictions.

Game Description

Board Configuration: The game board has 5 rows and 4 columns.

Initial State:

2 Black bishops (B) placed on the top row.

2 White bishops (W) placed on the bottom row.

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

Rules:

Bishops move according to chess rules (diagonal moves only).

A piece cannot move into a square attacked by an opposing piece.

Moves are not restricted by turns; any bishop can move first.

Implementation Details

Features:

Two-Phase Movement: The program outlines the moves required to reach the goal state in minimal steps while respecting the game rules.

Interactive Visualization: The movement of each bishop is displayed step by step for better clarity.

Rule Enforcement: Ensures that no move violates the allowed game constraints (e.g., invalid placements or attacks).

Optimized Solution: The program provides an optimal sequence of moves to achieve the desired configuration efficiently.

Example Sequence:

Move black bishop from (0,1) to (1,2).

Move white bishop from (4,3) to (1,0).

Move white bishop from (4,1) to (3,2).

Continue alternating movements until achieving the goal state.

The entire series of steps ensures that the bishops safely swap positions while abiding by chess movement rules.

Technologies Used:

Programming Language: Python

Data Structures: 2D Arrays for board representation

Usage:

Clone the repository.

Run the script to execute the simulation.

View the step-by-step progression from the initial to the goal state.

Applications:

This project demonstrates problem-solving in a constrained environment and can serve as a basis for:

Chess-based game simulations.

Learning pathfinding and movement algorithms.

Developing strategies for optimal move sequences in constrained setups.
