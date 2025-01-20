package com.dam.di.gestion.gestiondeservicios;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GestorBinTest {
    GestorBin<String> gestor = new GestorBin<>("test.dat");

    @Test
    @Order(1)
    void vaciar() {
        gestor.vaciar();
        List<String> resultado = gestor.leer();
        List<String> expected = new ArrayList<>();//Deberia estar vacio al haberlo vaciado al principio
        assertLinesMatch(expected, resultado);
    }

    @Test
    @Order(2)
    void add() {
        gestor.add("Hola");
        List<String> resultado = gestor.leer();
        List<String> expected = new ArrayList<>();
        expected.add("Hola");
        assertLinesMatch(expected,resultado);
    }

    @Test
    @Order(3)
    void add2() {
        gestor.add("Adios");
        List<String> resultado = gestor.leer();
        List<String> expected = new ArrayList<>();
        expected.add("Hola");
        expected.add("Adios");
        assertLinesMatch(expected,resultado);
    }

    @Test
    @Order(4)
    void add3() {
        gestor.add("Fin");
        List<String> resultado = gestor.leer();
        List<String> expected = new ArrayList<>();
        expected.add("Hola");
        expected.add("Adios");
        expected.add("Fin");
        assertLinesMatch(expected,resultado);
    }

    @Test
    @Order(5)
    void eliminarPorPosicion() {
        boolean resultadoBoolean = gestor.eliminarPorPosicion(1);
        List<String> resultado = gestor.leer();
        List<String> expected = new ArrayList<>();
        expected.add("Hola");
        expected.add("Fin");
        assertLinesMatch(expected,resultado);
        assertTrue(resultadoBoolean);
    }

    @Test
    @Order(6)
    void leer() {
        List<String> resultado = gestor.leer();
        List<String> expected = new ArrayList<>();
        expected.add("Hola");
        expected.add("Fin");
        assertLinesMatch(expected,resultado);
    }

    @Test
    @Order(7)
    void eliminarPorPosicionOutOfRange(){
        boolean resultadoBoolean = gestor.eliminarPorPosicion(6);
        assertFalse(resultadoBoolean);
    }

    @Test
    @Order(8)
    void leer2() {
        List<String> resultado = gestor.leer();
        List<String> expected = new ArrayList<>();
        expected.add("Hola");
        expected.add("Fin");
        assertLinesMatch(expected,resultado);
    }

    @Test
    @Order(9)
    void escribir() {
        List<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("2");
        expected.add("3");
        expected.add("4");
        gestor.escribir(expected);
        List<String> resultado = gestor.leer();
        assertLinesMatch(expected,resultado);
    }

    @Test
    @Order(10)
    void leer3() {
        List<String> resultado = gestor.leer();
        List<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("2");
        expected.add("3");
        expected.add("4");
        expected.add("4");//Fallara porque la lista no incluye este numero
        assertLinesMatch(expected,resultado);
    }
}