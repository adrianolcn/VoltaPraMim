package br.ucsal.voltapramim.aplicacao;

import br.ucsal.voltapramim.dominio.Categoria;
import br.ucsal.voltapramim.dominio.LocalCampus;

public record ConsultaItens(String texto, Categoria categoria, LocalCampus local) {
    public ConsultaItens { texto = texto == null ? "" : texto.trim(); }
}
