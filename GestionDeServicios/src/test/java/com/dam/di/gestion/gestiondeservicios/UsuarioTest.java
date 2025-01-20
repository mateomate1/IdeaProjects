package com.dam.di.gestion.gestiondeservicios;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    Usuario u = new Usuario("usuario1","1234");
    static GestorBin<Usuario> gestor = new GestorBin<>("users.dat");

    @BeforeAll
    static void restart(){
        gestor.vaciar();
    }

    @Test
    void login() {
        assertTrue(u.login("1234"));
    }

    @Test
    void alta() {
        assertTrue(u.alta());
    }

    @Test
    void mostrar() {
        List<Usuario> expected = gestor.leer();
        List<Usuario> resultado = u.mostrar();
        assertIterableEquals(expected,resultado);
    }

    @Test
    void testEquals() {
        Usuario b = new Usuario("Hola","1234");
        assertNotEquals(u, b);
    }
}