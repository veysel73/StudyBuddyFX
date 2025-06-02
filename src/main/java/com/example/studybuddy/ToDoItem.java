package com.example.studybuddy;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;

public class ToDoItem {
    private final StringProperty task = new SimpleStringProperty();
    private final BooleanProperty completed = new SimpleBooleanProperty();
    private LocalDate date;

    public ToDoItem(String task) {
        this.task.set(task);
    }

    public String getTask() {
        return task.get();
    }

    public void setTask(String task) {
        this.task.set(task);
    }

    public StringProperty taskProperty() {
        return task;
    }

    public boolean isCompleted() {
        return completed.get();
    }

    public void setCompleted(boolean completed) {
        this.completed.set(completed);
    }

    public BooleanProperty completedProperty() {
        return completed;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}