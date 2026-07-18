package br.com.raimundo.estoque.app;

import br.com.raimundo.estoque.dao.MovimentacaoDao;
import br.com.raimundo.estoque.dao.MovimentacaoDaoJdbc;
import br.com.raimundo.estoque.dao.ProdutoDao;
import br.com.raimundo.estoque.dao.ProdutoDaoJdbc;
import br.com.raimundo.estoque.exceptions.DataAccessException;
import br.com.raimundo.estoque.exceptions.ProdutoNaoEncontradoException;
import br.com.raimundo.estoque.model.Movimentacao;
import br.com.raimundo.estoque.model.Produto;
import br.com.raimundo.estoque.service.EstoqueService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class MenuProduto {

    private final Scanner scanner = new Scanner(System.in);
    private final ProdutoDao produtoDao = new ProdutoDaoJdbc();
    private final MovimentacaoDao movimentacaoDao = new MovimentacaoDaoJdbc();
    private final EstoqueService estoqueService = new EstoqueService(produtoDao, movimentacaoDao);

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
                        cadastrarProdutos();// recebe o produto com id

                    } catch (RuntimeException e) {
                        System.out.println("Erro ao cadastrar: " + e.getMessage());
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
                    registrarEntradaEstoque();
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
        System.out.println("4 - Incrementar quantidade");
        System.out.println("5 - Remover produto");
        System.out.println("0 - Sair");
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        String texto = scanner.nextLine().trim();
        if (texto.isBlank()){
            throw new IllegalArgumentException("Este campo não pode ser vazio.");
        }
        return texto;

    }

    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número inteiro.");
            }
        }
    }

    private Long lerLong(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número válido.");
            }
        }
    }

    private BigDecimal lerDecimal(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return new BigDecimal(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número decimal. Ex: 29.90");
            }
        }
    }

    private void cadastrarProdutos(){
        String nome = lerTexto("Digite o nome do produto: ");
        BigDecimal preco = lerDecimal("Digite o preco do produto: ");
        Integer estoque = lerInteiro("Digite estoque do produto: ");

        Produto produto = new Produto(nome, preco, estoque);

         Produto salvo = produtoDao.salvar(produto);

        System.out.println("Produto cadastrado com sucesso!");
        System.out.println("ID gerado: " + salvo.getId());
        System.out.println("Nome: "      + salvo.getNome());






    }

    private void listarProdutos() {
        try {
            List<Produto> produtos = produtoDao.listarTodos();

            if (produtos.isEmpty()) {
                System.out.println("Nenhum produto cadastrado.");
                return;
            }

            for (Produto produto : produtos) {
                System.out.println(produto);
            }

        } catch (DataAccessException e) {
            System.out.println("Erro ao buscar produtos. Tente novamente.");
            System.err.println("[ERRO TÉCNICO] " + e.getMessage());
        }
    }

    private void buscarPorNome(String trecho){

        try {
            List<Produto> itensEncontrados =  produtoDao.buscarPorNome(trecho);

            if (itensEncontrados.isEmpty()){
                System.out.println("Produto não encontrado");
                return;
            }
            for (Produto produto : itensEncontrados) {
                System.out.println(produto);
            }
        }catch (DataAccessException e){
            System.err.println("Detalhes tecnicos: " + e.getMessage());
        }

    }

    private void registrarEntradaEstoque() {
        Long id = lerLong("Digite o id do produto: ");

        int quantidadeEntrada = lerInteiro("Digite a quantidade de entrada: ");

        try {
            estoqueService.entradaEstoque(id, quantidadeEntrada);

            System.out.println("Entrada de estoque registrada com sucesso.");

        } catch (ProdutoNaoEncontradoException e) {
            System.out.println(e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println( "Valor inválido: " + e.getMessage());

        } catch (DataAccessException e) {
            System.out.println("Não foi possível registrar a entrada de estoque.");

            e.printStackTrace();
        }
    }

    private void removerProduto(Long id){
        produtoDao.removerPorId(id);
    }
}


