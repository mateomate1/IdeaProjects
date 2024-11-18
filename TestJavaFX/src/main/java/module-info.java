module com.example.testjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.testjavafx to javafx.fxml;
    exports com.example.testjavafx;
}