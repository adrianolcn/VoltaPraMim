package br.ucsal.voltapramim.infraestrutura.busca;

import br.ucsal.voltapramim.aplicacao.ConsultaItens;
import br.ucsal.voltapramim.aplicacao.porta.MotorDeBusca;
import br.ucsal.voltapramim.dominio.Item;

import java.util.Comparator;
import java.util.List;

public final class BuscaRecentes implements MotorDeBusca {

    @Override
    public List<Item> buscar(
            List<Item> itens,
            ConsultaItens consulta
    ) {
        return itens.stream()
                .filter(Item::isVisivelNaBusca)
                .filter(item -> aceitaCategoria(item, consulta))
                .filter(item -> aceitaLocal(item, consulta))
                .sorted(
                        Comparator.comparing(Item::getEncontradoEm)
                                .reversed()
                )
                .toList();
    }

    private boolean aceitaCategoria(
            Item item,
            ConsultaItens consulta
    ) {
        return consulta.categoria() == null
                || item.getCategoria() == consulta.categoria();
    }

    private boolean aceitaLocal(
            Item item,
            ConsultaItens consulta
    ) {
        return consulta.local() == null
                || item.getLocal() == consulta.local();
    }
}