module com.dam.di.jartoexe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.dam.di.jartoexe to javafx.fxml;
    exports com.dam.di.jartoexe;
}