module app.hotel_2 {
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires net.sf.jasperreports.core;
    requires java.sql;
    requires jrviewer.fx;
    requires javahelp;
    requires javafx.swing;

    opens app.hotel_2 to javafx.fxml;
    exports app.hotel_2;
}