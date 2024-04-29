package com.example.artificial_intelligence_project1;

import java.util.ArrayList;
import java.util.List;

public class SolutionPrinter {


    public void printSolution(String searchMethod, State solution) {
        if (solution != null) {
            List<State> path = new ArrayList<>();
            State state = solution;
            while (state != null) {
                path.add(state);
                state = state.getParentState();
            }
            int depth = path.size() - 1;
            int i = depth;
            while (i >= 0) {
                state = path.get(i);
                if (state.isOnLeft()) {
                    MainApplication.list.add("Left-" + state.getcannibal_Left() + "C," + state.getmissionary_Left() + "M-"
                            + state.getcannibal_Right() + "C," + state.getmissionary_Right() + "M");
                } else {
                    MainApplication.list.add("Right-" + state.getcannibal_Left() + "C," + state.getmissionary_Left() + "M-"
                            + state.getcannibal_Right() + "C," + state.getmissionary_Right() + "M");
                }

                i--;
            }
            MainApplication.list.add(""+depth);
        }
    }
}
