package org.example;

import org.example.commands.Command;
import org.example.commands.CommandFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(org.example.config.AppConfig.class);
        CommandFactory commandFactory = context.getBean(CommandFactory.class);

        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to the GraphXDrower");
        System.out.println("Available commands: \n• add vertex <label>, \n• add edge <from> <to>, \n• print adj, \n• print inc, \n• get logs \n• exit");

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine();
            Command cmd = commandFactory.createCommand(command);
            if (cmd != null) {
                cmd.execute();
            } else if ("exit".equals(command)) {
                System.out.println("Exiting...");
                scanner.close();
                return;
            }
        }
    }
}