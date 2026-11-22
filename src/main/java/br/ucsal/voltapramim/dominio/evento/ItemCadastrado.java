package br.ucsal.voltapramim.dominio.evento;

import java.time.Instant;
import java.util.UUID;

public record ItemCadastrado(UUID itemId, Instant ocorridoEm) implements EventoDominio { }
