package br.ucsal.voltapramim.dominio;

public enum LocalCampus {
    BIBLIOTECA("Biblioteca"), CANTINA("Cantina"), SALA_DE_AULA("Sala de aula"),
    LABORATORIO("Laboratório"), SECRETARIA("Secretaria"), AREA_EXTERNA("Área externa"), OUTRO("Outro local");

    private final String rotulo;
    LocalCampus(String rotulo) { this.rotulo = rotulo; }
    public String getRotulo() { return rotulo; }

    public static LocalCampus de(String valor) {
        if (valor == null || valor.isBlank()) throw new IllegalArgumentException("Selecione um local.");
        try { return valueOf(valor); }
        catch (IllegalArgumentException erro) { throw new IllegalArgumentException("Local inválido."); }
    }
}
