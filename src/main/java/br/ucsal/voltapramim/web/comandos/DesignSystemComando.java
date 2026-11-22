package br.ucsal.voltapramim.web.comandos;

import br.ucsal.voltapramim.web.ComandoWeb;
import br.ucsal.voltapramim.web.Views;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class DesignSystemComando implements ComandoWeb {
    @Override public void executar(HttpServletRequest request, HttpServletResponse response) throws jakarta.servlet.ServletException, IOException {
        Views.render(request, response, "design-system");
    }
}
