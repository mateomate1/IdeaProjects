module com.dam.di.calculadora.calculadora {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.dam.di.calculadora.calculadora to javafx.fxml;
    exports com.dam.di.calculadora.calculadora;
}