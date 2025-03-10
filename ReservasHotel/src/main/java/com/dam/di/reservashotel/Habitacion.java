package com.dam.di.reservashotel;

/**
 * Clase Habitacion
 */
public class Habitacion {

    /**
     * Definicion de atributos locales
     */
    private int numHabitacion;
    private double precio;
    private Reserva reserva;

    /**
     * Constructor con parametros
     *
     * @param numHabitacion numero de habitacion
     * @param precio precio
     * @param reserva instancia de reserva
     */
    public Habitacion(int numHabitacion, double precio, Reserva reserva) {
        this.numHabitacion = numHabitacion;
        this.precio = precio;
        this.reserva = reserva;
    }

    /**
     * Metodo getNumero
     * Devuelve el valor de numHabitacion
     *
     * @return numHabitacion
     **/
    public int getNumero() {
        return numHabitacion;
    }


    /**
     * Metodo getPrecio
     * Devuelve el valor de precio
     *
     * @return precio
     **/
    public double getPrecio() {
        return precio;
    }

    /**
     * Metodo estaReservada
     * Comprueba si la reserva tiene valor o no
     *
     * @return boolean si esta reservada o no
     */
    public boolean estaReservada() {
        return reserva != null;
    }

    /**
     * Metodo reservar
     * Asigna una habitacion a una reserva
     *
     * @param reserva reserva
     */
    public void reservar(Reserva reserva) {
        if (this.reserva == null) {
            this.reserva = reserva;
        } else {
            throw new IllegalStateException("La habitacion ya esta reservada.");
        }
    }

    /**
     * Metodo cancelarReserva
     * Asigna el valor nulo a la reserva
     */
    public void cancelarReserva() {
        this.reserva = null;
    }
}
