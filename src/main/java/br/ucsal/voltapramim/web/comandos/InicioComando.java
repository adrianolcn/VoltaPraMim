package br.ucsal.voltapramim.web.comandos;

import br.ucsal.voltapramim.aplicacao.BuscarItens;
import br.ucsal.voltapramim.web.ComandoWeb;
import br.ucsal.voltapramim.dominio.Categoria;
import br.ucsal.voltapramim.dominio.Item;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class InicioComando implements ComandoWeb {
    private final BuscarItens buscar;
    public InicioComando(BuscarItens buscar) { this.buscar = buscar; }
    @Override public void executar(HttpServletRequest request, HttpServletResponse response) throws jakarta.servlet.ServletException, IOException {
        List<Item> itens = buscar.executar("", null, null);
        request.setAttribute("itens", itens);
        request.setAttribute("categorias", Categoria.values());
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }
}
