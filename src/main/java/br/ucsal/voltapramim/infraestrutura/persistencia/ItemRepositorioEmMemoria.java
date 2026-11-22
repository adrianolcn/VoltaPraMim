package br.ucsal.voltapramim.infraestrutura.persistencia;

import br.ucsal.voltapramim.aplicacao.porta.RepositorioDeItens;
import br.ucsal.voltapramim.dominio.Item;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class ItemRepositorioEmMemoria implements RepositorioDeItens {
    private final ConcurrentHashMap<UUID, Item> itens = new ConcurrentHashMap<>();
    @Override public void salvar(Item item) { itens.put(item.getId(), item); }
    @Override public Optional<Item> buscarPorId(UUID id) { return Optional.ofNullable(itens.get(id)); }
    @Override public List<Item> listarAtivos() {
        return itens.values().stream().filter(Item::isVisivelNaBusca)
                .sorted(Comparator.comparing(Item::getEncontradoEm).reversed()).toList();
    }
    @Override public boolean existeReferencia(String referencia) {
        return itens.values().stream().anyMatch(item -> item.getReferenciaPublica().equals(referencia));
    }
}
