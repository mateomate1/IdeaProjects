module app.javahelp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javahelp;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens app.javahelp to javafx.fxml;
    exports app.javahelp;
}