package br.ucsal.voltapramim.aplicacao;

import br.ucsal.voltapramim.aplicacao.porta.OuvinteDeEvento;
import br.ucsal.voltapramim.aplicacao.porta.PublicadorDeEventos;
import br.ucsal.voltapramim.dominio.Categoria;
import br.ucsal.voltapramim.dominio.LocalCampus;
import br.ucsal.voltapramim.dominio.evento.EventoDominio;
import br.ucsal.voltapramim.dominio.evento.ItemCadastrado;
import br.ucsal.voltapramim.infraestrutura.persistencia.ItemRepositorioEmMemoria;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CadastrarItemTest {
    @Test
    void persisteItemComReferenciaPublicaEPublicaEvento() {
        ItemRepositorioEmMemoria repositorio = new ItemRepositorioEmMemoria();
        PublicadorDeEventosFake eventos = new PublicadorDeEventosFake(repositorio);
        CadastrarItem cadastro = new CadastrarItem(repositorio, eventos);

        var item = cadastro.executar("Mochila azul", "Perto da biblioteca", Categoria.BOLSAS,
                LocalCampus.BIBLIOTECA);

        assertTrue(Pattern.matches("VPM-[A-HJ-NP-Z2-9]{5}", item.getReferenciaPublica()));
        assertTrue(repositorio.buscarPorId(item.getId()).isPresent());
        assertEquals(1, eventos.publicados.size());
        assertTrue(eventos.publicados.get(0) instanceof ItemCadastrado);
        assertTrue(eventos.itemJaEstavaPersistido);
        assertEquals(item.getId(), ((ItemCadastrado) eventos.publicados.get(0)).itemId());
    }

    private static final class PublicadorDeEventosFake implements PublicadorDeEventos {
        private final ItemRepositorioEmMemoria repositorio;
        private final List<EventoDominio> publicados = new ArrayList<>();
        private boolean itemJaEstavaPersistido;

        private PublicadorDeEventosFake(ItemRepositorioEmMemoria repositorio) {
            this.repositorio = repositorio;
        }

        @Override public void inscrever(OuvinteDeEvento ouvinte) { }
        @Override public void publicar(EventoDominio evento) {
            publicados.add(evento);
            if (evento instanceof ItemCadastrado cadastrado) {
                itemJaEstavaPersistido = repositorio.buscarPorId(cadastrado.itemId()).isPresent();
            }
        }
    }
}
