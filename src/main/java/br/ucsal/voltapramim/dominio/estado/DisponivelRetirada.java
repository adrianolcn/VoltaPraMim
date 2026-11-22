package br.ucsal.voltapramim.dominio.estado;

public final class DisponivelRetirada implements EstadoItem {
    @Override public String rotulo() { return "Disponível para retirada"; }
    @Override public boolean visivelNaBusca() { return true; }
    @Override public EstadoItem confirmarRecebimento() { throw EstadoItem.transicaoInvalida("O item já foi recebido."); }
    @Override public EstadoItem registrarDevolucao() { return new Devolvido(); }
}
