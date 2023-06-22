package com.github.macgarcia.access.control.desktop.model;

import com.github.macgarcia.access.control.desktop.repository.EntidadeBase;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nota")
    private List<HistoricoNota> historico;
    
    public Nota(){
        
    }

    public Nota(String titulo, String descricao, String usuario, String senha, String urlSite) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.senha = senha;
        this.urlSite = urlSite;
        this.dataCriacao = LocalDateTime.now();
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
        return historico;
    }

    public void setHistorico(List<HistoricoNota> historico) {
        this.historico = historico;
    }

    @Override
    public String toString() {
        return "Nota{" + "id=" + id + ", descricao=" + descricao + ", titulo=" + titulo + ", dataCriacao=" + dataCriacao + ", usuario=" + usuario + ", senha=" + senha + ", urlSite=" + urlSite + ", dataAtualizacao=" + dataAtualizacao + '}';
    }
    
    public String comHistorico() {
        return "Nota{" + "id=" + id + ", descricao=" + descricao 
                + ", titulo=" + titulo + ", dataCriacao=" + dataCriacao 
                + ", usuario=" + usuario + ", senha=" + senha + ", urlSite=" + urlSite 
                + ", dataAtualizacao=" + dataAtualizacao 
                + ", historico=" + historico
                + '}';
    }
}
