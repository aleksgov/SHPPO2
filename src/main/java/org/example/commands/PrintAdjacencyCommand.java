package org.example.commands;

import org.example.graph.Graph;

import java.util.List;

public class PrintAdjacencyCommand implements Command {
    private final Graph graph;

    public PrintAdjacencyCommand(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void execute() {
        int[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        List<String> labels = graphLabels(graph);
        printMatrix(adjacencyMatrix, labels);
    }

    private void printMatrix(int[][] matrix, List<String> labels) {
        System.out.print("  ");
        for (String label : labels) {
            System.out.print(label + " ");
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(labels.get(i) + " ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private List<String> graphLabels(Graph graph) {
        return graph.getVertices().stream().map(v -> v.getLabel()).toList();
    }
}
