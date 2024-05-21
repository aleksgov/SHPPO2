package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.example.commands.Command;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Aspect
@Component
public class LoggingAspect {

    private static final String LOG_FILE_PATH = "log.txt";
    private int sessionCounter;

    {
        // Instance initializer block to set the session counter
        this.sessionCounter = readLastSessionNumber() + 1;
        writeLog("Session " + sessionCounter + " started");
    }

    @AfterReturning("execution(* org.example.commands.Command.execute(..))")
    public void logCommandExecution(JoinPoint joinPoint) {
        String commandName = joinPoint.getTarget().getClass().getSimpleName();
        String logMessage = commandName + " executed";
        System.out.println(logMessage);
        writeLog(getSessionPrefix() + logMessage);
    }

    @AfterReturning(pointcut = "execution(* org.example.graph.Graph.addVertex(..)) && args(vertex)", returning = "added")
    public void logVertexAdded(JoinPoint joinPoint, Object vertex, boolean added) {
        String logMessage;
        if (added) {
            logMessage = "Vertex added: " + vertex;
        } else {
            logMessage = "Failed to add vertex: " + vertex;
        }
        System.out.println(logMessage);
        writeLog(getSessionPrefix() + logMessage);
    }

    @AfterReturning(pointcut = "execution(* org.example.graph.Graph.addEdge(..)) && args(edge)", returning = "added")
    public void logEdgeAdded(JoinPoint joinPoint, Object edge, boolean added) {
        String logMessage;
        if (added) {
            logMessage = "Edge added: " + edge;
        } else {
            logMessage = "Failed to add edge: " + edge;
        }
        System.out.println(logMessage);
        writeLog(getSessionPrefix() + logMessage);
    }

    private void writeLog(String log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write(log);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getSessionPrefix() {
        return "Session " + sessionCounter + ": ";
    }

    private int readLastSessionNumber() {
        int lastSessionNumber = 0;
        File logFile = new File(LOG_FILE_PATH);

        if (logFile.exists()) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(LOG_FILE_PATH));
                if (!lines.isEmpty()) {
                    String lastLine = lines.get(lines.size() - 1);
                    int startIndex = lastLine.indexOf("Session ") + 8;
                    int endIndex = lastLine.indexOf(":", startIndex);
                    if (startIndex != -1 && endIndex != -1) {
                        String sessionNumberStr = lastLine.substring(startIndex, endIndex).trim();
                        lastSessionNumber = Integer.parseInt(sessionNumberStr);
                    }
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return lastSessionNumber;
    }

    public void clearLog() {
        FileSystemResource resource = new FileSystemResource(LOG_FILE_PATH);
        if (resource.exists()) {
            resource.getFile().delete();
        }
    }
}
