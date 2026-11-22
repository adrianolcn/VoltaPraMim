package br.ucsal.voltapramim.aplicacao.porta;

import br.ucsal.voltapramim.dominio.evento.EventoDominio;

public interface OuvinteDeEvento {
    void aoReceber(EventoDominio evento);
}
