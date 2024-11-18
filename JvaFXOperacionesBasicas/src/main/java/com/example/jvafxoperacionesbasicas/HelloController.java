package com.example.jvafxoperacionesbasicas;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML private TextField resultado;
    @FXML private TextField operando1;
    @FXML private TextField operando2;

    @FXML
    protected void ButtonOperar(){
        int salida = Operaciones.suma(operando1.getText(),operando2.getText());
        resultado.setText(salida+"");
    }
}