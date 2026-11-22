package br.ucsal.voltapramim.dominio.estado;

import br.ucsal.voltapramim.dominio.TransicaoInvalidaException;

public interface EstadoItem {
    String rotulo();
    boolean visivelNaBusca();
    EstadoItem confirmarRecebimento();
    EstadoItem registrarDevolucao();

    static TransicaoInvalidaException transicaoInvalida(String mensagem) {
        return new TransicaoInvalidaException(mensagem);
    }
}
