package br.ucsal.voltapramim.infraestrutura.busca;

import br.ucsal.voltapramim.aplicacao.ConsultaItens;
import br.ucsal.voltapramim.aplicacao.porta.MotorDeBusca;
import br.ucsal.voltapramim.dominio.Categoria;
import br.ucsal.voltapramim.dominio.Item;
import br.ucsal.voltapramim.dominio.LocalCampus;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuscaComSinonimosTest {
    @Test
    void celularEncontraSmartphoneSemDuplicarResultados() {
        Item celular = new Item("VPM-AAAAA", "Celular preto", "", Categoria.CELULAR,
                LocalCampus.BIBLIOTECA);
        Item smartphone = new Item("VPM-BBBBB", "Smartphone azul", "", Categoria.CELULAR,
                LocalCampus.BIBLIOTECA);
        Item ambosTermos = new Item("VPM-CCCCC", "Celular smartphone híbrido", "", Categoria.CELULAR,
                LocalCampus.BIBLIOTECA);
        MotorDeBusca busca = new BuscaComSinonimos(new BuscaPorRelevancia());

        List<Item> resultado = busca.buscar(
                List.of(celular, smartphone, ambosTermos), new ConsultaItens("celular", null, null));

        assertEquals(3, resultado.size());
        assertEquals(Set.of(celular.getId(), smartphone.getId(), ambosTermos.getId()),
                resultado.stream().map(Item::getId).collect(Collectors.toSet()));
    }

    @Test
    void sinonimoPreservaFiltrosDaConsulta() {
        Item smartphone = new Item("VPM-AAAAA", "Smartphone azul", "", Categoria.CELULAR,
                LocalCampus.BIBLIOTECA);
        Item outraCategoria = new Item("VPM-BBBBB", "Smartphone azul", "", Categoria.BOLSAS,
                LocalCampus.BIBLIOTECA);
        MotorDeBusca busca = new BuscaComSinonimos(new BuscaPorRelevancia());

        List<Item> resultado = busca.buscar(
                List.of(smartphone, outraCategoria),
                new ConsultaItens("celular", Categoria.CELULAR, LocalCampus.BIBLIOTECA));

        assertEquals(List.of(smartphone), resultado);
    }
}
