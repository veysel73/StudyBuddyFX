package com.example.studybuddy;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ToDoItem {
    private final StringProperty task;
    private final BooleanProperty completed;
    private final ObjectProperty<LocalDate> date;

    public ToDoItem(String task) {
        this.task = new SimpleStringProperty(task);
        this.completed = new SimpleBooleanProperty(false);
        this.date = new SimpleObjectProperty<>();
    }

    public String getTask() {
        return task.get();
    }

    public void setTask(String value) {
        task.set(value);
    }

    public StringProperty taskProperty() {
        return task;
    }

    public boolean isCompleted() {
        return completed.get();
    }

    public void setCompleted(boolean value) {
        completed.set(value);
    }

    public BooleanProperty completedProperty() {
        return completed;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate value) {
        date.set(value);
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    @Override
    public String toString() {
        return getTask();
    }
}
