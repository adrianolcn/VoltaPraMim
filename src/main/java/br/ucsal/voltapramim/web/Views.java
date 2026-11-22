package br.ucsal.voltapramim.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class Views {
    private Views() { }
    public static void render(HttpServletRequest request, HttpServletResponse response, String pagina)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/" + pagina + ".jsp").forward(request, response);
    }
}
