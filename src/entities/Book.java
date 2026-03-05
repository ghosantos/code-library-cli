package entities;

public class Book {

    private Integer id;
    private String title;
    private String author;
    private String category;
    private Double price;

    public Book(String titulo, String autor, String categoria, Double preco) {
        this.title = titulo;
        this.author = autor;
        this.category = categoria;
        this.price = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return title;
    }

    public void setTitulo(String titulo) {
        this.title = titulo;
    }

    public String getAutor() {
        return author;
    }

    public void setAutor(String autor) {
        this.author = autor;
    }

    public String getCategoria() {
        return category;
    }

    public void setCategoria(String categoria) {
        this.category = categoria;
    }

    public Double getPreco() {
        return price;
    }

    public void setPreco(Double preco) {
        this.price = preco;
    }

    @Override
    public String toString() {
        return ("ID: " + getId() + " | " + getTitulo() + " (" + getAutor() + ") - R$ " + getPreco());
    }
}
