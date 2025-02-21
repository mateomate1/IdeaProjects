package org.example.informereservas;

/**
 * @author Alvaro Morales Moreno
 * @version 1
 * @since 17/02/2025
 * Representa una habitación de un hotel, incluyendo su número, precio y una reserva asociada.
 */
public class Habitacion {
    private int numero;
    private double precio;
    private Reserva reserva;

    /**
     * Constructor para crear una habitación con un número, precio y reserva.
     *
     * @param numero  Número de la habitación.
     * @param precio  Precio de la habitación.
     * @param reserva Reserva asociada a la habitación.
     */
    public Habitacion(int numero, double precio, Reserva reserva) {
        this.numero = numero;
        this.precio = precio;
        this.reserva = reserva;
    }

    /**
     * Obtiene el número de la habitación.
     *
     * @return Número de la habitación.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Establece el número de la habitación.
     *
     * @param numero Nuevo número de la habitación.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Obtiene el precio de la habitación.
     *
     * @return Precio de la habitación.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de la habitación.
     *
     * @param precio Nuevo precio de la habitación.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la reserva asociada a la habitación.
     *
     * @return La reserva de la habitación.
     */
    public Reserva getReserva() {
        return reserva;
    }

    /**
     * Asigna una reserva a la habitación.
     *
     * @param reserva Nueva reserva de la habitación.
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
