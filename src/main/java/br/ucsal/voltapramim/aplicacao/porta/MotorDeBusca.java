package br.ucsal.voltapramim.aplicacao.porta;

import br.ucsal.voltapramim.aplicacao.ConsultaItens;
import br.ucsal.voltapramim.dominio.Item;
import java.util.List;

public interface MotorDeBusca {
    List<Item> buscar(List<Item> itens, ConsultaItens consulta);
}
