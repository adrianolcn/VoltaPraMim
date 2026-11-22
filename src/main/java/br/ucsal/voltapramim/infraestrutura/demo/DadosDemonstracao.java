package br.ucsal.voltapramim.infraestrutura.demo;

import br.ucsal.voltapramim.aplicacao.CadastrarItem;
import br.ucsal.voltapramim.dominio.Categoria;
import br.ucsal.voltapramim.dominio.LocalCampus;

public final class DadosDemonstracao {
    private DadosDemonstracao() { }
    public static void carregar(CadastrarItem cadastrar) {
        cadastrar.executar("Chaveiro azul com duas chaves", "Encontrado perto das mesas.", Categoria.CHAVES, LocalCampus.CANTINA);
        cadastrar.executar("Garrafa térmica verde", "Garrafa sem marca visível.", Categoria.OUTROS, LocalCampus.BIBLIOTECA);
        cadastrar.executar("Estojo preto", "Encontrado em uma sala de aula.", Categoria.ACESSORIOS, LocalCampus.SALA_DE_AULA);
    }
}
