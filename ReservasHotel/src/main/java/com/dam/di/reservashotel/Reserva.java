package com.dam.di.reservashotel;

/**
 * Clase Reserva.
 */
public class Reserva {

    /**
     * Definicion de atributos locales
     */
    private int numPersonas;
    private String nombre;
    private int duracion;

    /**
     * Constructor con parametros
     *
     * @param numPersonas  num personas
     * @param nombre       nombre
     * @param duracion     duracion
     */
    public Reserva(int numPersonas, String nombre, int duracion) {
        this.numPersonas = numPersonas;
        this.nombre = nombre;
        this.duracion = duracion;
    }

    /**
     * Metodo getNumPersonas
     * Devuelve el valor de numPersonas
     * @return numPersonas
     */
    public int getNumPersonas() {
        return numPersonas;
    }

    /**
     * Metodo setNumpersonas
     * Cambia el valor de numPersonas por el pasado por parametro
     *
     * @param numPersonas  numero de personas
     */
    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    /**
     * Metodo getNombre
     * Devuelve el valor de nombre
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo setNombre
     * Cambia el valor de nombre por el pasado por parametro
     *
     * @param nombre nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo getDuracion
     * Devuelve el valor de duracion
     *
     * @return duracion
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Metodo setDuracion
     * Cambia el valor de duracion por el pasado por parametro
     *
     * @param duracion duracion
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
