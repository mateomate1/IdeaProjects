module com.dam.di.examenjaspersoftjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires net.sf.jasperreports.core;

    requires java.sql;

    opens app to javafx.fxml;
    exports app;
}