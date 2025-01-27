package biblioteca;

public class Libro {
    private String ISBN;
    private String titulo;

    public Libro(String ISBN, String titulo) {
        this.ISBN = ISBN;
        this.titulo = titulo;

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

    public String getTitulo() { return titulo; }
}
