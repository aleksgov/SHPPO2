package org.example.commands;

import org.example.graph.Graph;

public class PrintIncidenceCommand implements Command {
    private final Graph graph;

    public PrintIncidenceCommand(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void execute() {
        int[][] incidenceMatrix = graph.getIncidenceMatrix();
        printMatrix(incidenceMatrix);
    }

    private void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
