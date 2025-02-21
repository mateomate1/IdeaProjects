module org.example.informereservas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;
    requires net.sf.jasperreports.core;
    requires jrviewer.fx;
    requires java.sql;


    opens org.example.informereservas to javafx.fxml;
    exports org.example.informereservas;
}