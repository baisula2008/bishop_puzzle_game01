package states;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import models.Position;
import models.Square;
import puzzle.TwoPhaseMoveState;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: willson2008
 * @Date: 15/03/2024 - 03 - 15 - 19:06
 * @Description: org.example.demo2.org.example.demo2
 * @Version: 1.0
 */
public class BoardGameModule implements TwoPhaseMoveState<TwoPhaseMoveState.TwoPhaseMove>{

    public static final int BOARD_SIZE = 5;

    private ReadOnlyObjectWrapper<Square>[][] board;

    public BoardGameModule(){
        board = new ReadOnlyObjectWrapper[BOARD_SIZE][4];
        for (var i = 0; i < BOARD_SIZE; i++) {
            for (var j = 0; j < 4; j++) {
                board[i][j] = new ReadOnlyObjectWrapper<>(
                        switch (i){
                            case 0 -> (j % 2 != 0) ? Square.BLACK : Square.NONE;
                            case BOARD_SIZE -1 ->(j % 2 != 0) ? Square.WHITE : Square.NONE;
                            default -> Square.NONE;
                        }
                );
            }
        }
    }
    public ReadOnlyObjectProperty<Square> squareProperty(int i , int j){
        return board[i][j].getReadOnlyProperty();
    }
    public Square getSquare(Position p){
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

    public void reset() {
        // 重置棋盘上的所有棋子状态
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < 4; j++) {
                switch (i) {
                    case 0, BOARD_SIZE - 1 -> board[i][j].set((j % 2 != 0) ? Square.BLACK : Square.NONE);
                    default -> board[i][j].set(Square.NONE);
                }
            }
        }
    }

    @Override
    public boolean isLegalToMoveFrom(TwoPhaseMove move) {
        return !isEmpty((Position) move.from());
    }

    @Override
    public boolean isSolved() {
        return board[0][1].get() == Square.WHITE && board[0][3].get() == Square.WHITE && board[BOARD_SIZE - 1][1].get() == Square.BLACK && board[BOARD_SIZE - 1][3].get() == Square.BLACK;
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
        return isOnBoard(from) && isOnBoard(to) && !isEmpty(from) && isEmpty(to) && isDiagonalMove(from, to);
    }

    @Override
    public void makeMove(TwoPhaseMove move) {
        System.out.println("current move:" + move.toString());
        Position from = (Position) move.from();
        Position to = (Position) move.to();
        setSquare(to, getSquare(from));
        setSquare(from,Square.NONE);
        System.out.println("made move");
    }

    @Override
    public Set getLegalMoves() {
        Set tempSet = new HashSet<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < 4; j++) {
                Position temp1 = new Position(i, j);
                if (!isEmpty(temp1)){
                    for (int x = 0; x < BOARD_SIZE; x++) {
                        for (int y = 0; y < 4; y++) {
                            Position temp2 = new Position(x, y);
                            TwoPhaseMove twoPhaseMove = new TwoPhaseMove(temp1, temp2);
                            if (isLegalMove(twoPhaseMove)) {
                                System.out.println(twoPhaseMove.toString());
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardGameModule that = (BoardGameModule) o;
        return Arrays.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    @Override
    public String toString() {
        return "BoardGameModule{" +
                "board=" + Arrays.toString(board) +
                '}';
    }

    @Override
    public BoardGameModule clone() {
        BoardGameModule copy = null;
        try {
            copy = (BoardGameModule) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
        copy.board = board.clone();
        return copy;
    }
}
