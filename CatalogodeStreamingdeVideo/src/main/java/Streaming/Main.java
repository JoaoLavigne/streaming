package Streaming;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        CatalogoABB catalogo = new CatalogoABB();
        HistoricoPilha recentes = new HistoricoPilha();
        ListaEsperaFila assistirDepois = new ListaEsperaFila();

        catalogo.inserir(new Obra(100, "Duna: Parte Dois", "Ficcao Cientifica", 2024, 166));
        catalogo.inserir(new Obra(50, "Oppenheimer", "Drama Historico", 2023, 180));
        catalogo.inserir(new Obra(150, "The Last of Us", "Pos-Apocaliptico", 2023, 55));
        catalogo.inserir(new Obra(25, "Matrix", "Acao", 1999, 136));
        catalogo.inserir(new Obra(75, "Clube da Luta", "Suspense", 1999, 139));
        catalogo.inserir(new Obra(125, "Severance", "Misterio", 2022, 50));
        catalogo.inserir(new Obra(175, "Parasita", "Thriller", 2019, 132));

        int escolha = -1;

        while (escolha != 0) {
            System.out.println("\n=== CINEFLOW STREAMING ===");
            System.out.println("1. Cadastrar nova obra no catalogo (ABB)");
            System.out.println("2. Pesquisar obra no catalogo por ID");
            System.out.println("3. Remover obra do sistema");
            System.out.println("4. Listar catalogo (In-Ordem, Pre-Ordem e Pos-Ordem)");
            System.out.println("5. Salvar obra na fila 'Assistir mais tarde'");
            System.out.println("6. Reproduzir proximo da fila (Fila -> Pilha)");
            System.out.println("7. Assistir obra direto do catalogo (ABB -> Pilha)");
            System.out.println("8. Exibir ou buscar na fila 'Assistir mais tarde'");
            System.out.println("9. Exibir ou buscar no historico de assistidos (Pilha)");
            System.out.println("10. Apagar ultimo registro do historico (Pop)");
            System.out.println("0. Encerrar");
            System.out.print("Digite sua escolha: ");

            escolha = leitor.nextInt();
            leitor.nextLine();

            switch (escolha) {
                case 1:
                    System.out.print("ID numerico: ");
                    int id = leitor.nextInt();
                    leitor.nextLine();
                    if (catalogo.buscar(id) != null) {
                        System.out.println("Ja existe uma obra com esse ID.");
                        break;
                    }
                    System.out.print("Titulo: ");
                    String titulo = leitor.nextLine();
                    System.out.print("Categoria: ");
                    String categoria = leitor.nextLine();
                    System.out.print("Ano de lancamento: ");
                    int ano = leitor.nextInt();
                    System.out.print("Duracao em minutos: ");
                    int duracao = leitor.nextInt();
                    leitor.nextLine();
                    catalogo.inserir(new Obra(id, titulo, categoria, ano, duracao));
                    System.out.println("Obra cadastrada com sucesso.");
                    break;

                case 2:
                    System.out.print("Informe o ID para pesquisa: ");
                    int idBusca = leitor.nextInt();
                    Obra resultado = catalogo.buscar(idBusca);
                    if (resultado != null) {
                        System.out.println("Localizado: " + resultado);
                    } else {
                        System.out.println("Obra nao encontrada no catalogo.");
                    }
                    break;

                case 3:
                    System.out.print("Informe o ID da obra a remover: ");
                    int idRemover = leitor.nextInt();
                    if (catalogo.excluir(idRemover)) {
                        assistirDepois.excluirPorId(idRemover);
                        recentes.excluirPorId(idRemover);
                        System.out.println("Obra excluida do catalogo, fila e historico.");
                    } else {
                        System.out.println("ID nao encontrado no catalogo.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- PERCURSO IN-ORDEM ---");
                    catalogo.exibirInOrdem();
                    System.out.println("\n--- PERCURSO PRE-ORDEM ---");
                    catalogo.exibirPreOrdem();
                    System.out.println("\n--- PERCURSO POS-ORDEM ---");
                    catalogo.exibirPosOrdem();
                    break;

                case 5:
                    System.out.print("ID da obra para adicionar a fila: ");
                    int idFila = leitor.nextInt();
                    Obra obraFila = catalogo.buscar(idFila);
                    if (obraFila == null) {
                        System.out.println("ID inexistente no catalogo.");
                    } else if (assistirDepois.buscar(idFila) != null) {
                        System.out.println("Essa obra ja esta na sua fila.");
                    } else {
                        assistirDepois.inserir(obraFila);
                        System.out.println("Adicionado a fila: " + obraFila.getTitulo());
                    }
                    break;

                case 6:
                    Obra proximaObra = assistirDepois.excluir();
                    if (proximaObra == null) {
                        System.out.println("Nao ha obras na fila de espera.");
                    } else {
                        recentes.inserir(proximaObra);
                        System.out.println("Reproduzindo agora: " + proximaObra);
                    }
                    break;

                case 7:
                    System.out.print("ID da obra que deseja assistir agora: ");
                    int idAssistir = leitor.nextInt();
                    Obra obraAssistir = catalogo.buscar(idAssistir);
                    if (obraAssistir == null) {
                        System.out.println("Obra nao encontrada no catalogo.");
                    } else {
                        recentes.inserir(obraAssistir);
                        System.out.println("Reproduzindo agora: " + obraAssistir);
                    }
                    break;

                case 8:
                    System.out.println("\n--- FILA ASSISTIR MAIS TARDE ---");
                    assistirDepois.listar();
                    System.out.print("Pesquisar ID na fila (0 para voltar): ");
                    int buscaFila = leitor.nextInt();
                    if (buscaFila != 0) {
                        Obra achadaFila = assistirDepois.buscar(buscaFila);
                        System.out.println(achadaFila != null ? "Na fila: " + achadaFila : "Nao esta na fila.");
                    }
                    break;

                case 9:
                    System.out.println("\n--- HISTORICO RECENTE ---");
                    recentes.listar();
                    System.out.print("Pesquisar ID no historico (0 para voltar): ");
                    int buscaHist = leitor.nextInt();
                    if (buscaHist != 0) {
                        Obra achadaHist = recentes.buscar(buscaHist);
                        System.out.println(achadaHist != null ? "Ja assistido: " + achadaHist : "Ainda nao foi assistido.");
                    }
                    break;

                case 10:
                    Obra removidaHistorico = recentes.excluir();
                    if (removidaHistorico == null) {
                        System.out.println("O historico ja esta vazio.");
                    } else {
                        System.out.println("Removido do topo do historico: " + removidaHistorico);
                    }
                    break;

                case 0:
                    System.out.println("Saindo do CineFlow...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        }

        leitor.close();
    }
}