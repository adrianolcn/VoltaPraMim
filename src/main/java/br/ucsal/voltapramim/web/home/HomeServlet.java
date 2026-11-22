package br.ucsal.voltapramim.web.home;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public final class HomeServlet extends HttpServlet {
    private final ItemRepository repository = new ItemRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Item> items = repository.findAll();
        request.setAttribute("items", items);
        request.setAttribute("itemCount", items.size());
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }
}
