package app.hotel_2;

public class Habitacion {
    Integer nHabitacion;
    Double precio;
    String reserva;

    public Habitacion(Integer nHabitacion, Double precio, Reserva reserva) {
        this.nHabitacion = nHabitacion;
        this.precio = precio;
        this.reserva = "Hola";
    }

    public Integer getnHabitacion() {
        return nHabitacion;
    }

    public void setnHabitacion(Integer nHabitacion) {
        this.nHabitacion = nHabitacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getReserva() {
        return reserva;
    }

    public void setReserva(String reserva) {
        this.reserva = reserva;
    }
/*
    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
     */
}
