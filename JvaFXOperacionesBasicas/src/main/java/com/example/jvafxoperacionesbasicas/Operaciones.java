package com.example.jvafxoperacionesbasicas;

public class Operaciones {
    public static double suma(String x, String y){
        double num1 = Double.parseDouble(x);
        double num2 = Double.parseDouble(y);
        return num1+num2;
    }

    public static double resta(String x, String y){
        double num1 = Double.parseDouble(x);
        double num2 = Double.parseDouble(y);
        return num1-num2;
    }

    public static double division(String x, String y){
        double num1 = Double.parseDouble(x);
        double num2 = Double.parseDouble(y);
        return num1/num2;
    }

    public static double multiplicacion(String x, String y){
        double num1 = Double.parseDouble(x);
        double num2 = Double.parseDouble(y);
        return num1*num2;
    }
}
