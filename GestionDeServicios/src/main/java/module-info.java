module com.dam.di.gestion.gestiondeservicios {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.slf4j;

    opens com.dam.di.gestion.gestiondeservicios to javafx.fxml;
    exports com.dam.di.gestion.gestiondeservicios;
}