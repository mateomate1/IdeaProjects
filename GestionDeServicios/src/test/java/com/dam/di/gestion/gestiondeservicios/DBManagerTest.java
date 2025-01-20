package com.dam.di.gestion.gestiondeservicios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBManagerTest {

    @Test
    void addUser() {//Falla la conexion por lo que no puedo conectarlo
        DBManager db = new DBManager();
        Usuario u = new Usuario("contrasena");
        u.setUsername("usuario");
        assertTrue(db.addUser(u));
    }
}