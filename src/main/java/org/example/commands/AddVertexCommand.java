package org.example.commands;

import org.example.graph.Graph;
import org.example.graph.Vertex;

public class AddVertexCommand implements Command {
    private final Graph graph;
    private final String label;

    public AddVertexCommand(Graph graph, String label) {
        this.graph = graph;
        this.label = label;
    }

    @Override
    public void execute() {
        Vertex vertex = new Vertex(label);
        graph.addVertex(vertex);
        System.out.println("Vertex added: " + vertex);
    }
}
