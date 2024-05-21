package org.example.commands;

import org.example.graph.Graph;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CommandFactory {
    private final Graph graph;
    private final Map<String, Function<String[], Command>> commandMap = new HashMap<>();

    public CommandFactory(Graph graph) {
        this.graph = graph;
        initializeCommands();
    }

    private void initializeCommands() {
        commandMap.put("add vertex", parts -> {
            if (parts.length == 3) {
                return new AddVertexCommand(graph, parts[2]);
            } else {
                System.out.println("Usage: add vertex <label>");
                return null;
            }
        });

        commandMap.put("add edge", parts -> {
            if (parts.length == 4) {
                return new AddEdgeCommand(graph, parts[2], parts[3]);
            } else {
                System.out.println("Usage: add edge <from> <to>");
                return null;
            }
        });

        commandMap.put("print adj", parts -> new PrintAdjacencyCommand(graph));
        commandMap.put("print inc", parts -> new PrintIncidenceCommand(graph));
        commandMap.put("get logs", parts -> new GetLogsCommand());
    }

    public Command createCommand(String command) {
        String[] parts = command.split(" ");
        if (parts.length == 0) {
            return null;
        }

        String key = String.join(" ", parts[0], parts[1]);
        Function<String[], Command> commandFunction = commandMap.get(key);

        if (commandFunction != null) {
            return commandFunction.apply(parts);
        } else {
            System.out.println("Unknown command: " + command);
            return null;
        }
    }
}
