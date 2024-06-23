package states;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import models.Position;
import models.Square;
import puzzle.TwoPhaseMoveState;

import java.util.*;

public class BoardGameModule implements TwoPhaseMoveState<TwoPhaseMoveState.TwoPhaseMove>{

    private static final int BOARD_SIZE = 5;

    private final ReadOnlyObjectWrapper<Square>[][] board;

    public BoardGameModule(){
        this.board = new ReadOnlyObjectWrapper[BOARD_SIZE][4];
        for (var i = 0; i < BOARD_SIZE; i++) {
            for (var j = 0; j < 4; j++) {
                this.board[i][j] = new ReadOnlyObjectWrapper<>(
                        switch (i){
                            case 0 -> (j % 2 != 0) ? Square.BLACK : Square.NONE;
                            case BOARD_SIZE -1 ->(j % 2 != 0) ? Square.WHITE : Square.NONE;
                            default -> Square.NONE;
                        }
                );
            }
        }
    }

    public BoardGameModule(ReadOnlyObjectWrapper<Square>[][] inputBoard){
        this.board = inputBoard;
    }
    public ReadOnlyObjectProperty<Square> squareProperty(int i , int j){
        return this.board[i][j].getReadOnlyProperty();
    }
    private Square getSquare(Position p){
        return this.board[p.row()][p.col()].get();
    }
    private void setSquare(Position p, Square square){
        this.board[p.row()][p.col()].set(square);
    }
    public boolean isEmpty(Position p){
        return getSquare(p)==Square.NONE;
    }
    public static boolean isOnBoard(Position p){
        return 0<=p.row() && p.col() < BOARD_SIZE && 0<=p.col() && p.col() < 4;
    }
    public static boolean isDiagonalMove(Position from, Position to) {
        var dx = Math.abs(to.row() - from.row());
        var dy = Math.abs(to.col() - from.col());
        return dx == dy && dx != 0;
    }



    @Override
    public boolean isLegalToMoveFrom(TwoPhaseMove move) {
        Set allLegalMoves = getLegalMoves();
        boolean isLegalToMove = false;
        for(Object validMove : allLegalMoves) {
            TwoPhaseMove tempMove = (TwoPhaseMove) validMove;
            if (tempMove.from() == move.from()) {
                isLegalToMove = true;
                break;
            }
        }
        return isLegalToMove;
    }

    @Override
    public boolean isSolved() {
        return this.board[0][1].get() == Square.WHITE && this.board[0][3].get() == Square.WHITE && this.board[BOARD_SIZE - 1][1].get() == Square.BLACK && this.board[BOARD_SIZE - 1][3].get() == Square.BLACK;
    }

    @Override
    public boolean isLegalMove(TwoPhaseMove move) {
        Position from = (Position) move.from();
        Position to = (Position) move.to();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < 4; j++) {
                Position temp = new Position(i, j);
                if (!isEmpty(temp) && getSquare(from) != getSquare(temp)){
                    if (isDiagonalMove(temp, to)) return false;
                }
            }
        }
        return isOnBoard(from) && isOnBoard(to) && isEmpty(to) && isDiagonalMove(from, to);
    }

    @Override
    public void makeMove(TwoPhaseMove move) {
        Position from = (Position) move.from();
        Position to = (Position) move.to();
        setSquare(to, getSquare(from));
        setSquare(from,Square.NONE);
    }

    @Override
    public Set getLegalMoves() {
        Set tempSet = new HashSet<TwoPhaseMove>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < 4; j++) {
                Position temp1 = new Position(i, j);
                if (!isEmpty(temp1)){
                    for (int x = 0; x < BOARD_SIZE; x++) {
                        for (int y = 0; y < 4; y++) {
                            Position temp2 = new Position(x, y);
                            TwoPhaseMove twoPhaseMove = new TwoPhaseMove(temp1, temp2);
                            if (isLegalMove(twoPhaseMove)) {
                                tempSet.add(twoPhaseMove);
                            }
                        }
                    }
                }
            }
        }
        return tempSet;
    }

    @Override
    public boolean equals(Object o) {
        BoardGameModule boardGameModule = (BoardGameModule) o;
        boolean result = true;
        for (var i = 0; i < BOARD_SIZE; i++) {
            for (var j = 0; j < 4; j++) {
                if (boardGameModule.board[i][j].get() != this.board[i][j].get())    result = false;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        List positions = getSetOfSquares();
        return Objects.hash(positions.get(0), positions.get(1),
                positions.get(2), positions.get(3));
    }

    @Override
    public String toString() {
        StringBuilder boardInText = new StringBuilder("\n");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < 4; j++) {
                Position pos = new Position(i, j);
                if (board[pos.row()][pos.col()].get() == Square.WHITE){
                    boardInText.append("W");
                } else if (board[pos.row()][pos.col()].get() == Square.BLACK) {
                    boardInText.append("B");
                } else {
                    boardInText.append("0");
                }
                boardInText.append(" ");
            }
            boardInText.append("\n");
        }
        return boardInText.toString();
    }

    @Override
    public BoardGameModule clone() {
        var tempBoard = new ReadOnlyObjectWrapper[BOARD_SIZE][4];
        for (var i = 0; i < BOARD_SIZE; i++) {
            for (var j = 0; j < 4; j++) {
                tempBoard[i][j] = new ReadOnlyObjectWrapper<>(Square.NONE);
            }
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < 4; j++) {
                Position pos = new Position(i, j);
                switch (this.board[pos.row()][pos.col()].get()) {
                    case BLACK -> tempBoard[i][j] = new ReadOnlyObjectWrapper<>(Square.BLACK);
                    case WHITE -> tempBoard[i][j] = new ReadOnlyObjectWrapper<>(Square.WHITE);
                }
            }
        }
        var temp = new BoardGameModule(tempBoard);
        return temp;
    }

    private List<Position> getSetOfSquares() {
        List<Position> temp = new ArrayList<Position>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < 4; j++) {
                Position pos = new Position(i, j);
                if (!isEmpty(pos))
                    temp.add(pos);
            }
        }
        return temp;
    }
}
