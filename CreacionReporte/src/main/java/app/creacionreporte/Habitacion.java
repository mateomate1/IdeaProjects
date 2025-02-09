package app.creacionreporte;

class Habitacion {
    private int numero;
    private double precio;
    private Reserva reserva;

    public Habitacion(int numero, double precio, Reserva reserve) {
        this.numero = numero;
        this.precio = precio;
        this.reserva = reserva;
    }

    public int getNumero() {
        return numero;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean estaReservada() {
        return reserva != null;
    }

    public void reservar(Reserva reserva) {
        if (this.reserva == null) {
            this.reserva = reserva;
        } else {
            throw new IllegalStateException("La habitacion ya esta reservada.");
        }
    }

    public void cancelarReserva() {
        this.reserva = null;
    }
}