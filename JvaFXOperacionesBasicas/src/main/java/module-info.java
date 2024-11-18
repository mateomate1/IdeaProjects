module com.example.jvafxoperacionesbasicas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.jvafxoperacionesbasicas to javafx.fxml;
    exports com.example.jvafxoperacionesbasicas;
}