package Streaming;
public class Obra {
    private int id;
    private String titulo;
    private String categoria;
    private int anoLancamento;
    private int duracaoMinutos;

    public Obra(int id, String titulo, String categoria, int anoLancamento, int duracaoMinutos) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.anoLancamento = anoLancamento;
        this.duracaoMinutos = duracaoMinutos;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    @Override
    public String toString() {
        return "ID " + id + " | " + titulo + " (" + anoLancamento + ") - " + categoria + " [" + duracaoMinutos + " min]";
    }
}