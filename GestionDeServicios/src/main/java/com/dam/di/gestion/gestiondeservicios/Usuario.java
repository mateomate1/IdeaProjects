package com.dam.di.gestion.gestiondeservicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Usuario implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(Usuario.class);

    private String username, nombre, apellidos;
    private LocalDate fechaNacimiento;
    private String password;
    private final String rutaArchivos = "users.dat";
    private transient GestorBin<Usuario> gestorBin = new GestorBin<>(rutaArchivos);

    public Usuario(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }
        this.password = password;
    }

    public void newPassword(String vieja, String nueva) {
        if (vieja.equals(password)) {
            if (nueva == null || nueva.trim().isEmpty()) {
                throw new IllegalArgumentException("La contraseña no puede estar vacía.");
            }
            password = nueva;
            log.warn("Contraseña cambiada con éxito.");
        } else {
            log.warn("Contraseña incorrecta, no se cambiará la contraseña.");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean login(String password) {
        if(this.password.equals(password)){
            return true;
        } else{
            return false;
        }
    }

    public boolean alta() {
        if (username == null || username.isBlank()) {
            log.error("El nombre de usuario no puede estar vacío.");
            return false;
        }

        List<Usuario> usuarios = gestorBin.leer();
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(this.username)) {
                log.warn("El nombre de usuario ya existe: {}", username);
                return false;
            }
        }

        gestorBin.add(this);
        log.info("Usuario añadido correctamente: {}", username);
        return true;
    }

    public List<Usuario> mostrar() {
        return gestorBin.leer();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(username, usuario.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                '}';
    }
}
