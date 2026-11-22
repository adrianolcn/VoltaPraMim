package br.ucsal.voltapramim.web.comandos;

import br.ucsal.voltapramim.dominio.Categoria;
import br.ucsal.voltapramim.dominio.LocalCampus;
import br.ucsal.voltapramim.web.Views;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

final class CadastroComando {
    private CadastroComando() { }
    static void mostrarErro(HttpServletRequest request, HttpServletResponse response, String mensagem)
            throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        request.setAttribute("erro", mensagem);
        request.setAttribute("categorias", Categoria.values());
        request.setAttribute("locais", LocalCampus.values());
        Views.render(request, response, "cadastro");
    }
}
