package br.ucsal.voltapramim.infraestrutura.busca;

import br.ucsal.voltapramim.aplicacao.ConsultaItens;
import br.ucsal.voltapramim.aplicacao.porta.MotorDeBusca;
import br.ucsal.voltapramim.dominio.Item;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class BuscaPorRelevancia implements MotorDeBusca {
    @Override public List<Item> buscar(List<Item> itens, ConsultaItens consulta) {
        String[] termos = normalizar(consulta.texto()).split("\\s+");
        return itens.stream().filter(item -> BuscaRecentes.aceitaFiltros(item, consulta))
                .map(item -> new Resultado(item, pontuar(item, termos)))
                .filter(resultado -> resultado.pontos > 0)
                .sorted((a, b) -> Integer.compare(b.pontos, a.pontos))
                .map(Resultado::item).toList();
    }
    private int pontuar(Item item, String[] termos) {
        String titulo = normalizar(item.getTitulo());
        String descricao = normalizar(item.getDescricao());
        String categoria = normalizar(item.getCategoria().getRotulo());
        String local = normalizar(item.getLocal().getRotulo());
        return Arrays.stream(termos).mapToInt(termo -> {
            int pontos = titulo.contains(termo) ? 4 : 0;
            if (descricao.contains(termo)) pontos += 2;
            if (categoria.contains(termo) || local.contains(termo)) pontos++;
            return pontos;
        }).sum();
    }
    private String normalizar(String texto) {
        return Normalizer.normalize(texto == null ? "" : texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}+", "").toLowerCase(Locale.ROOT);
    }
    private record Resultado(Item item, int pontos) { }
}
