package br.ucsal.voltapramim.dominio;

public enum Categoria {
    CELULAR("Eletrônicos"), CHAVES("Chaves"), DOCUMENTOS("Documentos"),
    BOLSAS("Bolsas e mochilas"), ACESSORIOS("Acessórios"), OUTROS("Outros");

    private final String rotulo;
    Categoria(String rotulo) { this.rotulo = rotulo; }
    public String getRotulo() { return rotulo; }
    public boolean aceitaFoto() { return this != DOCUMENTOS; }

    public static Categoria de(String valor) {
        if (valor == null || valor.isBlank()) throw new IllegalArgumentException("Selecione uma categoria.");
        try { return valueOf(valor); }
        catch (IllegalArgumentException erro) { throw new IllegalArgumentException("Categoria inválida."); }
    }
}
