package br.ucsal.voltapramim.web.comandos;

import br.ucsal.voltapramim.aplicacao.CadastrarItem;
import br.ucsal.voltapramim.dominio.Categoria;
import br.ucsal.voltapramim.dominio.Item;
import br.ucsal.voltapramim.dominio.LocalCampus;
import br.ucsal.voltapramim.web.ComandoWeb;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class SalvarItemComando implements ComandoWeb {
    private final CadastrarItem cadastrar;
    public SalvarItemComando(CadastrarItem cadastrar) { this.cadastrar = cadastrar; }
    @Override public void executar(HttpServletRequest request, HttpServletResponse response) throws jakarta.servlet.ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        request.setAttribute("titulo", titulo == null ? "" : titulo);
        request.setAttribute("descricao", descricao == null ? "" : descricao);
        try {
            Categoria categoria = Categoria.de(request.getParameter("categoria"));
            LocalCampus local = LocalCampus.de(request.getParameter("local"));
            Item item = cadastrar.executar(titulo, descricao, categoria, local);
            response.sendRedirect(request.getContextPath() + "/itens/" + item.getId());
        } catch (IllegalArgumentException erro) {
            CadastroComando.mostrarErro(request, response, erro.getMessage());
        }
    }
}
