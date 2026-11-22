package br.ucsal.voltapramim.aplicacao;

import br.ucsal.voltapramim.aplicacao.porta.MotorDeBusca;
import br.ucsal.voltapramim.aplicacao.porta.RepositorioDeItens;
import br.ucsal.voltapramim.dominio.Categoria;
import br.ucsal.voltapramim.dominio.Item;
import br.ucsal.voltapramim.dominio.LocalCampus;
import java.util.List;

public final class BuscarItens {
    private final RepositorioDeItens repositorio;
    private final MotorDeBusca porRelevancia;
    private final MotorDeBusca recentes;

    public BuscarItens(RepositorioDeItens repositorio, MotorDeBusca porRelevancia, MotorDeBusca recentes) {
        this.repositorio = repositorio;
        this.porRelevancia = porRelevancia;
        this.recentes = recentes;
    }

    public List<Item> executar(String texto, String categoria, String local) {
        Categoria categoriaFiltro = categoria == null || categoria.isBlank() ? null : Categoria.de(categoria);
        LocalCampus localFiltro = local == null || local.isBlank() ? null : LocalCampus.de(local);
        ConsultaItens consulta = new ConsultaItens(texto, categoriaFiltro, localFiltro);
        MotorDeBusca estrategia = consulta.texto().isBlank() ? recentes : porRelevancia;
        return estrategia.buscar(repositorio.listarAtivos(), consulta);
    }
}
