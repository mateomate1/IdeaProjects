package app.hotel_2;

/**
 * Representa una habitacion en el hotel.
 * @author Mateo
 */
public class Habitacion {
    private Integer nHabitacion;
    private Double precio;
    private String reserva;

    /**
     * Constructor de la clase Habitacion.
     *
     * @param nHabitacion Numero de la habitacion.
     * @param precio Precio de la habitacion.
     * @param reserva Reserva asociada a la habitacion.
     */
    public Habitacion(Integer nHabitacion, Double precio, Reserva reserva) {
        this.nHabitacion = nHabitacion;
        this.precio = precio;
        this.reserva = "Hola";
    }

    /**
     * Obtiene el numero de la habitacion.
     *
     * @return Numero de la habitacion.
     */
    public Integer getnHabitacion() {
        return nHabitacion;
    }

    /**
     * Establece el numero de la habitacion.
     *
     * @param nHabitacion Numero de la habitacion.
     */
    public void setnHabitacion(Integer nHabitacion) {
        this.nHabitacion = nHabitacion;
    }

    /**
     * Obtiene el precio de la habitacion.
     *
     * @return Precio de la habitacion.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de la habitacion.
     *
     * @param precio Precio de la habitacion.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la reserva de la habitacion.
     *
     * @return Reserva de la habitacion.
     */
    public String getReserva() {
        return reserva;
    }

    /**
     * Establece la reserva de la habitacion.
     *
     * @param reserva Reserva de la habitacion.
     */
    public void setReserva(String reserva) {
        this.reserva = reserva;
    }
}
