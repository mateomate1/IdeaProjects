module app.creacionreporte {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires net.sf.jasperreports.core;

    requires java.sql;
    requires jrviewer.fx;

    opens app.creacionreporte to javafx.fxml;
    exports app.creacionreporte;
}