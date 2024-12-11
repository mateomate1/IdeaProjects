module com.example.tablapersonas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.slf4j;

    opens com.example.tablapersonas to javafx.fxml;
    exports com.example.tablapersonas;
}