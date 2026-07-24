package br.com.raimundo.estoque.app;

import br.com.raimundo.estoque.service.ProdutoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TesteInjecao implements CommandLineRunner {

    private final ProdutoService produtoService;

    public TesteInjecao(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    public void run(String... args) {
        System.out.println("\n=== TESTE DE INJEÇÃO ===");

        System.out.println("\nTodos os produtos:");
        produtoService.listarTodos()
                .forEach(System.out::println);

        System.out.println("\nProduto de ID 2:");
        produtoService.buscarPorId(2L)
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Produto não encontrado.")
                );

        System.out.println("\nBusca pelo nome 'mouse':");
        produtoService.buscarPorNome("mouse")
                .forEach(System.out::println);
    }
}