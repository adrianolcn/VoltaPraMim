package br.ucsal.voltapramim.web;

import br.ucsal.voltapramim.aplicacao.BuscarItens;
import br.ucsal.voltapramim.aplicacao.CadastrarItem;
import br.ucsal.voltapramim.aplicacao.ConsultarItem;
import br.ucsal.voltapramim.dominio.Categoria;
import br.ucsal.voltapramim.dominio.LocalCampus;
import br.ucsal.voltapramim.infraestrutura.busca.BuscaPorRelevancia;
import br.ucsal.voltapramim.infraestrutura.busca.BuscaRecentes;
import br.ucsal.voltapramim.infraestrutura.eventos.PublicadorEmMemoria;
import br.ucsal.voltapramim.infraestrutura.persistencia.ItemRepositorioEmMemoria;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class IntegracaoInicioTest {
    @Test void cadastroEConsultaCompartilhamItensAtivos(@org.junit.jupiter.api.io.TempDir java.nio.file.Path pasta) {
        var repositorio = new ItemRepositorioEmMemoria();
        var cadastrar = new CadastrarItem(repositorio, new PublicadorEmMemoria(), new br.ucsal.voltapramim.infraestrutura.fotos.ArmazenamentoLocalDeFotos(pasta));
        var buscar = new BuscarItens(repositorio, new BuscaPorRelevancia(), new BuscaRecentes());
        var consultar = new ConsultarItem(repositorio);
        assertTrue(buscar.executar("", null, null).isEmpty());
        var item = cadastrar.executar("Chave", "", Categoria.CHAVES, LocalCampus.CANTINA);
        assertEquals(item.getId(), consultar.executar(item.getId()).orElseThrow().getId());
        assertEquals(item.getId(), buscar.executar("", null, null).get(0).getId());
        item.confirmarRecebimento();
        item.registrarDevolucao();
        assertTrue(buscar.executar("", null, null).isEmpty());
        assertTrue(buscar.executar("chave", null, null).isEmpty());
    }

    @Test void rotasSelecionamComandoPorMetodoECaminho() {
        ComandoWeb inicio = (request, response) -> {};
        ComandoWeb busca = (request, response) -> {};
        ComandoWeb novo = (request, response) -> {};
        ComandoWeb salvar = (request, response) -> {};
        ComandoWeb design = (request, response) -> {};
        ComandoWeb detalhe = (request, response) -> {};
        var roteador = new Roteador(Map.of(
                new ChaveRota("GET", "/"), inicio,
                new ChaveRota("GET", "/home"), inicio,
                new ChaveRota("GET", "/buscar"), busca,
                new ChaveRota("GET", "/achei"), novo,
                new ChaveRota("POST", "/achei"), salvar,
                new ChaveRota("GET", "/design-system"), design), detalhe, detalhe);
        assertSame(inicio, roteador.resolver("GET", "/").orElseThrow());
        assertSame(inicio, roteador.resolver("GET", "/home").orElseThrow());
        assertSame(busca, roteador.resolver("GET", "/buscar").orElseThrow());
        assertSame(novo, roteador.resolver("GET", "/achei").orElseThrow());
        assertSame(salvar, roteador.resolver("POST", "/achei").orElseThrow());
        assertSame(design, roteador.resolver("GET", "/design-system").orElseThrow());
        assertSame(detalhe, roteador.resolver("GET", "/itens/123").orElseThrow());
        assertTrue(roteador.resolver("GET", "/inexistente").isEmpty());
        assertTrue(roteador.resolver("POST", "/buscar").isEmpty());
        assertTrue(roteador.resolver("POST", "/itens/123").isEmpty());
    }
}
