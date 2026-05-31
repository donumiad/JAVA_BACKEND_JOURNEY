package br.com.raimundo.loja;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    void deveSomarDoisNumerosCorretamente() {
        int resultado = 2 + 2;
        assertEquals(4, resultado);
    }
}
