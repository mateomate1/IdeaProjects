module app.formulariogonzalo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens app.formulariogonzalo to javafx.fxml;
    exports app.formulariogonzalo;
}