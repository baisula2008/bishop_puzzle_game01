[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/f0r53tPY)
# Bishops Swapping

Consider a game board consisting of 5 rows and 4 columns. The game is played with 2 black bishop and 2 white bishop chess pieces. Initially, the 2 black bishops are placed in the top row, while the 2 white bishops are placed in the bottom row as shown below (B's represent black bishops, and W's represent white bishops, respectively):

+---+---+---+---+
|   | B |   | B |
+---+---+---+---+
|   |   |   |   |
+---+---+---+---+
|   |   |   |   |
+---+---+---+---+
|   |   |   |   |
+---+---+---+---+
|   | W |   | W |
+---+---+---+---+
The goal of the game is to swap the pieces on the board, i.e., to obtain the following configuration:

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
The pieces move according to the rules of the chess. A piece is not allowed to be moved to a square that is under attack by any piece of the opposite color. Black pieces and white pieces are not required to move in turn. Any of the pieces can be moved in the first move.

The optimal solution:
0 B 0 B
0 0 0 0
0 0 0 0
0 0 0 0
0 W 0 W

TwoPhaseMove[from=(0,1), to=(1,2)]
0 0 0 B
0 0 B 0
0 0 0 0
0 0 0 0
0 W 0 W

TwoPhaseMove[from=(4,3), to=(1,0)]
0 0 0 B
W 0 B 0
0 0 0 0
0 0 0 0
0 W 0 0

TwoPhaseMove[from=(4,1), to=(3,2)]
0 0 0 B
W 0 B 0
0 0 0 0
0 0 W 0
0 0 0 0

TwoPhaseMove[from=(1,2), to=(3,0)]
0 0 0 B
W 0 0 0
0 0 0 0
B 0 W 0
0 0 0 0

TwoPhaseMove[from=(1,0), to=(0,1)]
0 W 0 B
0 0 0 0
0 0 0 0
B 0 W 0
0 0 0 0

TwoPhaseMove[from=(3,2), to=(2,3)]
0 W 0 B
0 0 0 0
0 0 0 W
B 0 0 0
0 0 0 0

TwoPhaseMove[from=(3,0), to=(2,1)]
0 W 0 B
0 0 0 0
0 B 0 W
0 0 0 0
0 0 0 0

TwoPhaseMove[from=(2,1), to=(4,3)]
0 W 0 B
0 0 0 0
0 0 0 W
0 0 0 0
0 0 0 B

TwoPhaseMove[from=(0,3), to=(2,1)]
0 W 0 0
0 0 0 0
0 B 0 W
0 0 0 0
0 0 0 B

TwoPhaseMove[from=(2,3), to=(4,1)]
0 W 0 0
0 0 0 0
0 B 0 0
0 0 0 0
0 W 0 B

TwoPhaseMove[from=(0,1), to=(2,3)]
0 0 0 0
0 0 0 0
0 B 0 W
0 0 0 0
0 W 0 B

TwoPhaseMove[from=(2,1), to=(1,0)]
0 0 0 0
B 0 0 0
0 0 0 W
0 0 0 0
0 W 0 B

TwoPhaseMove[from=(2,3), to=(1,2)]
0 0 0 0
B 0 W 0
0 0 0 0
0 0 0 0
0 W 0 B

TwoPhaseMove[from=(4,1), to=(3,0)]
0 0 0 0
B 0 W 0
0 0 0 0
W 0 0 0
0 0 0 B

TwoPhaseMove[from=(1,0), to=(3,2)]
0 0 0 0
0 0 W 0
0 0 0 0
W 0 B 0
0 0 0 B

TwoPhaseMove[from=(1,2), to=(0,1)]
0 W 0 0
0 0 0 0
0 0 0 0
W 0 B 0
0 0 0 B

TwoPhaseMove[from=(3,0), to=(0,3)]
0 W 0 W
0 0 0 0
0 0 0 0
0 0 B 0
0 0 0 B

TwoPhaseMove[from=(3,2), to=(4,1)]
0 W 0 W
0 0 0 0
0 0 0 0
0 0 0 0
0 B 0 B 
