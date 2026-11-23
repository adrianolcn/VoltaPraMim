package br.ucsal.voltapramim.web.comandos;

import br.ucsal.voltapramim.aplicacao.porta.ArmazenamentoDeFotos;
import br.ucsal.voltapramim.aplicacao.porta.FotoArmazenada;
import br.ucsal.voltapramim.web.ComandoWeb;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class FotoComando implements ComandoWeb {
    private final ArmazenamentoDeFotos fotos;

    public FotoComando(ArmazenamentoDeFotos fotos) {
        this.fotos = fotos;
    }

    @Override
    public void executar(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String caminho = request.getRequestURI()
                .substring(request.getContextPath().length());

        String fotoId = caminho.substring("/fotos/".length());

        FotoArmazenada foto = fotos.buscarPorId(fotoId).orElse(null);

        if (foto == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType(foto.contentType());
        response.setContentLength(foto.conteudo().length);
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.getOutputStream().write(foto.conteudo());
    }
}