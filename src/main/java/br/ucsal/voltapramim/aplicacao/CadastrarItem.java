package br.ucsal.voltapramim.aplicacao;

import br.ucsal.voltapramim.aplicacao.porta.PublicadorDeEventos;
import br.ucsal.voltapramim.aplicacao.porta.RepositorioDeItens;
import br.ucsal.voltapramim.dominio.Categoria;
import br.ucsal.voltapramim.dominio.Item;
import br.ucsal.voltapramim.dominio.LocalCampus;
import br.ucsal.voltapramim.dominio.evento.ItemCadastrado;

import java.time.Instant;
import java.util.UUID;

public final class CadastrarItem {
    private static final String ALFABETO = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private final RepositorioDeItens repositorio;
    private final PublicadorDeEventos eventos;

    public CadastrarItem(RepositorioDeItens repositorio, PublicadorDeEventos eventos) {
        this.repositorio = repositorio;
        this.eventos = eventos;
    }

    public Item executar(String titulo, String descricao, Categoria categoria, LocalCampus local) {
        String referencia;
        do { referencia = gerarReferencia(); } while (repositorio.existeReferencia(referencia));
        Item item = new Item(referencia, titulo, descricao, categoria, local);
        repositorio.salvar(item);
        eventos.publicar(new ItemCadastrado(item.getId(), Instant.now()));
        return item;
    }

    private String gerarReferencia() {
        String hex = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        StringBuilder codigo = new StringBuilder("VPM-");
        for (int i = 0; i < 5; i++) {
            int indice = Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16) % ALFABETO.length();
            codigo.append(ALFABETO.charAt(indice));
        }
        return codigo.toString();
    }
}
