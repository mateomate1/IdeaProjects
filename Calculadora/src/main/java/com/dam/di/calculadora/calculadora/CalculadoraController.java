package com.dam.di.calculadora.calculadora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculadoraController {

    @FXML private TextField textFieldOutput;

    private boolean punto = false;
    private String input = "";
    private boolean lastIsNAN = false;

    private void addText(String texto) {
        input += texto;
        textFieldOutput.setText(input);
    }

    @FXML
    protected void Numero(ActionEvent event) {
        Button btn = (Button) event.getSource();
        addText(btn.getText());
        lastIsNAN = false;
    }

    @FXML
    protected void Operador(ActionEvent event) {
        Button btn = (Button) event.getSource();
        if (lastIsNAN) {
            Borrar();
        }
        addText(btn.getText());
        lastIsNAN = true;
    }

    @FXML
    protected void Punto() {
        if (!punto) {
            addText(".");
            punto = true;
        }
    }

    @FXML
    protected void Limpiar() {
        input = "";
        textFieldOutput.setText(input);
    }

    @FXML
    protected void Borrar() {
        if (!input.isEmpty()) {
            input = input.substring(0, input.length() - 1);
            textFieldOutput.setText(input);
        }
    }

    @FXML
    protected void calcular() {
        Calculadora calculadora = new Calculadora();
        double resultado = calculadora.calcular(input);

        if (Double.isNaN(resultado)) {
            textFieldOutput.setText("Error");
        } else {
            textFieldOutput.setText(String.valueOf(resultado));
        }
        input = "";
    }
}
