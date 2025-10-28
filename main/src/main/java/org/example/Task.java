package org.example;

public class Task {
    private static int nextId = 1;
    private final int id;
    private String description;
    private int priority;
    private boolean isCompleted;

    public Task(String description, int priority) {
        this.id = nextId++;
        this.description = description;
        this.priority = priority;
        this.isCompleted = false;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        String status = isCompleted ? "[V]" : "[ ]";
        return String.format("%s ID: %d | Priority: %d | Description: %s", status, id, priority, description);
    }
}
