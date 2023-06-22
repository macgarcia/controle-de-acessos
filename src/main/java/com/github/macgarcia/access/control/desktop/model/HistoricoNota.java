package com.github.macgarcia.access.control.desktop.model;

import com.github.macgarcia.access.control.desktop.repository.EntidadeBase;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author macgarcia
 */
@Entity
@Table(name = "historico_nota")
public class HistoricoNota implements Serializable, EntidadeBase {
    
    @Id
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_nota")
    private Nota nota;
    
    @Column(name = "data_validade_inicial")
    private LocalDateTime dataValidadeInicial;
    @Column(name = "data_validade_final")
    private LocalDateTime dataValidadeFinal;
    @Column(name = "numero_atualizacao")
    private Integer numeroAtualizacao;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "senha")
    private String senha;
    @Column(name = "url_site")
    private String urlSite;
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public String toString() {
        return "HistoricoNota{" + "id=" + id + ", dataValidadeInicial=" + dataValidadeInicial + ", dataValidadeFinal=" + dataValidadeFinal + ", numeroAtualizacao=" + numeroAtualizacao + ", descricao=" + descricao + ", titulo=" + titulo + ", dataCriacao=" + dataCriacao + ", usuario=" + usuario + ", senha=" + senha + ", urlSite=" + urlSite + ", dataAtualizacao=" + dataAtualizacao + '}';
    }
}
