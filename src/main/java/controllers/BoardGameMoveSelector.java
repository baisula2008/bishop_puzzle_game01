package controllers;


import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import models.Position;
import puzzle.TwoPhaseMoveState;
import states.BoardGameModule;

public class BoardGameMoveSelector {

    public enum Phase{
        SELECT_FROM,
        SELECT_TO,
        READY_TO_MOVE
    }

    private BoardGameModule module;

    private ReadOnlyObjectWrapper<Phase> phase = new ReadOnlyObjectWrapper<>(Phase.SELECT_FROM);
    private boolean invalidSelection = false;
    private Position from;
    private Position to;

    public BoardGameMoveSelector(BoardGameModule module){
        this.module = module;
    }
    public ReadOnlyObjectProperty<Phase> phaseProperty(){
        return phase.getReadOnlyProperty();
    }
    public boolean isReadyToMove(){
        return phase.get() == Phase.READY_TO_MOVE;
    }
    public void select(Position position){
        switch(phase.get()){
            case SELECT_FROM -> selectFrom(position);
            case SELECT_TO -> selectTo(position);
            case READY_TO_MOVE -> throw new IllegalStateException();
        }
    }
    private void selectFrom(Position position){
        if (!module.isEmpty(position)){
            from = position;
            phase.set(Phase.SELECT_TO);
            invalidSelection = false;
        }else {
            invalidSelection = true;
        }
    }
    private void selectTo(Position position){
        if (module.isLegalMove(new TwoPhaseMoveState.TwoPhaseMove(from, position))){
            to = position;
            phase.set(Phase.READY_TO_MOVE);
            invalidSelection = false;
        }else {
            invalidSelection = true;
        }
    }
    public Position getFrom(){
        if (phase.get()==Phase.SELECT_FROM){
            throw new IllegalStateException();
        }
        return from;
    }

    public boolean isInvalidSelection(){
        return invalidSelection;
    }
    public void makeMove(){
        if (phase.get()!=Phase.READY_TO_MOVE){
            throw new IllegalStateException();
        }
        module.makeMove(new TwoPhaseMoveState.TwoPhaseMove(from, to));
        reset();
    }
    public void reset(){
        from = null;
        to = null;
        phase.set(Phase.SELECT_FROM);
        invalidSelection = false;
    }


}
