package br.ucsal.voltapramim.aplicacao;

import br.ucsal.voltapramim.dominio.Categoria;
import br.ucsal.voltapramim.dominio.Item;
import br.ucsal.voltapramim.dominio.LocalCampus;
import br.ucsal.voltapramim.infraestrutura.busca.BuscaPorRelevancia;
import br.ucsal.voltapramim.infraestrutura.busca.BuscaRecentes;
import br.ucsal.voltapramim.infraestrutura.persistencia.ItemRepositorioEmMemoria;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void buscaSemTextoOrdenaItensDoMaisRecenteParaOMaisAntigo() {
        ItemRepositorioEmMemoria repositorio = new ItemRepositorioEmMemoria();

        Item primeiro = new Item(
                "VPM-AAAAA",
                "Mochila",
                "",
                Categoria.BOLSAS,
                LocalCampus.BIBLIOTECA
        );

        Item segundo = new Item(
                "VPM-BBBBB",
                "Chave",
                "",
                Categoria.CHAVES,
                LocalCampus.CANTINA
        );

        repositorio.salvar(primeiro);
        repositorio.salvar(segundo);

        BuscarItens buscar = new BuscarItens(
                repositorio,
                new BuscaPorRelevancia(),
                new BuscaRecentes()
        );

        List<Item> resultado = buscar.executar(
                new ConsultaItens("", null, null)
        );

        assertEquals(2, resultado.size());
        assertEquals(java.util.Set.of(primeiro.getId(), segundo.getId()),
                resultado.stream().map(Item::getId).collect(java.util.stream.Collectors.toSet()));
        org.junit.jupiter.api.Assertions.assertFalse(resultado.get(0).getEncontradoEm().isBefore(resultado.get(1).getEncontradoEm()));
    }
    @Test
    void aplicaFiltrosEExcluiDevolvidosNasDuasEstrategias() {
        var repositorio = new ItemRepositorioEmMemoria();
        var ativo = new Item("VPM-A", "Chave azul", "", Categoria.CHAVES, LocalCampus.CANTINA);
        var devolvido = new Item("VPM-B", "Chave azul", "", Categoria.CHAVES, LocalCampus.CANTINA);
        devolvido.confirmarRecebimento();
        devolvido.registrarDevolucao();
        repositorio.salvar(ativo);
        repositorio.salvar(devolvido);
        var buscar = new BuscarItens(repositorio, new BuscaPorRelevancia(), new BuscaRecentes());
        assertEquals(List.of(ativo), buscar.executar("", "CHAVES", "CANTINA"));
        assertEquals(List.of(ativo), buscar.executar("CHÁVE", "CHAVES", "CANTINA"));
        assertEquals(List.of(), buscar.executar("chave", "CHAVES", "BIBLIOTECA"));
    }
}
