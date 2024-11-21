package com.example.jvafxoperacionesbasicas;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML private TextField resultado;
    @FXML private TextField operando1;
    @FXML private TextField operando2;
    @FXML private ToggleGroup operaciones;

    @FXML
    protected void ButtonOperar() {
        String salida;
        try {
            switch (getSelected()){
                case 1 -> salida = "" + Operaciones.suma(operando1.getText(),operando2.getText());
                case 2 -> salida = "" + Operaciones.resta(operando1.getText(),operando2.getText());
                case 3 -> salida = "" + Operaciones.division(operando1.getText(),operando2.getText());
                case 4 -> salida = "" + Operaciones.multiplicacion(operando1.getText(),operando2.getText());
                case 0 -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("No has seleccionado una operacion");
                    alert.showAndWait();
                    salida = null;
                }
                default -> salida = null;
            }
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No has introducido un numero");
            alert.showAndWait();
            salida = null;
        } catch (ArithmeticException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No se puede dividir entre 0");
            alert.showAndWait();
            salida = "Infiniti";
        }

        resultado.setText(salida);
    }

    protected int getSelected(){
        RadioButton selectedButton = (RadioButton) operaciones.getSelectedToggle();
        int salida;
        if (selectedButton != null) {
            switch (selectedButton.getId()) {
                case "RadioButtonSuma" -> salida = 1;
                case "RadioButtonResta" -> salida = 2;
                case "RadioButtonDivision" -> salida = 3;
                case "RadioButtonMultiplicacion" -> salida = 4;
                default -> salida = 0;
            }
        }
        else
            salida = 0;
        return salida;
    }
}