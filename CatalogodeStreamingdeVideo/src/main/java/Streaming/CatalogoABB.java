package Streaming;

public class CatalogoABB {
    private NodoArvore noRaiz;

    public CatalogoABB() {
        this.noRaiz = null;
    }

    public void inserir(Obra novaObra) {
        noRaiz = adicionarRecursivo(noRaiz, novaObra);
    }

    private NodoArvore adicionarRecursivo(NodoArvore subarvore, Obra novaObra) {
        if (subarvore == null) {
            return new NodoArvore(novaObra);
        }

        if (novaObra.getId() < subarvore.conteudo.getId()) {
            subarvore.filhoEsquerdo = adicionarRecursivo(subarvore.filhoEsquerdo, novaObra);
        } else if (novaObra.getId() > subarvore.conteudo.getId()) {
            subarvore.filhoDireito = adicionarRecursivo(subarvore.filhoDireito, novaObra);
        }

        return subarvore;
    }

    public Obra buscar(int id) {
        return localizarRecursivo(noRaiz, id);
    }

    private Obra localizarRecursivo(NodoArvore subarvore, int id) {
        if (subarvore == null) {
            return null;
        }

        if (id == subarvore.conteudo.getId()) {
            return subarvore.conteudo;
        }

        if (id < subarvore.conteudo.getId()) {
            return localizarRecursivo(subarvore.filhoEsquerdo, id);
        } else {
            return localizarRecursivo(subarvore.filhoDireito, id);
        }
    }

    public boolean excluir(int id) {
        if (buscar(id) == null) {
            return false;
        }
        noRaiz = removerRecursivo(noRaiz, id);
        return true;
    }

    private NodoArvore removerRecursivo(NodoArvore subarvore, int id) {
        if (subarvore == null) {
            return null;
        }

        if (id < subarvore.conteudo.getId()) {
            subarvore.filhoEsquerdo = removerRecursivo(subarvore.filhoEsquerdo, id);
        } else if (id > subarvore.conteudo.getId()) {
            subarvore.filhoDireito = removerRecursivo(subarvore.filhoDireito, id);
        } else {
            if (subarvore.filhoEsquerdo == null) {
                return subarvore.filhoDireito;
            }
            if (subarvore.filhoDireito == null) {
                return subarvore.filhoEsquerdo;
            }

            Obra substituto = obterMaiorDaEsquerda(subarvore.filhoEsquerdo);
            subarvore.conteudo = substituto;
            subarvore.filhoEsquerdo = removerRecursivo(subarvore.filhoEsquerdo, substituto.getId());
        }

        return subarvore;
    }

    private Obra obterMaiorDaEsquerda(NodoArvore nodo) {
        while (nodo.filhoDireito != null) {
            nodo = nodo.filhoDireito;
        }
        return nodo.conteudo;
    }

    public void exibirInOrdem() {
        if (noRaiz == null) {
            System.out.println("Nenhuma obra cadastrada.");
            return;
        }
        visitarInOrdem(noRaiz);
    }

    public void exibirPreOrdem() {
        if (noRaiz == null) {
            System.out.println("Nenhuma obra cadastrada.");
            return;
        }
        visitarPreOrdem(noRaiz);
    }

    public void exibirPosOrdem() {
        if (noRaiz == null) {
            System.out.println("Nenhuma obra cadastrada.");
            return;
        }
        visitarPosOrdem(noRaiz);
    }

    private void visitarInOrdem(NodoArvore nodo) {
        if (nodo != null) {
            visitarInOrdem(nodo.filhoEsquerdo);
            System.out.println(nodo.conteudo);
            visitarInOrdem(nodo.filhoDireito);
        }
    }

    private void visitarPreOrdem(NodoArvore nodo) {
        if (nodo != null) {
            System.out.println(nodo.conteudo);
            visitarPreOrdem(nodo.filhoEsquerdo);
            visitarPreOrdem(nodo.filhoDireito);
        }
    }

    private void visitarPosOrdem(NodoArvore nodo) {
        if (nodo != null) {
            visitarPosOrdem(nodo.filhoEsquerdo);
            visitarPosOrdem(nodo.filhoDireito);
            System.out.println(nodo.conteudo);
        }
    }
}