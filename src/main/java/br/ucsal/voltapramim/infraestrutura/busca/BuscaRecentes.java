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
                .filter(item -> aceitaFiltros(item, consulta))
                .sorted(
                        Comparator.comparing(Item::getEncontradoEm)
                                .reversed()
                )
                .toList();
    }

    static boolean aceitaFiltros(Item item, ConsultaItens consulta) {
        return (consulta.categoria() == null || item.getCategoria() == consulta.categoria())
                && (consulta.local() == null || item.getLocal() == consulta.local());
    }
}
