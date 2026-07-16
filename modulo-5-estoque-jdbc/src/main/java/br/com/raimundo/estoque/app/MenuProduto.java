package br.com.raimundo.estoque.app;

import br.com.raimundo.estoque.dao.ProdutoDao;
import br.com.raimundo.estoque.dao.ProdutoDaoJdbc;
import br.com.raimundo.estoque.model.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class MenuProduto {

    private final Scanner scanner = new Scanner(System.in);
    private final ProdutoDao produtoDao = new ProdutoDaoJdbc();

    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    try {
                        Produto produtoCadastro = new Produto();

                        System.out.println("Digite o nome do produto: ");
                        String nome = scanner.nextLine();
                        System.out.println("Digite o preco do produto: ");
                        BigDecimal preco = new BigDecimal(scanner.nextLine().replace(",", "."));
                        System.out.println("Quanto há de estoque do produto: ");
                        Integer estoque = Integer.parseInt(scanner.nextLine());

                        produtoCadastro.setNome(nome);
                        produtoCadastro.setPreco(preco);
                        produtoCadastro.setEstoque(estoque);

                        cadastrarProdutos(produtoCadastro);
                    } catch (NumberFormatException e){
                        System.out.println("Valor numérico inválido");
                    } catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }


                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    System.out.println("Digite o nome do produto buscado: ");
                    String trecho = scanner.nextLine();
                    buscarPorNome(trecho);
                    break;
                case 4:
                    System.out.println("Digite o id do produto: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    System.out.println("Digite o nova quantidade no estoque: ");
                    String texto = scanner.nextLine();
                    int quantidadeNoEstoque = Integer.parseInt(texto);
                    atualizarEstoque(id, quantidadeNoEstoque);
                    break;
                case 5:
                    System.out.println("Digite o ID do produto a ser removido: ");
                    Long idARemover = Long.parseLong(scanner.nextLine());
                    removerProduto(idARemover);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\n=== SISTEMA DE ESTOQUE ===");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Buscar por nome");
        System.out.println("4 - Atualizar quantidade");
        System.out.println("5 - Remover produto");
        System.out.println("0 - Sair");
    }

    private void cadastrarProdutos(Produto produto){
        produtoDao.salvar(produto);
    }

    private void listarProdutos() {
        for (Produto produto : produtoDao.listarTodos()) {
            System.out.println(produto);
        }
    }

    private void buscarPorNome(String trecho){
        List<Produto> itensEncontrados =  produtoDao.buscarPorNome(trecho);

        if (itensEncontrados.isEmpty()){
            System.out.println("Produto não encontrado");
            return;
        }
        for (Produto produto : itensEncontrados) {
            System.out.println(produto);
        }
    }

    private void atualizarEstoque(Long id, Integer estoque){
        produtoDao.atualizarEstoque(id,estoque);
    }

    private void removerProduto(Long id){
        produtoDao.removerPorId(id);
    }
}


