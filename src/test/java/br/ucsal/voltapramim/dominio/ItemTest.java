Failed to create stream fd: Operation not permitted
Failed to create stream fd: Operation not permitted
Failed to create stream fd: Operation not permitted
package br.ucsal.voltapramim.dominio;

import br.ucsal.voltapramim.dominio.TransicaoInvalidaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemTest {
    @Test
    void iniciaVisivelAguardandoEntregaEPermiteCicloDeDevolucao() {
        Item item = novoItem("  Mochila azul  ");

        assertEquals("Mochila azul", item.getTitulo());
        assertEquals("Aguardando entrega", item.getEstadoRotulo());
        assertTrue(item.isVisivelNaBusca());

        item.confirmarRecebimento();
        assertEquals("Disponível para retirada", item.getEstadoRotulo());
        assertTrue(item.isVisivelNaBusca());

        item.registrarDevolucao();
        assertEquals("Devolvido", item.getEstadoRotulo());
        assertFalse(item.isVisivelNaBusca());
    }

    @Test
    void rejeitaDevolucaoAntesDoRecebimento() {
        Item item = novoItem("Mochila azul");

        assertThrows(TransicaoInvalidaException.class, item::registrarDevolucao);
        assertEquals("Aguardando entrega", item.getEstadoRotulo());
    }

    @Test
    void rejeitaTituloVazio() {
        assertThrows(IllegalArgumentException.class, () -> novoItem("   "));
    }

    private Item novoItem(String titulo) {
        return new Item("VPM-ABCDE", titulo, "", Categoria.BOLSAS, LocalCampus.BIBLIOTECA);
    }
}
