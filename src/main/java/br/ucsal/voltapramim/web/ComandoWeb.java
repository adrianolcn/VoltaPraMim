package br.ucsal.voltapramim.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@FunctionalInterface
public interface ComandoWeb {
    void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
