package br.com.raimundo.estoque.service;

import br.com.raimundo.estoque.exception.ParametroInvalidoException;
import br.com.raimundo.estoque.exception.ProdutoNaoEncontradoException;
import br.com.raimundo.estoque.model.PaginaProduto;
import br.com.raimundo.estoque.model.Produto;
import br.com.raimundo.estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.listarTodos();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.buscarPorId(id)
                .orElseThrow(
                        () -> new ProdutoNaoEncontradoException(id)
                );
    }

    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.buscarPorNome(nome);
    }

    public Produto cadastrar(Produto produto) {
        return produtoRepository.salvar(produto);
    }

    public Optional<Produto> atualizar(
            Long id,
            String nome,
            BigDecimal preco
    ) {

        Optional<Produto> produtoExistente =
                produtoRepository.buscarPorId(id);

        if (produtoExistente.isEmpty()) {
            return Optional.empty();
        }

        Produto atual = produtoExistente.get();

        Produto produtoAtualizado = new Produto(
                atual.getId(),
                nome,
                preco,
                atual.getEstoque()
        );

        produtoRepository.atualizar(produtoAtualizado);

        return Optional.of(produtoAtualizado);
    }

    public boolean remover(Long id) {
        return produtoRepository.removerPorId(id);
    }

    public PaginaProduto listarPaginado(
            String nome,
            int page,
            int size,
            String sort,
            String direction
    ) {

        if (page < 0) {
            throw new ParametroInvalidoException(
                    "page deve ser maior ou igual a zero"
            );
        }

        if (size < 1 || size > 100) {
            throw new ParametroInvalidoException(
                    "size deve estar entre 1 e 100"
            );
        }

        Set<String> camposPermitidos =
                Set.of("id", "nome", "preco", "estoque");

        String sortNormalizado =
                sort.toLowerCase();

        if (!camposPermitidos.contains(sortNormalizado)) {
            throw new ParametroInvalidoException(
                    "Campo de ordenação não permitido: " + sort
            );
        }

        String directionNormalizada =
                direction.toLowerCase();

        if (!directionNormalizada.equals("asc")
                && !directionNormalizada.equals("desc")) {

            throw new ParametroInvalidoException(
                    "direction deve ser asc ou desc"
            );
        }

        List<Produto> produtos =
                produtoRepository.buscarPaginado(
                        nome,
                        page,
                        size,
                        sortNormalizado,
                        directionNormalizada
                );

        long total =
                produtoRepository.contar(nome);

        boolean proximaPagina = ((long) page + 1) * size < total;

        return new PaginaProduto(
                produtos,
                page,
                size,
                total,
                proximaPagina
        );
    }
}