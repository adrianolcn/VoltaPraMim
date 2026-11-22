package br.ucsal.voltapramim.web.comandos;

import br.ucsal.voltapramim.dominio.Categoria;
import br.ucsal.voltapramim.dominio.LocalCampus;
import br.ucsal.voltapramim.web.ComandoWeb;
import br.ucsal.voltapramim.web.Views;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class NovoItemComando implements ComandoWeb {
    @Override public void executar(HttpServletRequest request, HttpServletResponse response) throws jakarta.servlet.ServletException, IOException {
        request.setAttribute("categorias", Categoria.values());
        request.setAttribute("locais", LocalCampus.values());
        Views.render(request, response, "cadastro");
    }
}
