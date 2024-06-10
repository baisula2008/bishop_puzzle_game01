package controllers;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import models.Position;
import models.Square;
import states.BoardGameModule;

import java.util.logging.Level;
import java.util.logging.Logger;


public class BoardGameController {


    @FXML
    private Button logout;

    @FXML
    private Button reset;



    @FXML
    private GridPane board;
    private final BoardGameModule module = new BoardGameModule();
    private final BoardGameMoveSelector selector = new BoardGameMoveSelector(module);

    @FXML
    private void initialize() {
        for (int i = 0; i < board.getRowCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare(i, j);
                board.add(square, j, i);
            }
        }
        selector.phaseProperty().addListener(this::showSelectionPhaseChange);
    }

    private StackPane createSquare(int i, int j) {
        var square = new StackPane();
        square.getStyleClass().add("square");
        var piece = new Circle(40);
        piece.fillProperty().bind(createSquareBinding(module.squareProperty(i, j)));
        square.getChildren().add(piece);
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        Logger logger = Logger.getLogger(BoardGameController.class.getName());
        logger.log(Level.INFO, "click on square ({0},{1})", new Object[]{row, col});
        selector.select(new Position(row, col));
        if (selector.isReadyToMove()) {
            selector.makeMove();
            if (module.isSolved()) {
//                try {
//                    Application.changeScene("log_out.fxml");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }
        else if (selector.isInvalidSelection()) {
            try {
                hideSelection(selector.getFrom());
            }
            catch (Exception e) {
                return;
            }
            selector.reset();
        }
    }

    private ObjectBinding<Paint> createSquareBinding(ReadOnlyObjectProperty<Square> squareProperty) {
        return new ObjectBinding<Paint>() {
            {
                super.bind(squareProperty);
            }

            @Override
            protected Paint computeValue() {
                return switch (squareProperty.get()) {
                    case NONE -> Color.TRANSPARENT;
                    case BLACK -> Color.BLACK;
                    case WHITE -> Color.WHITE;
                };
            }
        };
    }


    private void showSelectionPhaseChange(ObservableValue<? extends BoardGameMoveSelector.Phase> value, BoardGameMoveSelector.Phase oldPhase, BoardGameMoveSelector.Phase newPhase){
        switch (newPhase){
            case SELECT_FROM -> {}
            case SELECT_TO -> showSelection(selector.getFrom());
            case READY_TO_MOVE -> hideSelection(selector.getFrom());
        }
    }
    private void showSelection(Position position){
        var square = getSquare(position);
        square.getStyleClass().add("selected");
    }
    private void hideSelection(Position position){
        var square = getSquare(position);
        square.getStyleClass().remove("selected");
    }
    private StackPane getSquare(Position position) {
        for (var child : board.getChildren()) {
            if (GridPane.getColumnIndex(child) != null && GridPane.getRowIndex(child) == position.row() && GridPane.getColumnIndex(child) == position.col()) {
                return (StackPane) child;
            }
        }
        throw new AssertionError();
    }

    private void setReset(BoardGameMoveSelector selector){
        selector.reset();
    }


}