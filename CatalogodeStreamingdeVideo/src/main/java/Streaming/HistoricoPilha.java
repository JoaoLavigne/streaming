package Streaming;

public class HistoricoPilha {
    private Celula topoPilha;

    public HistoricoPilha() {
        this.topoPilha = null;
    }

    public void inserir(Obra obra) {
        Celula novaCelula = new Celula(obra);
        novaCelula.seguinte = topoPilha;
        topoPilha = novaCelula;
    }

    public Obra excluir() {
        if (topoPilha == null) {
            return null;
        }
        Obra retirada = topoPilha.conteudo;
        topoPilha = topoPilha.seguinte;
        return retirada;
    }

    public boolean excluirPorId(int id) {
        if (topoPilha == null) {
            return false;
        }

        if (topoPilha.conteudo.getId() == id) {
            topoPilha = topoPilha.seguinte;
            return true;
        }

        Celula cursor = topoPilha;
        while (cursor.seguinte != null) {
            if (cursor.seguinte.conteudo.getId() == id) {
                cursor.seguinte = cursor.seguinte.seguinte;
                return true;
            }
            cursor = cursor.seguinte;
        }
        return false;
    }

    public Obra buscar(int id) {
        Celula cursor = topoPilha;
        while (cursor != null) {
            if (cursor.conteudo.getId() == id) {
                return cursor.conteudo;
            }
            cursor = cursor.seguinte;
        }
        return null;
    }

    public void listar() {
        if (topoPilha == null) {
            System.out.println("Nenhum titulo assistido ainda.");
            return;
        }
        Celula cursor = topoPilha;
        while (cursor != null) {
            System.out.println(cursor.conteudo);
            cursor = cursor.seguinte;
        }
    }
}