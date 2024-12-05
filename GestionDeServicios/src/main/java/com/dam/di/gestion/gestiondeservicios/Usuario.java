package com.dam.di.gestion.gestiondeservicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Usuario {
    private static final Logger log = LoggerFactory.getLogger(Usuario.class);

    private String username, nombre, apellidos;
    private final String ID;
    private String password;
    private final String rutaArchivos = "users.dat";

    public Usuario(String id, String password) {
        ID = id;
        if(password.trim().isEmpty() || password == null){
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }
        this.password=password;
    }

    public void newPassword(String vieja, String nueva){
        if (vieja.equals(password)) {
            if(nueva.trim().isEmpty() || nueva == null){
                throw new IllegalArgumentException("La contraseña no puede estar vacía.");
            }
            password = nueva;
            log.warn("Contrasena cambiada con exito.");
        }
        else
            log.warn("Contrasena incorrecta, ne se cambiara la contrasena.");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getID() {
        return ID;
    }

    public boolean login(){
        boolean succed = false;
        return succed;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getID());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(getID(), usuario.getID());
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
