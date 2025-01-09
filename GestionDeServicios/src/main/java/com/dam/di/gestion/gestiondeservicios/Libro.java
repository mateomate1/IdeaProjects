package com.dam.di.gestion.gestiondeservicios;

import java.io.Serializable;
import java.util.List;

public class Libro implements Serializable {
    private String titulo, autor, genero, ISBN, editorial;
    private int anio, paginas, precio;
    private final String rutaArchivos = "biblioteca.dat";
    private transient GestorBin<Libro> gestorBin = new GestorBin<>(rutaArchivos);
    public boolean alta() {
        List<Libro> libros = gestorBin.leer();
        for(Libro l : libros){
            if(l.getTitulo().equals(this.titulo)){
                return false;
            }
        }
        gestorBin.add(this);
        return true;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
