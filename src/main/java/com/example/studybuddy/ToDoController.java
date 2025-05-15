package com.example.studybuddy;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.paint.Color;

public class ToDoController implements Initializable {

    @FXML
    private TextField taskInput;

    @FXML
    private ListView<ToDoItem> taskListView;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private DatePicker taskDatePicker;

    private ObservableList<ToDoItem> tasks = FXCollections.observableArrayList();

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ListView'ı yapılandır
        taskListView.setItems(tasks);

        // Özel CheckBox hücre fabrikası
        taskListView.setCellFactory(lv -> new CheckBoxListCell<>(item -> {
            item.completedProperty().addListener((obs, wasCompleted, isNowCompleted) -> {
                // Hücreyi güncelle (özel stil için)
                lv.refresh();
            });
            return item.completedProperty();
        }) {
            @Override
            public void updateItem(ToDoItem item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                    setStyle("");
                } else {
                    // Görevin tamamlanma durumuna göre stil uygula
                    if (item.isCompleted()) {
                        setStyle("-fx-background-color: rgba(46, 204, 113, 0.2); -fx-text-fill: #707070; -fx-strikethrough: true;"); // Yarı saydam yeşil arkaplan, soluk yazı, üstü çizili
                    } else {
                        setStyle("-fx-background-color: transparent;");
                    }

                    // Tarih bilgisini göster
                    if (item.getDate() != null) {
                        setText(item.getTask() + " - " + item.getDate().format(dateFormatter));
                    } else {
                        setText(item.getTask());
                    }
                }
            }
        });

        // DatePicker'ı yapılandır
        taskDatePicker.setValue(LocalDate.now());
        taskDatePicker.setPromptText("Tarih seçin");

        // Liste seçimi değiştiğinde deleteButton'u etkinleştir/devre dışı bırak
        taskListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            deleteButton.setDisable(newValue == null);
        });

        // Başlangıçta deleteButton'u devre dışı bırak
        deleteButton.setDisable(true);
    }

    @FXML
    private void handleAddTask(ActionEvent event) {
        String text = taskInput.getText().trim();
        if (!text.isEmpty()) {
            // Yeni görev oluştur
            ToDoItem newItem = new ToDoItem(text);
            // Tarih bilgisini ekle
            newItem.setDate(taskDatePicker.getValue());
            // Görevler listesine ekle
            tasks.add(newItem);
            // Girdi alanını temizle ve odaklan
            taskInput.clear();
            taskInput.requestFocus();
        }
    }

    @FXML
    private void handleDeleteTask(ActionEvent event) {
        ToDoItem selectedItem = taskListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            tasks.remove(selectedItem);
        }
    }

    // Enter tuşuna basıldığında da görev ekleme
    @FXML
    private void handleKeyPress(ActionEvent event) {
        handleAddTask(event);
    }
}
