package com.github.macgarcia.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public record NotaDtoEntrada(
		@NotNull Long idIntegracao,
		String descricao,
		@NotNull String titulo,
		@NotNull LocalDateTime dataCriacao,
		@NotNull String usuario,
		@NotNull String senha,
		String urlSite,
		LocalDateTime dataAtualizacao,
		List<HistoricoDtoEntrada> historico) {
}
