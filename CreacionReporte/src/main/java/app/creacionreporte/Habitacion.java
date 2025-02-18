package app.creacionreporte;

class Habitacion {
    private int numero;
    private double precio;

    public Habitacion(int numero, double precio, Reserva reserve) {
        //this.reserva = reserva;
        this.numero = numero;
        this.precio = precio;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Habitacion " + numero + " Precio: " + precio;
    }
}