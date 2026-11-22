package br.ucsal.voltapramim.web.comandos;

import br.ucsal.voltapramim.aplicacao.BuscarItens;
import br.ucsal.voltapramim.dominio.Categoria;
import br.ucsal.voltapramim.dominio.LocalCampus;
import br.ucsal.voltapramim.web.ComandoWeb;
import br.ucsal.voltapramim.web.Views;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class BuscaComando implements ComandoWeb {
    private final BuscarItens buscar;
    public BuscaComando(BuscarItens buscar) { this.buscar = buscar; }
    @Override public void executar(HttpServletRequest request, HttpServletResponse response) throws jakarta.servlet.ServletException, IOException {
        String texto = request.getParameter("q");
        String categoria = request.getParameter("categoria");
        String local = request.getParameter("local");
        request.setAttribute("texto", texto == null ? "" : texto);
        request.setAttribute("categoriaSelecionada", categoria == null ? "" : categoria);
        request.setAttribute("localSelecionado", local == null ? "" : local);
        request.setAttribute("categorias", Categoria.values());
        request.setAttribute("locais", LocalCampus.values());
        request.setAttribute("itens", buscar.executar(texto, categoria, local));
        Views.render(request, response, "busca");
    }
}
