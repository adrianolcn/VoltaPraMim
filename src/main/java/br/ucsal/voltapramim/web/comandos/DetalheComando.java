package br.ucsal.voltapramim.web.comandos;

import br.ucsal.voltapramim.aplicacao.ConsultarItem;
import br.ucsal.voltapramim.dominio.Item;
import br.ucsal.voltapramim.web.ComandoWeb;
import br.ucsal.voltapramim.web.Views;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

public final class DetalheComando implements ComandoWeb {
    private static final DateTimeFormatter DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm", Locale.forLanguageTag("pt-BR"))
            .withZone(ZoneId.of("America/Bahia"));
    private final ConsultarItem consultar;
    public DetalheComando(ConsultarItem consultar) { this.consultar = consultar; }
    @Override public void executar(HttpServletRequest request, HttpServletResponse response) throws jakarta.servlet.ServletException, IOException {
        String caminho = request.getRequestURI().substring(request.getContextPath().length());
        try {
            UUID id = UUID.fromString(caminho.substring("/itens/".length()));
            Item item = consultar.executar(id).orElse(null);
            if (item == null || !item.isVisivelNaBusca()) { response.sendError(HttpServletResponse.SC_NOT_FOUND); return; }
            request.setAttribute("item", item);
            request.setAttribute("dataEncontradoEm", DATA.format(item.getEncontradoEm()));
            Views.render(request, response, "detalhe");
        } catch (IllegalArgumentException erro) { response.sendError(HttpServletResponse.SC_NOT_FOUND); }
    }
}
