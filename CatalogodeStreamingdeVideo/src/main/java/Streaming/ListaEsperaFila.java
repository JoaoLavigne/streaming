package Streaming;

public class ListaEsperaFila {
    private Celula primeiro;
    private Celula ultimo;

    public ListaEsperaFila() {
        this.primeiro = null;
        this.ultimo = null;
    }

    public void inserir(Obra obra) {
        Celula novaCelula = new Celula(obra);
        if (ultimo == null) {
            primeiro = novaCelula;
            ultimo = novaCelula;
        } else {
            ultimo.seguinte = novaCelula;
            ultimo = novaCelula;
        }
    }

    public Obra excluir() {
        if (primeiro == null) {
            return null;
        }
        Obra retirada = primeiro.conteudo;
        primeiro = primeiro.seguinte;
        if (primeiro == null) {
            ultimo = null;
        }
        return retirada;
    }

    public boolean excluirPorId(int id) {
        if (primeiro == null) {
            return false;
        }

        if (primeiro.conteudo.getId() == id) {
            excluir();
            return true;
        }

        Celula cursor = primeiro;
        while (cursor.seguinte != null) {
            if (cursor.seguinte.conteudo.getId() == id) {
                if (cursor.seguinte == ultimo) {
                    ultimo = cursor;
                }
                cursor.seguinte = cursor.seguinte.seguinte;
                return true;
            }
            cursor = cursor.seguinte;
        }
        return false;
    }

    public Obra buscar(int id) {
        Celula cursor = primeiro;
        while (cursor != null) {
            if (cursor.conteudo.getId() == id) {
                return cursor.conteudo;
            }
            cursor = cursor.seguinte;
        }
        return null;
    }

    public void listar() {
        if (primeiro == null) {
            System.out.println("A lista de assistir mais tarde esta vazia.");
            return;
        }
        Celula cursor = primeiro;
        while (cursor != null) {
            System.out.println(cursor.conteudo);
            cursor = cursor.seguinte;
        }
    }
}