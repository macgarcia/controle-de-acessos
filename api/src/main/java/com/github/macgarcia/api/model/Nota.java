package com.github.macgarcia.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.github.macgarcia.api.dto.NotaDtoEntrada;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "NOTA")
@SequenceGenerator(name = "nota_sequence", sequenceName = "nota_sequence", allocationSize = 1, initialValue = 1)
public class Nota implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_sequence")
	private Long id;

	@Column(name = "id_integracao")
	@NotNull(message = "Identificador de integração é obrigatório")
	private Long idIntegracao;

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

	@Column(name = "data_atualizacao")
	private LocalDateTime dataAtualizacao;

	@OneToMany(mappedBy = "nota", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<HistoricoNota> historico;

	public Nota() {
	}
	
	public Nota(final NotaDtoEntrada dto) {
		this.idIntegracao = dto.idIntegracao();
		this.descricao = dto.descricao();
		this.titulo = dto.titulo();
		this.dataCriacao = dto.dataCriacao();
		this.usuario = dto.usuario();
		this.senha = dto.senha();
		this.urlSite = dto.urlSite();
	}
	
	public void atualizarNota(final NotaDtoEntrada dto) {
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

	public Long getIdIntegracao() {
		return idIntegracao;
	}

	public void setIdIntegracao(Long idIntegracao) {
		this.idIntegracao = idIntegracao;
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

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public List<HistoricoNota> getHistorico() {
		if (this.historico == null) {
			this.historico = new ArrayList<>();
		}
		return historico;
	}

	public void setHistorico(List<HistoricoNota> historico) {
		this.historico = historico;
	}

}
