package com.github.macgarcia.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.macgarcia.api.dto.HistoricoDtoEntrada;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "HISTORICO_NOTA")
@SequenceGenerator(name = "historico_nota_sequence", sequenceName = "historico_nota_sequence", allocationSize = 1, initialValue = 1)
public class HistoricoNota implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historico_nota_sequence")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_nota")
	@NotNull(message = "Nota de referencia é obrigatória")
	@JsonIgnore
	private Nota nota;

	@Column(name = "data_validade_inicial")
	@NotNull(message = "Data de validade inicial é obrigatório")
	private LocalDateTime dataValidadeInicial;
	
	@Column(name = "data_validade_final")
	@NotNull(message = "Data de validade final é obrigatório")
	private LocalDateTime dataValidadeFinal;
	
	@Column(name = "numero_atualizacao")
	@NotNull(message = "Numero de ordem de atualização é obrigatório")
	private Integer numeroAtualizacao;
	
	@Column(name = "descricao")
	private String descricao;

	@Column(name = "titulo")
	@NotNull(message = "Titulo é obrigatório")
	private String titulo;

	@Column(name = "data_criacao")
	@NotNull(message = "Data de criação é obrigatório")
	private LocalDateTime dataCriacao;

	@Column(name = "usuario")
	@NotNull(message = "Usuário é obrigatório")
	private String usuario;

	@Column(name = "senha")
	@NotNull(message = "Senha é obrigatório")
	private String senha;

	@Column(name = "url_site")
	private String urlSite;
	
	
	public HistoricoNota() {
	}
	
	public HistoricoNota(final HistoricoDtoEntrada dto) {
		this.dataValidadeInicial = dto.dataValidadeInicial();
		this.dataValidadeFinal = dto.dataValidadeFinal();
		this.numeroAtualizacao = dto.numeroAtualizacao();
		this.descricao = dto.descricao();
		this.titulo = dto.titulo();
		this.dataCriacao = dto.dataCriacao();
		this.usuario = dto.usuario();
		this.senha = dto.senha();
		this.urlSite = dto.urlSite();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public LocalDateTime getDataValidadeInicial() {
		return dataValidadeInicial;
	}

	public void setDataValidadeInicial(LocalDateTime dataValidadeInicial) {
		this.dataValidadeInicial = dataValidadeInicial;
	}

	public LocalDateTime getDataValidadeFinal() {
		return dataValidadeFinal;
	}

	public void setDataValidadeFinal(LocalDateTime dataValidadeFinal) {
		this.dataValidadeFinal = dataValidadeFinal;
	}

	public Integer getNumeroAtualizacao() {
		return numeroAtualizacao;
	}

	public void setNumeroAtualizacao(Integer numeroAtualizacao) {
		this.numeroAtualizacao = numeroAtualizacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUrlSite() {
		return urlSite;
	}

	public void setUrlSite(String urlSite) {
		this.urlSite = urlSite;
	}
}
