package br.com.raimundo.estoque.service;

import br.com.raimundo.estoque.connection.ConnectionFactory;
import br.com.raimundo.estoque.dao.MovimentacaoDao;
import br.com.raimundo.estoque.dao.ProdutoDao;
import br.com.raimundo.estoque.exceptions.DataAccessException;
import br.com.raimundo.estoque.exceptions.ProdutoNaoEncontradoException;
import br.com.raimundo.estoque.model.Movimentacao;

import java.sql.Connection;
import java.sql.SQLException;

public class EstoqueService {


    private final ProdutoDao produtoDao;
    private final MovimentacaoDao movimentacaoDao;

    public EstoqueService(
            ProdutoDao produtoDao,
            MovimentacaoDao movimentacaoDao
    ) {
        this.produtoDao = produtoDao;
        this.movimentacaoDao = movimentacaoDao;
    }

    public void entradaEstoque(
            Long idProduto,
            Integer quantidadeEntrada
    ) {
        validarEntrada(idProduto, quantidadeEntrada);

        try (Connection connection =
                     ConnectionFactory.getConnection()) {

            connection.setAutoCommit(false);

            try {
                boolean produtoAtualizado =
                        produtoDao.incrementarEstoque(
                                connection,
                                idProduto,
                                quantidadeEntrada
                        );

                if (!produtoAtualizado) {
                    throw new ProdutoNaoEncontradoException(
                            idProduto
                    );
                }

                Movimentacao movimentacao =
                        new Movimentacao(
                                idProduto,
                                "ENTRADA",
                                quantidadeEntrada
                        );

                movimentacaoDao.registrar(
                        movimentacao,
                        connection

                );

                connection.commit();

            } catch (SQLException e) {
                executarRollback(connection, e);

                throw new DataAccessException(
                        "Não foi possível registrar a entrada de estoque.",
                        e
                );
            }

            catch (RuntimeException e) {
                executarRollback(connection, e);
                throw e;
            }

        } catch (SQLException e) {
            throw new DataAccessException(
                    "Erro ao acessar o banco de dados.",
                    e
            );
        }
    }

    private void validarEntrada(
            Long idProduto,
            Integer quantidadeEntrada
    ) {
        if (idProduto == null) {
            throw new IllegalArgumentException(
                    "O id do produto não pode ser nulo."
            );
        }

        if (quantidadeEntrada == null ||
                quantidadeEntrada <= 0) {

            throw new IllegalArgumentException(
                    "A quantidade de entrada deve ser maior que zero."
            );
        }
    }

    private void executarRollback(Connection connection,Exception erroOriginal) {
        try {
            connection.rollback();
        } catch (SQLException erroRollback) {
            erroOriginal.addSuppressed(erroRollback);
        }
    }
}
