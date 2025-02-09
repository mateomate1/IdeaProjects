package app.creacionreporte;

class Reserva {
    private int numeroPersonas;
    private String nombreHuesped;
    private int duracionNoches;

    public Reserva(int numeroPersonas, String nombreHuesped, int duracionNoches) {
        this.numeroPersonas = numeroPersonas;
        this.nombreHuesped = nombreHuesped;
        this.duracionNoches = duracionNoches;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public String getNombreHuesped() {
        return nombreHuesped;
    }

    public int getDuracionNoches() {
        return duracionNoches;
    }
}

