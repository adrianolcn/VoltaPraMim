package br.ucsal.voltapramim.aplicacao.porta;

import br.ucsal.voltapramim.dominio.evento.EventoDominio;

public interface PublicadorDeEventos {
    void inscrever(OuvinteDeEvento ouvinte);
    void publicar(EventoDominio evento);
}
