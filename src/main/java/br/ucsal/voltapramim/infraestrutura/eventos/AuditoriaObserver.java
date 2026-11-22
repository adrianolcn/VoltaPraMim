package br.ucsal.voltapramim.infraestrutura.eventos;

import br.ucsal.voltapramim.aplicacao.porta.OuvinteDeEvento;
import br.ucsal.voltapramim.dominio.evento.EventoDominio;
import java.util.logging.Logger;

public final class AuditoriaObserver implements OuvinteDeEvento {
    private static final Logger LOG = Logger.getLogger(AuditoriaObserver.class.getName());
    @Override public void aoReceber(EventoDominio evento) { LOG.info(() -> "evento=" + evento); }
}
