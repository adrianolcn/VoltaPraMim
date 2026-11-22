package br.ucsal.voltapramim.aplicacao.porta;

import br.ucsal.voltapramim.dominio.Item;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositorioDeItens {
    void salvar(Item item);
    Optional<Item> buscarPorId(UUID id);
    List<Item> listarAtivos();
    boolean existeReferencia(String referencia);
}
