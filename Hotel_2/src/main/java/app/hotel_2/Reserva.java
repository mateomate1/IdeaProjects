package app.hotel_2;

/**
 * Representa una reserva en el hotel.
 * @author Mateo
 */
public class Reserva {
    private Integer nPersonas;
    private String huespedPrincipal;
    private Integer nNoches;

    /**
     * Constructor de la clase Reserva.
     *
     * @param nPersonas Numero de personas en la reserva.
     * @param huespedPrincipal Nombre del huesped principal.
     * @param nNoches Numero de noches de la estancia.
     */
    public Reserva(Integer nPersonas, String huespedPrincipal, Integer nNoches) {
        this.nPersonas = nPersonas;
        this.huespedPrincipal = huespedPrincipal;
        this.nNoches = nNoches;
    }

    /**
     * Obtiene el numero de personas en la reserva.
     *
     * @return Numero de personas en la reserva.
     */
    public Integer getnPersonas() {
        return nPersonas;
    }

    /**
     * Establece el numero de personas en la reserva.
     *
     * @param nPersonas Numero de personas en la reserva.
     */
    public void setnPersonas(Integer nPersonas) {
        this.nPersonas = nPersonas;
    }

    /**
     * Obtiene el nombre del huesped principal.
     *
     * @return Nombre del huesped principal.
     */
    public String getHuespedPrincipal() {
        return huespedPrincipal;
    }

    /**
     * Establece el nombre del huesped principal.
     *
     * @param huespedPrincipal Nombre del huesped principal.
     */
    public void setHuespedPrincipal(String huespedPrincipal) {
        this.huespedPrincipal = huespedPrincipal;
    }

    /**
     * Obtiene el numero de noches de la reserva.
     *
     * @return Numero de noches de la reserva.
     */
    public Integer getnNoches() {
        return nNoches;
    }

    /**
     * Establece el numero de noches de la reserva.
     *
     * @param nNoches Numero de noches de la reserva.
     */
    public void setnNoches(Integer nNoches) {
        this.nNoches = nNoches;
    }
}
