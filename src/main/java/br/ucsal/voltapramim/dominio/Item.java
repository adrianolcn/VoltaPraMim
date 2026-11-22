package br.ucsal.voltapramim.dominio;

import br.ucsal.voltapramim.dominio.estado.AguardandoEntrega;
import br.ucsal.voltapramim.dominio.estado.EstadoItem;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class Item {
    private final UUID id = UUID.randomUUID();
    private final String referenciaPublica;
    private final String titulo;
    private final String descricao;
    private final Categoria categoria;
    private final LocalCampus local;
    private final Instant encontradoEm = Instant.now();
    private EstadoItem estado = new AguardandoEntrega();

    public Item(String referenciaPublica, String titulo, String descricao, Categoria categoria, LocalCampus local) {
        this.referenciaPublica = Objects.requireNonNull(referenciaPublica);
        this.titulo = titulo == null ? "" : titulo.trim();
        if (this.titulo.isBlank()) throw new IllegalArgumentException("Informe o nome do objeto.");
        if (this.titulo.length() > 80) throw new IllegalArgumentException("O nome deve ter até 80 caracteres.");
        this.descricao = descricao == null ? "" : descricao.trim();
        if (this.descricao.length() > 500) throw new IllegalArgumentException("A descrição deve ter até 500 caracteres.");
        this.categoria = Objects.requireNonNull(categoria);
        this.local = Objects.requireNonNull(local);
    }

    public void confirmarRecebimento() { estado = estado.confirmarRecebimento(); }
    public void registrarDevolucao() { estado = estado.registrarDevolucao(); }
    public boolean isVisivelNaBusca() { return estado.visivelNaBusca(); }
    public UUID getId() { return id; }
    public String getReferenciaPublica() { return referenciaPublica; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public Categoria getCategoria() { return categoria; }
    public LocalCampus getLocal() { return local; }
    public Instant getEncontradoEm() { return encontradoEm; }
    public String getEstadoRotulo() { return estado.rotulo(); }
}
