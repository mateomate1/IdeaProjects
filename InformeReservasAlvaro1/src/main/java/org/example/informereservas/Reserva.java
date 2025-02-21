package org.example.informereservas;

/**
 * @author Alvaro Morales Moreno
 * @version 1
 * @since 17/02/2025
 * Clase, representa una reserva indicando: numero de personas, Nombre del huesped principal y duracion de la reserva.
 */
public class Reserva {
    private int numPersonas;
    private String huespedPrincipal;
    private int duracion;

    /**
     * Constructor para crear una Reserva con numero de Personas, huesped Principal y duracion de reserva.
     *
     * @param numPersonas
     * @param huespedPrincipal
     * @param duracion
     */
    public Reserva(int numPersonas, String huespedPrincipal, int duracion) {
        this.numPersonas = numPersonas;
        this.huespedPrincipal = huespedPrincipal;
        this.duracion = duracion;
    }

    /**
     * Obtiene el numero de personas que hay en la reserva.
     * @return numero de personas.
     */
    public int getNumPersonas() {
        return numPersonas;
    }

    /**
     * Establece un nuevo numero de personas.
     * @param numPersonas Nuevo numero de personas.
     */
    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    /**
     * Obtiene el nombre del huesped principal
     * @return huesped principal
     */
    public String getHuespedPrincipal() {
        return huespedPrincipal;
    }

    /**
     * Establece un nuevo nombre del huesped principal
     * @param huespedPrincipal Nuevo nombre del huesped principal
     */
    public void setHuespedPrincipal(String huespedPrincipal) {
        this.huespedPrincipal = huespedPrincipal;
    }

    /**
     * Obtiene la duracion de la reserva.
     * @return duracion de la reserva.
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Establece un nuevo tiempo de duracion de reserva.
     * @param duracion Nuevo tiempo de duracion de reserva.
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
