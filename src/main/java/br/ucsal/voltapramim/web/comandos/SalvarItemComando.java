package br.ucsal.voltapramim.web.comandos;

import br.ucsal.voltapramim.aplicacao.CadastrarItem;
import br.ucsal.voltapramim.aplicacao.porta.FotoUpload;
import br.ucsal.voltapramim.dominio.Categoria;
import br.ucsal.voltapramim.dominio.Item;
import br.ucsal.voltapramim.dominio.LocalCampus;
import br.ucsal.voltapramim.web.ComandoWeb;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

public final class SalvarItemComando implements ComandoWeb {
    private static final long TAMANHO_MAXIMO_FOTO = 5L * 1024 * 1024;

    private final CadastrarItem cadastrar;

    public SalvarItemComando(CadastrarItem cadastrar) {
        this.cadastrar = cadastrar;
    }

    @Override
    public void executar(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            String titulo = request.getParameter("titulo");
            String descricao = request.getParameter("descricao");
            request.setAttribute("titulo", titulo == null ? "" : titulo);
            request.setAttribute("descricao", descricao == null ? "" : descricao);
            Categoria categoria =
                    Categoria.de(request.getParameter("categoria"));

            LocalCampus local =
                    LocalCampus.de(request.getParameter("local"));

            FotoUpload foto = obterFoto(request);

            Item item = cadastrar.executar(
                    titulo,
                    descricao,
                    categoria,
                    local,
                    foto
            );

            response.sendRedirect(
                    request.getContextPath() + "/itens/" + item.getId()
            );
        } catch (IllegalArgumentException erro) {
            CadastroComando.mostrarErro(
                    request,
                    response,
                    erro.getMessage()
            );
        }
    }

    private FotoUpload obterFoto(HttpServletRequest request)
            throws IOException, ServletException {
        try {
            Part parteFoto = request.getPart("foto");

            if (parteFoto == null || parteFoto.getSize() == 0) {
                return null;
            }

            if (parteFoto.getSize() > TAMANHO_MAXIMO_FOTO) {
                throw new IllegalArgumentException(
                        "A foto deve ter no máximo 5 MiB."
                );
            }

            return new FotoUpload(
                    parteFoto.getInputStream().readAllBytes(),
                    parteFoto.getContentType()
            );
        } catch (IllegalStateException erro) {
            throw new IllegalArgumentException(
                    "A foto deve ter no máximo 5 MiB."
            );
        }
    }
}
