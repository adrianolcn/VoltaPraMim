package br.ucsal.voltapramim.dominio;

public final class TransicaoInvalidaException extends IllegalStateException {
    public TransicaoInvalidaException(String mensagem) { super(mensagem); }
}
