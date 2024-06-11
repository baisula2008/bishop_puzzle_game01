package states;

import models.Position;
import models.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


public class BoardGameModuleTest {

    private BoardGameModule boardGameModule;

    @BeforeEach
    void setUp() {
        boardGameModule = new BoardGameModule();
    }

    @Test
    void squareProperty() {
        assertNotNull(boardGameModule.squareProperty(0, 1));
        assertEquals(Square.BLACK, boardGameModule.squareProperty(0, 1).get());
    }

    @Test
    void isEmpty() {
        Position emptyPos = new Position(2, 2);
        Position nonEmptyPos = new Position(0, 1);
        assertTrue(boardGameModule.isEmpty(emptyPos));
        assertFalse(boardGameModule.isEmpty(nonEmptyPos));
    }

    @Test
    void isOnBoard() {
        Position validPos = new Position(2, 2);
        Position invalidPos = new Position(5, 5);
        assertTrue(BoardGameModule.isOnBoard(validPos));
        assertFalse(BoardGameModule.isOnBoard(invalidPos));
    }

    @Test
    void isDiagonalMove() {
        Position from = new Position(1, 1);
        Position to = new Position(2, 2);
        Position nonDiagonal = new Position(1, 2);
        assertTrue(BoardGameModule.isDiagonalMove(from, to));
        assertFalse(BoardGameModule.isDiagonalMove(from, nonDiagonal));
    }
}