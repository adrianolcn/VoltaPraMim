package br.ucsal.voltapramim.infraestrutura.eventos;

import br.ucsal.voltapramim.aplicacao.porta.OuvinteDeEvento;
import br.ucsal.voltapramim.aplicacao.porta.PublicadorDeEventos;
import br.ucsal.voltapramim.dominio.evento.EventoDominio;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PublicadorEmMemoria implements PublicadorDeEventos {
    private static final Logger LOG = Logger.getLogger(PublicadorEmMemoria.class.getName());
    private final CopyOnWriteArrayList<OuvinteDeEvento> ouvintes = new CopyOnWriteArrayList<>();
    @Override public void inscrever(OuvinteDeEvento ouvinte) { ouvintes.add(Objects.requireNonNull(ouvinte)); }
    @Override public void publicar(EventoDominio evento) {
        for (OuvinteDeEvento ouvinte : ouvintes) {
            try { ouvinte.aoReceber(evento); }
            catch (RuntimeException erro) { LOG.log(Level.WARNING, "Observer falhou ao processar evento.", erro); }
        }
    }
}
