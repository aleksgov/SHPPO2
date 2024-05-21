package org.example.commands;

import org.example.graph.Edge;
import org.example.graph.Graph;
import org.example.graph.Vertex;

public class AddEdgeCommand implements Command {
    private final Graph graph;
    private final String fromLabel;
    private final String toLabel;

    public AddEdgeCommand(Graph graph, String fromLabel, String toLabel) {
        this.graph = graph;
        this.fromLabel = fromLabel;
        this.toLabel = toLabel;
    }

    @Override
    public void execute() {
        Vertex from = findVertex(graph, fromLabel);
        Vertex to = findVertex(graph, toLabel);
        if (from != null && to != null) {
            Edge edge = new Edge(from, to);
            graph.addEdge(edge);
            System.out.println("Edge added: " + edge);
        } else {
            System.out.println("Both vertices must exist to add an edge");
        }
    }

    private Vertex findVertex(Graph graph, String label) {
        return graph.getVertices().stream().filter(v -> v.getLabel().equals(label)).findFirst().orElse(null);
    }
}
