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

    public boolean addVertex(Vertex vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            return true;
        }
        return false;
    }

    public boolean addEdge(Edge edge) {
        if (!edges.contains(edge)) {
            edges.add(edge);
            return true;
        }
        return false;
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
            matrix[toIndex][fromIndex] = 1; // Если граф неориентированный
        }

        return matrix;
    }

    public int[][] getIncidenceMatrix() {
        int vertexCount = vertices.size();
        int edgeCount = edges.size();
        int[][] matrix = new int[edgeCount][vertexCount];

        for (int i = 0; i < edgeCount; i++) {
            Edge edge = edges.get(i);
            int fromIndex = vertices.indexOf(edge.getFrom());
            int toIndex = vertices.indexOf(edge.getTo());
            matrix[i][fromIndex] = 1;
            matrix[i][toIndex] = 1;
        }

        return matrix;
    }
}
