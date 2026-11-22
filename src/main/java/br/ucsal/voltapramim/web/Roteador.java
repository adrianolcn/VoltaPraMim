package br.ucsal.voltapramim.web;

import java.util.Map;
import java.util.Optional;

public final class Roteador {
    private final Map<ChaveRota, ComandoWeb> comandos;
    private final ComandoWeb detalhe;

    public Roteador(Map<ChaveRota, ComandoWeb> comandos, ComandoWeb detalhe) {
        this.comandos = Map.copyOf(comandos);
        this.detalhe = detalhe;
    }

    public Optional<ComandoWeb> resolver(String metodo, String caminho) {
        ComandoWeb comando = comandos.get(new ChaveRota(metodo, caminho));
        if (comando != null) return Optional.of(comando);
        if ("GET".equals(metodo) && caminho.startsWith("/itens/")) return Optional.of(detalhe);
        return Optional.empty();
    }
}
