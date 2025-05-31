module com.example.studybuddy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    opens com.example.studybuddy to javafx.fxml; // Controller'lar için kritik!
    exports com.example.studybuddy;
}