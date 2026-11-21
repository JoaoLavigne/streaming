package Streaming;
public class Celula {
    public Obra conteudo;
    public Celula seguinte;

    public Celula(Obra conteudo) {
        this.conteudo = conteudo;
        this.seguinte = null;
    }
}