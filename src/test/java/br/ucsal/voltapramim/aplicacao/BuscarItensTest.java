package br.ucsal.voltapramim.aplicacao;

import br.ucsal.voltapramim.dominio.Categoria;
import br.ucsal.voltapramim.dominio.Item;
import br.ucsal.voltapramim.dominio.LocalCampus;
import br.ucsal.voltapramim.infraestrutura.busca.BuscaPorRelevancia;
import br.ucsal.voltapramim.infraestrutura.busca.BuscaRecentes;
import br.ucsal.voltapramim.infraestrutura.persistencia.ItemRepositorioEmMemoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuscarItensTest {
    @Test
    void buscaIgnorandoAcentosECaixaECombinaFiltros() {
        ItemRepositorioEmMemoria repositorio = new ItemRepositorioEmMemoria();
        Item mochila = new Item("VPM-AAAAA", "Mochila azul", "Na biblioteca", Categoria.BOLSAS,
                LocalCampus.BIBLIOTECA);
        Item chave = new Item("VPM-BBBBB", "Chave azul", "Na biblioteca", Categoria.CHAVES,
                LocalCampus.BIBLIOTECA);
        repositorio.salvar(mochila);
        repositorio.salvar(chave);
        BuscarItens busca = new BuscarItens(repositorio, new BuscaPorRelevancia(), new BuscaRecentes());

        var resultado = busca.executar("MOCHÍLA", "BOLSAS", "BIBLIOTECA");

        assertEquals(1, resultado.size());
        assertEquals(mochila.getId(), resultado.get(0).getId());
    }

    @Test
    void semTextoListaItensRecentesFiltrados() {
        ItemRepositorioEmMemoria repositorio = new ItemRepositorioEmMemoria();
        repositorio.salvar(new Item("VPM-AAAAA", "Mochila azul", "", Categoria.BOLSAS, LocalCampus.BIBLIOTECA));
        repositorio.salvar(new Item("VPM-BBBBB", "Chave", "", Categoria.CHAVES, LocalCampus.CANTINA));
        BuscarItens busca = new BuscarItens(repositorio, new BuscaPorRelevancia(), new BuscaRecentes());

        var resultado = busca.executar(" ", "CHAVES", null);

        assertEquals(1, resultado.size());
        assertEquals("Chave", resultado.get(0).getTitulo());
    }
}
