module com.dam.di.informesrcuperacion {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.dam.di.informesrcuperacion to javafx.fxml;
    exports com.dam.di.informesrcuperacion;
}