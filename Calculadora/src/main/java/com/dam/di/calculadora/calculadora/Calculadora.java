package com.dam.di.calculadora.calculadora;

import java.util.Stack;

public class Calculadora {

    public double calcular(String input) {
        try {
            input = input.replace('x', '*').replace('÷', '/');
            Stack<Double> numeros = new Stack<>();
            int i = 0;
            double num = 0;
            char operador = '+';

            while (i < input.length()) {
                char c = input.charAt(i);

                if (Character.isDigit(c) || c == '.') {
                    StringBuilder sb = new StringBuilder();
                    sb.append(c);
                    i++;
                    while (i < input.length() && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
                        sb.append(input.charAt(i));
                        i++;
                    }
                    num = Double.parseDouble(sb.toString());
                }

                if (i == input.length() || "+-*/".indexOf(c) >= 0) {
                    if (operador == '+') {
                        numeros.push(num);
                    } else if (operador == '-') {
                        numeros.push(-num);
                    } else if (operador == '*') {
                        numeros.push(numeros.pop() * num);
                    } else if (operador == '/') {
                        if (num == 0) {
                            return Double.NaN;
                        }
                        numeros.push(numeros.pop() / num);
                    }
                    num = 0;
                    operador = c;
                }
                i++;
            }

            double resultado = 0;
            while (!numeros.isEmpty()) {
                resultado += numeros.pop();
            }

            return resultado;

        } catch (Exception e) {
            return Double.NaN;
        }
    }
}
