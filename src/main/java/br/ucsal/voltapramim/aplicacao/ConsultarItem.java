package br.ucsal.voltapramim.aplicacao;

import br.ucsal.voltapramim.aplicacao.porta.RepositorioDeItens;
import br.ucsal.voltapramim.dominio.Item;
import java.util.Optional;
import java.util.UUID;

public final class ConsultarItem {
    private final RepositorioDeItens repositorio;
    public ConsultarItem(RepositorioDeItens repositorio) { this.repositorio = repositorio; }
    public Optional<Item> executar(UUID id) { return repositorio.buscarPorId(id); }
}
