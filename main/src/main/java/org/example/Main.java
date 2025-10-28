package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n--- Task Manager Menu ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");
            System.out.println("0. Exit (Save Tasks)");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addTask(scanner, manager);
                        break;
                    case 2:
                        manager.viewTasks();
                        break;
                    case 3:
                        completeTask(scanner, manager);
                        break;
                    case 4:
                        deleteTask(scanner, manager);
                        break;
                    case 0:
                        manager.saveTasksToFile();
                        System.out.println("Application exited.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
                choice = -1;
            }
        }
        scanner.close();
    }

    private static void addTask(Scanner scanner, TaskManager manager) {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        System.out.print("Enter priority (1=Low, 5=High): ");
        int priority = scanner.nextInt();
        scanner.nextLine();

        if (priority < 1 || priority > 5) {
            System.out.println("Priority must be between 1 and 5. Using default (3).");
            priority = 3;
        }

        manager.addTask(description, priority);
    }

    private static void completeTask(Scanner scanner, TaskManager manager) {
        System.out.print("Enter Task ID to mark as complete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        manager.completeTask(id);
    }

    private static void deleteTask(Scanner scanner, TaskManager manager) {
        System.out.print("Enter Task ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        manager.deleteTask(id);
    }
}