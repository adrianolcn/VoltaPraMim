package br.ucsal.voltapramim.aplicacao.porta;

import java.util.Optional;

public interface ArmazenamentoDeFotos {
    String salvar(FotoUpload foto);

    void remover(String fotoId);

    Optional<FotoArmazenada> buscarPorId(String fotoId);
}
