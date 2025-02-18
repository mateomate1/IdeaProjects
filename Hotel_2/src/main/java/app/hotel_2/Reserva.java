package app.hotel_2;

public class Reserva {
    Integer nPersonas;
    String huespedPrincipal;
    Integer nNoches;

    public Reserva(Integer nPersonas, String huespedPrincipal, Integer nNoches) {
        this.nPersonas = nPersonas;
        this.huespedPrincipal = huespedPrincipal;
        this.nNoches = nNoches;
    }

    public Integer getnPersonas() {
        return nPersonas;
    }

    public void setnPersonas(Integer nPersonas) {
        this.nPersonas = nPersonas;
    }

    public String getHuespedPrincipal() {
        return huespedPrincipal;
    }

    public void setHuespedPrincipal(String huespedPrincipal) {
        this.huespedPrincipal = huespedPrincipal;
    }

    public Integer getnNoches() {
        return nNoches;
    }

    public void setnNoches(Integer nNoches) {
        this.nNoches = nNoches;
    }
}
