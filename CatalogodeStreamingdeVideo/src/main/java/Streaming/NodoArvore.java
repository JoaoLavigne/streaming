package Streaming;

public class NodoArvore {
    public Obra conteudo;
    public NodoArvore filhoEsquerdo;
    public NodoArvore filhoDireito;

    public NodoArvore(Obra conteudo) {
        this.conteudo = conteudo;
        this.filhoEsquerdo = null;
        this.filhoDireito = null;
    }
}