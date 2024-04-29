package com.example.artificial_intelligence_project1;

import java.util.ArrayList;
import java.util.List;

enum Position {RIGHT, LEFT}

public class State {

    private int cannibal_Left;
    private int missionary_Left;
    private int cannibal_Right;
    private int missionary_Right;
    private Position boat;

    private State parentState;


    public State(int cannibal_Left, int missionary_Left, Position boat,
                 int cannibal_Right, int missionary_Right) {
        this.cannibal_Left = cannibal_Left;
        this.missionary_Left = missionary_Left;
        this.boat = boat;
        this.cannibal_Right = cannibal_Right;
        this.missionary_Right = missionary_Right;
    }

    public int getcannibal_Left() {
        return cannibal_Left;
    }

    public void setcannibal_Left(int cannibal_Left) {
        this.cannibal_Left = cannibal_Left;
    }

    public int getmissionary_Left() {
        return missionary_Left;
    }

    public void setmissionary_Left(int missionary_Left) {
        this.missionary_Left = missionary_Left;
    }

    public int getcannibal_Right() {
        return cannibal_Right;
    }

    public void setcannibal_Right(int cannibal_Right) {
        this.cannibal_Right = cannibal_Right;
    }

    public int getmissionary_Right() {
        return missionary_Right;
    }

    public void setmissionary_Right(int missionary_Right) {
        this.missionary_Right = missionary_Right;
    }

    public void goToLeft() {
        boat = Position.LEFT;
    }

    public void goToRight() {
        boat = Position.RIGHT;
    }

    public boolean isOnLeft() {
        return boat == Position.LEFT;
    }

    public boolean isOnRigth() {
        return boat == Position.RIGHT;
    }

    public State getParentState() {
        return parentState;
    }

    public void setParentState(State parentState) {
        this.parentState = parentState;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof State)) {
            return false;
        }
        State s = (State) obj;
        return (s.cannibal_Left == cannibal_Left && s.missionary_Left == missionary_Left
                && s.boat == boat && s.cannibal_Right == cannibal_Right
                && s.missionary_Right == missionary_Right);
    }

    public boolean isGoal() {
        return cannibal_Left == 0 && missionary_Left == 0;
    }

    public boolean isValid() {
        if (missionary_Left >= 0 && missionary_Right >= 0 && cannibal_Left >= 0 && cannibal_Right >= 0) {
            if ((missionary_Left == 0 || missionary_Left >= cannibal_Left)
                    && (missionary_Right == 0 || missionary_Right >= cannibal_Right)) {
                return true;
            }
        }
        return false;
    }

    public List<State> buildSuccessorStates() {
        List<State> successors = new ArrayList<State>();
        if (boat == Position.LEFT) {
            checkThenInsert(successors, new State(cannibal_Left, missionary_Left - 2, Position.RIGHT,
                    cannibal_Right, missionary_Right + 2)); 
            checkThenInsert(successors, new State(cannibal_Left - 2, missionary_Left, Position.RIGHT,
                    cannibal_Right + 2, missionary_Right));
            checkThenInsert(successors, new State(cannibal_Left - 1, missionary_Left - 1, Position.RIGHT,
                    cannibal_Right + 1, missionary_Right + 1));
            checkThenInsert(successors, new State(cannibal_Left, missionary_Left - 1, Position.RIGHT,
                    cannibal_Right, missionary_Right + 1)); 
            checkThenInsert(successors, new State(cannibal_Left - 1, missionary_Left, Position.RIGHT,
                    cannibal_Right + 1, missionary_Right));
        } else {
            checkThenInsert(successors, new State(cannibal_Left, missionary_Left + 2, Position.LEFT,
                    cannibal_Right, missionary_Right - 2)); 
            checkThenInsert(successors, new State(cannibal_Left + 2, missionary_Left, Position.LEFT,
                    cannibal_Right - 2, missionary_Right));
            checkThenInsert(successors, new State(cannibal_Left + 1, missionary_Left + 1, Position.LEFT,
                    cannibal_Right - 1, missionary_Right - 1)); 
            checkThenInsert(successors, new State(cannibal_Left, missionary_Left + 1, Position.LEFT,
                    cannibal_Right, missionary_Right - 1)); 
            checkThenInsert(successors, new State(cannibal_Left + 1, missionary_Left, Position.LEFT,
                    cannibal_Right - 1, missionary_Right));
        }
        return successors;
    }

    private void checkThenInsert(List<State> successors, State newState) {
        if (newState.isValid()) {
            newState.setParentState(this);
            successors.add(newState);
        }
    }


}
