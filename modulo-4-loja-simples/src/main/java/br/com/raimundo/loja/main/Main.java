package br.com.raimundo.loja.main;

import br.com.raimundo.loja.model.Cliente;
import br.com.raimundo.loja.model.Pedido;
import br.com.raimundo.loja.model.Produto;

public class Main {

    public static void main(String[] args) {
        Cliente clienteTeste = new Cliente("Raimundo",
                "123.456.789-00",
                "otenneto123@gmail.com");

        Produto produtoTeste = new Produto(1L,
                "curso Java",
                100.95F,
                1);

        Pedido pedidoTeste =
                new Pedido(
                        1L,
                        "apenas um teste",
                        clienteTeste);

        pedidoTeste.getProdutos().add(produtoTeste);

        System.out.println(clienteTeste);
        System.out.println(produtoTeste);
        System.out.println(pedidoTeste);
    }
}