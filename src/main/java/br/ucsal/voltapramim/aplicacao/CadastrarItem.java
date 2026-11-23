package br.ucsal.voltapramim.aplicacao;

import br.ucsal.voltapramim.aplicacao.porta.ArmazenamentoDeFotos;
import br.ucsal.voltapramim.aplicacao.porta.FotoUpload;
import br.ucsal.voltapramim.aplicacao.porta.PublicadorDeEventos;
import br.ucsal.voltapramim.aplicacao.porta.RepositorioDeItens;
import br.ucsal.voltapramim.dominio.Categoria;
import br.ucsal.voltapramim.dominio.Item;
import br.ucsal.voltapramim.dominio.LocalCampus;
import br.ucsal.voltapramim.dominio.evento.ItemCadastrado;

import java.time.Instant;
import java.util.UUID;

public final class CadastrarItem {
    private static final String ALFABETO =
            "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";

    private final RepositorioDeItens repositorio;
    private final PublicadorDeEventos eventos;
    private final ArmazenamentoDeFotos fotos;

    public CadastrarItem(
            RepositorioDeItens repositorio,
            PublicadorDeEventos eventos,
            ArmazenamentoDeFotos fotos
    ) {
        this.repositorio = repositorio;
        this.eventos = eventos;
        this.fotos = fotos;
    }

    public Item executar(
            String titulo,
            String descricao,
            Categoria categoria,
            LocalCampus local
    ) {
        return executar(titulo, descricao, categoria, local, null);
    }

    public Item executar(
            String titulo,
            String descricao,
            Categoria categoria,
            LocalCampus local,
            FotoUpload foto
    ) {
        if (foto != null && !categoria.aceitaFoto()) {
            throw new IllegalArgumentException(
                    "Documentos não podem ter foto."
            );
        }

        String referencia;
        do {
            referencia = gerarReferencia();
        } while (repositorio.existeReferencia(referencia));

        Item item = new Item(
                referencia,
                titulo,
                descricao,
                categoria,
                local
        );

        String fotoId = foto == null ? null : fotos.salvar(foto);
        if (fotoId != null) item.associarFoto(fotoId);
        try {
            repositorio.salvar(item);
        } catch (RuntimeException erro) {
            if (fotoId != null) {
                try { fotos.remover(fotoId); }
                catch (RuntimeException limpeza) { erro.addSuppressed(limpeza); }
            }
            throw erro;
        }

        eventos.publicar(
                new ItemCadastrado(item.getId(), Instant.now())
        );

        return item;
    }

    private String gerarReferencia() {
        String hex = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .toUpperCase();

        StringBuilder codigo = new StringBuilder("VPM-");

        for (int i = 0; i < 5; i++) {
            int indice = Integer.parseInt(
                    hex.substring(i * 2, i * 2 + 2),
                    16
            ) % ALFABETO.length();

            codigo.append(ALFABETO.charAt(indice));
        }

        return codigo.toString();
    }
}