package org.example.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetLogsCommand implements Command {

    @Override
    public void execute() {
        int currentSession = readLastSessionNumber();
        Set<String> vertices = new HashSet<>();
        Set<String> edges = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("log.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Session " + currentSession)) {
                    if (line.contains("Vertex added:")) {
                        String vertex = extractNodeFromLog(line);
                        vertices.add(vertex);
                    } else if (line.contains("Edge added:")) {
                        String edge = extractEdgeFromLog(line);
                        edges.add(edge);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Vertex: " + String.join(", ", vertices));
        System.out.println("Edge: " + String.join(", ", edges));
    }

    private String extractNodeFromLog(String logLine) {
        // Regular expression to extract node label from log line
        Pattern pattern = Pattern.compile("Vertex added: (\\w+)");
        Matcher matcher = pattern.matcher(logLine);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private String extractEdgeFromLog(String logLine) {
        // Regular expression to extract edge from log line
        Pattern pattern = Pattern.compile("Edge added: (\\w+) -> (\\w+)");
        Matcher matcher = pattern.matcher(logLine);
        if (matcher.find()) {
            return matcher.group(1) + " -> " + matcher.group(2);
        }
        return null;
    }

    private int readLastSessionNumber() {
        int lastSessionNumber = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("log.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Session ")) {
                    int sessionNumber = Integer.parseInt(line.split(" ")[1].replaceAll(":", ""));
                    lastSessionNumber = Math.max(lastSessionNumber, sessionNumber);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return lastSessionNumber;
    }
}
