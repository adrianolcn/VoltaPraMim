package br.ucsal.voltapramim.aplicacao.porta;

import java.util.Arrays;
import java.util.Objects;

public record FotoArmazenada(
        byte[] conteudo,
        String contentType
) {
    public FotoArmazenada {
        Objects.requireNonNull(conteudo, "O conteúdo da foto é obrigatório.");
        Objects.requireNonNull(contentType, "O tipo da foto é obrigatório.");
        conteudo = Arrays.copyOf(conteudo, conteudo.length);
    }

    @Override
    public byte[] conteudo() {
        return Arrays.copyOf(conteudo, conteudo.length);
    }
}