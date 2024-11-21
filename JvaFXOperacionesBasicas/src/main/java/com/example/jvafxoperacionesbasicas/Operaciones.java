package com.example.jvafxoperacionesbasicas;

import javafx.scene.control.Alert;

public class Operaciones {
    public static double suma(String x, String y) throws NumberFormatException{
        double num1 = Double.parseDouble(x);
        double num2 = Double.parseDouble(y);
        return num1+num2;
    }

    public static double resta(String x, String y) throws NumberFormatException {
        double num1 = Double.parseDouble(x);
        double num2 = Double.parseDouble(y);
        return num1-num2;
    }

    public static double division(String x, String y) throws NumberFormatException, ArithmeticException{
        double num1 = Double.parseDouble(x);
        double num2 = Double.parseDouble(y);
        if (num2==0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No se puede dividir entre 0");
            alert.showAndWait();
        }
        return num1/num2;
    }

    public static double multiplicacion(String x, String y) throws NumberFormatException {
        double num1 = Double.parseDouble(x);
        double num2 = Double.parseDouble(y);
        return num1*num2;
    }
}
