package org.example.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final List<Vertex> vertices;
    private final List<Edge> edges;

    public Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int[][] getAdjacencyMatrix() {
        int size = vertices.size();
        int[][] matrix = new int[size][size];

        for (Edge edge : edges) {
            int fromIndex = vertices.indexOf(edge.getFrom());
            int toIndex = vertices.indexOf(edge.getTo());
            matrix[fromIndex][toIndex] = 1;
            matrix[toIndex][fromIndex] = 1; // Для неориентированных графов
        }

        return matrix;
    }

    public int[][] getIncidenceMatrix() {
        int vertexCount = vertices.size();
        int edgeCount = edges.size();
        int[][] matrix = new int[vertexCount][edgeCount];

        for (int i = 0; i < edgeCount; i++) {
            Edge edge = edges.get(i);
            int fromIndex = vertices.indexOf(edge.getFrom());
            int toIndex = vertices.indexOf(edge.getTo());
            matrix[fromIndex][i] = 1;
            matrix[toIndex][i] = 1; // Для неориентированных графов
        }

        return matrix;
    }
}
