package com.github.macgarcia.api.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record HistoricoDtoEntrada(@NotNull LocalDateTime dataValidadeInicial,
		@NotNull LocalDateTime dataValidadeFinal,
		@NotNull Integer numeroAtualizacao,
		String descricao,
		@NotNull String titulo,
		@NotNull LocalDateTime dataCriacao,
		@NotNull String usuario,
		@NotNull String senha,
		String urlSite
		) {

}
