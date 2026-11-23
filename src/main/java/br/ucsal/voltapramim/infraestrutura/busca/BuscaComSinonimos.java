package br.ucsal.voltapramim.infraestrutura.busca;

import br.ucsal.voltapramim.aplicacao.ConsultaItens;
import br.ucsal.voltapramim.aplicacao.porta.MotorDeBusca;
import br.ucsal.voltapramim.dominio.Item;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/** Acrescenta consultas equivalentes sem alterar o algoritmo de busca envolvido. */
public final class BuscaComSinonimos implements MotorDeBusca {
    private static final Map<String, List<String>> SINONIMOS = Map.of(
            "celular", List.of("smartphone"),
            "smartphone", List.of("celular")
    );

    private final MotorDeBusca motor;

    public BuscaComSinonimos(MotorDeBusca motor) {
        this.motor = motor;
    }

    @Override
    public List<Item> buscar(List<Item> itens, ConsultaItens consulta) {
        String[] termosOriginais = consulta.texto().split("\\s+");
        List<String> termosExpandidos = new ArrayList<>(Arrays.asList(termosOriginais));
        Set<String> termosIncluidos = new HashSet<>();
        for (String termo : termosOriginais) {
            termosIncluidos.add(normalizar(termo));
        }

        for (String termo : termosOriginais) {
            for (String sinonimo : SINONIMOS.getOrDefault(normalizar(termo), List.of())) {
                if (termosIncluidos.add(normalizar(sinonimo))) {
                    termosExpandidos.add(sinonimo);
                }
            }
        }

        ConsultaItens consultaExpandida = new ConsultaItens(
                String.join(" ", termosExpandidos), consulta.categoria(), consulta.local());
        return motor.buscar(itens, consultaExpandida);
    }

    private String normalizar(String texto) {
        return Normalizer.normalize(texto == null ? "" : texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}+", "")
                .toLowerCase(Locale.ROOT);
    }
}
