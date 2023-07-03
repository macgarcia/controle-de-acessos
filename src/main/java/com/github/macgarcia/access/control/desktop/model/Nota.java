package com.github.macgarcia.access.control.desktop.model;

import com.github.macgarcia.access.control.desktop.repository.EntidadeBase;
import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author macgarcia
 */
@Entity
@Table(name = "nota")
public class Nota implements Serializable, EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "descricao", nullable = true)
    private String descricao;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "usuario", nullable = false)
    private String usuario;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "senha", nullable = false)
    private String senha;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "url_site", nullable = true)
    private String urlSite;

    @Expose(serialize = true, deserialize = true)
    @Column(name = "data_atualizacao", nullable = true)
    private LocalDateTime dataAtualizacao;

    @Expose(serialize = true, deserialize = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nota", cascade = CascadeType.PERSIST)
    private List<HistoricoNota> historico;
    
    @Expose(serialize = false, deserialize = false) /* Não enviar na integração */
    @Column(name = "flag_integrado")
    @Enumerated(EnumType.ORDINAL)
    private FlagIntegracao flagIntegrado;

    public Nota() {

    }

    public Nota(String titulo, String descricao, String usuario, String senha, String urlSite) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.senha = senha;
        this.urlSite = urlSite;
        //this.dataCriacao = LocalDateTime.now();
        this.flagIntegrado = FlagIntegracao.LIGADO;
    }

    @Override
    public Integer getId() {
        return this.id;
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

    public FlagIntegracao getFlagIntegrado() {
        return flagIntegrado;
    }

    public void setFlagIntegrado(FlagIntegracao flagIntegrado) {
        this.flagIntegrado = flagIntegrado;
    }
}
