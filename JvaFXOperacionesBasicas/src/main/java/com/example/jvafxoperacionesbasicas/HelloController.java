package com.example.jvafxoperacionesbasicas;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML private TextField resultado;
    @FXML private TextField operando1;
    @FXML private TextField operando2;
    @FXML private ToggleGroup operaciones;

    @FXML
    protected void ButtonOperar(){
        String salida;
        switch (getSelected()){
            case 1 -> salida = "" + Operaciones.suma(operando1.getText(),operando2.getText());
            case 2 -> salida = "" + Operaciones.resta(operando1.getText(),operando2.getText());
            case 3 -> salida = "" + Operaciones.division(operando1.getText(),operando2.getText());
            case 4 -> salida = "" + Operaciones.multiplicacion(operando1.getText(),operando2.getText());
            default -> salida = "No has elegido operacion";
        }
        resultado.setText(salida);
    }

    protected int getSelected(){
        RadioButton selectedButton = (RadioButton) operaciones.getSelectedToggle();
        int salida;
        switch (selectedButton.getId()){
            case "RadioButtonSuma" -> salida = 1;
            case "RadioButtonResta" -> salida =2;
            case "RadioButtonDivision" -> salida =3;
            case "RadioButtonMultiplicacion" -> salida =4;
            default -> salida = 0;
        }
        return salida;
    }
}