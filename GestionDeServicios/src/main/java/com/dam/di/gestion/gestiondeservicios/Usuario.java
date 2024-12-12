package com.dam.di.gestion.gestiondeservicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Usuario implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(Usuario.class);

    private String username, nombre, apellidos;
    private LocalDate fechaNacimiento;
    private final int ID;
    private String password;
    private final String rutaArchivos = "users.dat";
    GestorBin<Usuario> gestorBin = new GestorBin<>(rutaArchivos);

    public Usuario(String password) {
        ID = generarIDUnico();
        if(password.trim().isEmpty() || password == null){
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }
        this.password=password;
    }

    private int generarIDUnico() {
        List<Usuario> usuarios = gestorBin.leer();
        int nuevoID = 1; // Empezamos desde 1.

        // Buscar el ID más alto ya existente
        for (Usuario usuario : usuarios) {
            if (usuario.getID() >= nuevoID) {
                nuevoID = usuario.getID() + 1;
            }
        }

        return nuevoID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getID() {
        return ID;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean login(){
        boolean succed = false;
        return succed;
    }

    public boolean alta(){
        if(username!=null&& !(username.isBlank())){
            gestorBin.add(this);
            return true;
        }else {
            return false;
        }
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
