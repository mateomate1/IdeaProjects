package biblioteca;

public class Libro {
    private String ISBN;
    private String titulo;
    private Autor autor;
    private Integer ventas;
    /*
        public Libro(String ISBN, String titulo) {
            this.ISBN = ISBN;
            this.titulo = titulo;

        }
    */
    public Libro(String ISBN, String titulo, Autor autor) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;

    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Integer getVentas() {
        return ventas;
    }
    public void setVentas(Integer ventas) {
        this.ventas = ventas;
    }
}

