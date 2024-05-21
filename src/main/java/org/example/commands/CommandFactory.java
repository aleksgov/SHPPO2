package org.example.commands;

import org.example.graph.Graph;

public class CommandFactory {
    private final Graph graph;

    public CommandFactory(Graph graph) {
        this.graph = graph;
    }

    public Command createCommand(String command) {
        String[] parts = command.split(" ");
        if (parts.length == 0) {
            return null;
        }

        switch (parts[0]) {
            case "add":
                if (parts.length > 1) {
                    switch (parts[1]) {
                        case "vertex":
                            if (parts.length == 3) {
                                return new AddVertexCommand(graph, parts[2]);
                            } else {
                                System.out.println("Usage: add vertex <label>");
                            }
                            break;
                        case "edge":
                            if (parts.length == 4) {
                                return new AddEdgeCommand(graph, parts[2], parts[3]);
                            } else {
                                System.out.println("Usage: add edge <from> <to>");
                            }
                            break;
                        default:
                            System.out.println("Unknown add command: " + parts[1]);
                    }
                } else {
                    System.out.println("Usage: add <vertex|edge>");
                }
                break;
            case "print":
                if (parts.length > 1) {
                    switch (parts[1]) {
                        case "adj":
                            return new PrintAdjacencyCommand(graph);
                        case "inc":
                            return new PrintIncidenceCommand(graph);
                        default:
                            System.out.println("Unknown print command: " + parts[1]);
                    }
                } else {
                    System.out.println("Usage: print <adjacency|incidence>");
                }
                break;
            default:
                System.out.println("Unknown command: " + parts[0]);
        }

        return null;
    }
}
