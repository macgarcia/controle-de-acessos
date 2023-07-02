package com.github.macgarcia.api.dto;

public record IntegracaoResponse(int codigo, String metodo, String erro) {

	public IntegracaoResponse(int codigo, String metodo, String erro) {
		this.codigo = codigo;
		this.metodo = metodo;
		this.erro = erro;
	}

}
