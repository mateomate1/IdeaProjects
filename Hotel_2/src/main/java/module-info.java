module app.hotel_2 {
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires net.sf.jasperreports.core;
    requires java.sql;
    requires jrviewer.fx;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    requires org.apache.pdfbox;
    requires javafx.swing;
    requires javafx.web;
=======
    requires java.desktop;
    requires javahelp;
    requires javafx.swing;
>>>>>>> Stashed changes
=======
    requires java.desktop;
    requires javahelp;
    requires javafx.swing;
>>>>>>> Stashed changes

    opens app.hotel_2 to javafx.fxml;
    exports app.hotel_2;
}