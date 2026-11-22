package br.ucsal.voltapramim.infraestrutura.eventos;

import br.ucsal.voltapramim.aplicacao.porta.OuvinteDeEvento;
import br.ucsal.voltapramim.dominio.evento.EventoDominio;
import java.util.concurrent.atomic.LongAdder;

public final class EstatisticasObserver implements OuvinteDeEvento {
    private final LongAdder cadastros = new LongAdder();
    @Override public void aoReceber(EventoDominio evento) { cadastros.increment(); }
    public long totalDeCadastros() { return cadastros.sum(); }
}
