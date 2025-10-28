package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private static final String FILE_NAME = "tasks.txt";
    private final List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    public void addTask(String description, int priority) {
        Task task = new Task(description, priority);
        tasks.add(task);
        System.out.println("Task added: ID " + task.getId());
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        tasks.sort(Comparator.comparing(Task::getPriority).reversed().thenComparing(Task::getId));
        System.out.println("\n--- Task List (Sorted by Priority) ---");
        for (Task task : tasks) {
            System.out.println(task);
        }
        System.out.println("-------------------------------------\n");
    }

    public void completeTask(int id) {
        Task taskToComplete = findTaskById(id);
        if (taskToComplete != null) {
            taskToComplete.setCompleted(true);
            System.out.println("Task ID " + id + " marked as completed.");
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    public void deleteTask(int id) {
        boolean removed = tasks.removeIf(task -> task.getId() == id);
        if (removed) {
            System.out.println("Task ID " + id + " deleted.");
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    private Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public void saveTasksToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.println(task.getId() + "|" + task.getDescription() + "|" + task.getPriority() + "|" + task.isCompleted());
            }
            System.out.println("Tasks saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private void loadTasksFromFile() {
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    // Note: Task IDs are reset on load to avoid issues, but we load data
                    Task task = new Task(parts[1], Integer.parseInt(parts[2]));
                    task.setCompleted(Boolean.parseBoolean(parts[3]));
                    tasks.add(task);
                }
            }
            System.out.println("Tasks loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found. Starting fresh.");
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}
