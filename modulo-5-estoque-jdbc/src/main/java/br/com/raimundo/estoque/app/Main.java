package br.com.raimundo.estoque.app;

import br.com.raimundo.estoque.connection.ConnectionFactory;
import br.com.raimundo.estoque.dao.MovimentacaoDao;
import br.com.raimundo.estoque.dao.MovimentacaoDaoJdbc;
import br.com.raimundo.estoque.dao.ProdutoDao;
import br.com.raimundo.estoque.dao.ProdutoDaoJdbc;
import br.com.raimundo.estoque.model.Movimentacao;
import br.com.raimundo.estoque.model.Produto;
import br.com.raimundo.estoque.service.EstoqueService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MenuProduto menu = new MenuProduto();
        menu.iniciar();

//        ProdutoDao produtoDao = new ProdutoDaoJdbc();
//        MovimentacaoDao movimentacaoDao = new MovimentacaoDaoJdbc();
//
//        EstoqueService estoqueService = new EstoqueService(produtoDao, movimentacaoDao);
//
//        estoqueService.entradaEstoque(1L, 5);

    }

}


//TESTE DE CONEXÃO COM O BANCO DE DADOS
//        try (Connection connection = ConnectionFactory.getConnection()) {
//            System.out.println("Conexão aberta com sucesso!");
//            System.out.println(connection);
//        } catch (SQLException e) {
//            System.out.println("Erro ao conectar com o banco: " + e.getMessage());
//        }


//        CRIAÇÃO DE PRODUTO NA MEMORIA PARA POSTERIOR USO NO METODO ATUALIZAR
//        Produto produtoTeste = new Produto();
//        produtoTeste.setId(2L);
//        produtoTeste.setNome("Mouse Gamer RGB");
//        produtoTeste.setPreco(new BigDecimal("150.00"));
//        produtoTeste.setEstoque(20);

//        ProdutoDaoJdbc ProdutoDaoTeste = new ProdutoDaoJdbc();
//
//        for (Produto produto: ProdutoDaoTeste.listarTodos()) {
//            System.out.println(produto);
//        }

//        System.out.println();
//
//        ProdutoDaoTeste.removerPorId(11L);
//
//        System.out.println();
//
//        for (Produto produto: ProdutoDaoTeste.listarTodos()) {
//            System.out.println(produto);
//        }

//        TESTE DO METODO ATUALIZAR
//        ProdutoDaoTeste.atualizar(produtoTeste);



//        CODIGO PARA CRIAR NOVO PRODUTO NO BANCO
//        Produto produtoTeste = new Produto(
//                "Produto teste",
//                new BigDecimal("120.00"),
//                20
//        );
//        ProdutoDaoTeste.salvar(produtoTeste);

/*        COMANDO PARA LISTAR TODOS OS PRODUTOS DA TABELA produtos
        for (Produto produto: ProdutoDaoTeste.listarTodos()) {
            System.out.println(produto);
        }
*/
/*      CODIGO PARA BUSCA POR ID E POR TRECHO
        System.out.println();
        System.out.println(ProdutoDaoTeste.buscarPorNome("mouse"));
        System.out.println();
        System.out.println(ProdutoDaoTeste.buscarPorId(1L));
*/