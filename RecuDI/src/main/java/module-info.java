module com.dam.di.gestion.recudi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;

    opens com.dam.di.gestion.recudi to javafx.fxml;
    exports com.dam.di.gestion.recudi;
}