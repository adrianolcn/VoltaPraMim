package br.ucsal.voltapramim.dominio.estado;

public final class AguardandoEntrega implements EstadoItem {
    @Override public String rotulo() { return "Aguardando entrega"; }
    @Override public boolean visivelNaBusca() { return true; }
    @Override public EstadoItem confirmarRecebimento() { return new DisponivelRetirada(); }
    @Override public EstadoItem registrarDevolucao() { throw EstadoItem.transicaoInvalida("O item precisa ser recebido antes da devolução."); }
}
