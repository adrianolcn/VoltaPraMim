package br.ucsal.voltapramim.aplicacao;

import br.ucsal.voltapramim.aplicacao.porta.ArmazenamentoDeFotos;
import br.ucsal.voltapramim.aplicacao.porta.FotoArmazenada;
import br.ucsal.voltapramim.aplicacao.porta.FotoUpload;
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
import java.util.Optional;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CadastrarItemTest {

    @Test
    void persisteItemComReferenciaPublicaEPublicaEvento() {
        ItemRepositorioEmMemoria repositorio =
                new ItemRepositorioEmMemoria();

        PublicadorDeEventosFake eventos =
                new PublicadorDeEventosFake(repositorio);

        ArmazenamentoDeFotosFake fotos =
                new ArmazenamentoDeFotosFake();

        CadastrarItem cadastro =
                new CadastrarItem(repositorio, eventos, fotos);

        var item = cadastro.executar(
                "Mochila azul",
                "Perto da biblioteca",
                Categoria.BOLSAS,
                LocalCampus.BIBLIOTECA
        );

        assertTrue(
                Pattern.matches(
                        "VPM-[A-HJ-NP-Z2-9]{5}",
                        item.getReferenciaPublica()
                )
        );

        assertTrue(repositorio.buscarPorId(item.getId()).isPresent());
        assertFalse(item.temFoto());
        assertEquals(1, eventos.publicados.size());
        assertTrue(eventos.publicados.get(0) instanceof ItemCadastrado);
        assertTrue(eventos.itemJaEstavaPersistido);

        assertEquals(
                item.getId(),
                ((ItemCadastrado) eventos.publicados.get(0)).itemId()
        );
    }

    @Test
    void associaFotoSalvaAoItem() {
        ItemRepositorioEmMemoria repositorio =
                new ItemRepositorioEmMemoria();

        PublicadorDeEventosFake eventos =
                new PublicadorDeEventosFake(repositorio);

        ArmazenamentoDeFotosFake fotos =
                new ArmazenamentoDeFotosFake();

        CadastrarItem cadastro =
                new CadastrarItem(repositorio, eventos, fotos);

        FotoUpload foto = new FotoUpload(
                new byte[]{1, 2, 3},
                "image/png"
        );

        var item = cadastro.executar(
                "Mochila azul",
                "",
                Categoria.BOLSAS,
                LocalCampus.BIBLIOTECA,
                foto
        );

        assertTrue(item.temFoto());
        assertEquals("foto-teste", item.getFotoId().orElseThrow());
        assertEquals(foto, fotos.ultimaFotoSalva);
    }

    @Test
    void rejeitaFotoParaDocumentoSemSalvarArquivo() {
        ItemRepositorioEmMemoria repositorio =
                new ItemRepositorioEmMemoria();

        PublicadorDeEventosFake eventos =
                new PublicadorDeEventosFake(repositorio);

        ArmazenamentoDeFotosFake fotos =
                new ArmazenamentoDeFotosFake();

        CadastrarItem cadastro =
                new CadastrarItem(repositorio, eventos, fotos);

        FotoUpload foto = new FotoUpload(
                new byte[]{1, 2, 3},
                "image/png"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> cadastro.executar(
                        "Carteira estudantil",
                        "",
                        Categoria.DOCUMENTOS,
                        LocalCampus.BIBLIOTECA,
                        foto
                )
        );

        assertEquals(null, fotos.ultimaFotoSalva);
        assertTrue(eventos.publicados.isEmpty());
    }

    @Test
    void tituloInvalidoNaoGravaFotoNemPublicaEvento() {
        var repositorio = new ItemRepositorioEmMemoria();
        var eventos = new PublicadorDeEventosFake(repositorio);
        var fotos = new ArmazenamentoDeFotosFake();
        var cadastro = new CadastrarItem(repositorio, eventos, fotos);
        var foto = new FotoUpload(new byte[]{1}, "image/png");
        assertThrows(IllegalArgumentException.class, () -> cadastro.executar(" ", "", Categoria.OUTROS, LocalCampus.CANTINA, foto));
        assertEquals(null, fotos.ultimaFotoSalva);
        assertTrue(repositorio.listarAtivos().isEmpty());
        assertTrue(eventos.publicados.isEmpty());
    }

    private static final class PublicadorDeEventosFake
            implements PublicadorDeEventos {

        private final ItemRepositorioEmMemoria repositorio;
        private final List<EventoDominio> publicados =
                new ArrayList<>();

        private boolean itemJaEstavaPersistido;

        private PublicadorDeEventosFake(
                ItemRepositorioEmMemoria repositorio
        ) {
            this.repositorio = repositorio;
        }

        @Override
        public void inscrever(OuvinteDeEvento ouvinte) {
        }

        @Override
        public void publicar(EventoDominio evento) {
            publicados.add(evento);

            if (evento instanceof ItemCadastrado cadastrado) {
                itemJaEstavaPersistido =
                        repositorio.buscarPorId(
                                cadastrado.itemId()
                        ).isPresent();
            }
        }
    }

    private static final class ArmazenamentoDeFotosFake
            implements ArmazenamentoDeFotos {

        private FotoUpload ultimaFotoSalva;

        @Override
        public String salvar(FotoUpload foto) {
            ultimaFotoSalva = foto;
            return "foto-teste";
        }

        @Override
        public void remover(String id) { ultimaFotoSalva = null; }

        @Override
        public Optional<FotoArmazenada> buscarPorId(String fotoId) {
            return Optional.empty();
        }
    }
}