module com.example.studybuddy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.studybuddy to javafx.fxml;
    exports com.example.studybuddy;
}