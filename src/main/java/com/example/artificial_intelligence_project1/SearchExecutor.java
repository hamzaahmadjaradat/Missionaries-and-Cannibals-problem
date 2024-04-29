package com.example.artificial_intelligence_project1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class SearchExecutor {
    private final int DEFAULT_LIMIT = 11;

    private Set<State> explored; 

    private State goalState; 

    private SolutionPrinter printer; 

    public SearchExecutor() {
        explored = new HashSet<>();
        goalState = null;
        printer = new SolutionPrinter(); 
    }
    
    public void executeBFS(State initialState) {
        long startTime = System.currentTimeMillis(); 

        Queue<State> frontier = new LinkedList<>(); 
        Set<State> explored = new HashSet<>(); 
        frontier.add(initialState); 

        while (!frontier.isEmpty()) {
            State state = frontier.poll(); 
            explored.add(state); 

            if (state.isGoal()) {
                long endTime = System.currentTimeMillis(); 
                long executionTime = endTime - startTime; 

                printer.printSolution("BFS", state); 
                System.out.println("BFS Execution Time: " + executionTime + " milliseconds");
                return;
            }

            state.buildSuccessorStates().stream()
                .filter(child -> !explored.contains(child) && !frontier.contains(child))
                .forEach(frontier::add);
        }
        
        printer.printSolution("BFS", null); 
        System.out.println("BFS Execution Time: " + (System.currentTimeMillis() - startTime) + " milliseconds");
    }

    public void executeDFS(State initialState) {
        long startTime1 = System.currentTimeMillis(); 

        recursiveDFS(initialState, DEFAULT_LIMIT);
        long endTime1 = System.currentTimeMillis(); 
        long executionTime1 = endTime1 - startTime1; 

        if (goalState == null) {
            printer.printSolution("DFS", null); 
        }
        System.out.println("DFS Execution Time: " + executionTime1 + " milliseconds");
    }

    private void recursiveDFS(State state, int limit) {
        if (state.isGoal()) {
            goalState = state;
            printer.printSolution("DFS", state);
            return;
        }

        if (limit == 0) {
            return; 
        }

        explored.add(state); 

        List<State> successors = state.buildSuccessorStates(); 
        for (State child : successors) {
            if (!explored.contains(child)) { 
                recursiveDFS(child, limit - 1); 
                if (goalState != null) { 
                    return; 
                }
            }
        }
        
    }
}
