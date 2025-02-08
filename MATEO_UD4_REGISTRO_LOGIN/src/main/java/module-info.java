module app.mateo_ud4_registro_login {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.slf4j;
    requires java.sql;

    opens app.mateo_ud4_registro_login to javafx.fxml;
    exports app.mateo_ud4_registro_login;
}