module app.mateo_ud4_registro_login_v2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.slf4j;

    opens app.mateo_ud4_registro_login_v2 to javafx.fxml;
    exports app.mateo_ud4_registro_login_v2;
}