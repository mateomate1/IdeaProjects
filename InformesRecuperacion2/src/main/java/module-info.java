module com.dam.di.informesrecuperacion2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires net.sf.jasperreports.core;
    requires jrviewer.fx;
    requires java.sql;

    opens com.dam.di.informesrecuperacion2 to javafx.fxml;
    exports com.dam.di.informesrecuperacion2;
}