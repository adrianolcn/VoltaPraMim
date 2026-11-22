package br.ucsal.voltapramim.web.comandos;

import br.ucsal.voltapramim.aplicacao.BuscarItens;
import br.ucsal.voltapramim.web.ComandoWeb;
import br.ucsal.voltapramim.web.Views;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class InicioComando implements ComandoWeb {
    private final BuscarItens buscar;
    public InicioComando(BuscarItens buscar) { this.buscar = buscar; }
    @Override public void executar(HttpServletRequest request, HttpServletResponse response) throws jakarta.servlet.ServletException, IOException {
        request.setAttribute("itens", buscar.executar("", null, null));
        Views.render(request, response, "inicio");
    }
}
