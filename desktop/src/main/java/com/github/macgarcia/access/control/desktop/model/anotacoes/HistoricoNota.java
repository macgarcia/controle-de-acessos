package com.github.macgarcia.access.control.desktop.model.anotacoes;

import com.github.macgarcia.access.control.desktop.configuration.FactoryLog;
import com.github.macgarcia.access.control.desktop.repository.EntidadeBase;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

    private static final Logger LOGGER = FactoryLog.getLog();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_nota", nullable = false)
    private Nota nota;

    @Column(name = "data_validade_inicial", nullable = false)
    private LocalDateTime dataValidadeInicial;
    @Column(name = "data_validade_final", nullable = false)
    private LocalDateTime dataValidadeFinal;
    @Column(name = "numero_atualizacao", nullable = false)
    private Integer numeroAtualizacao;
    @Column(name = "descricao", nullable = true)
    private String descricao;
    @Column(name = "titulo", nullable = false)
    private String titulo;
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;
    @Column(name = "usuario", nullable = false)
    private String usuario;
    @Column(name = "senha", nullable = false)
    private String senha;
    @Column(name = "url_site", nullable = true)
    private String urlSite;

    public HistoricoNota() {
    }

    public HistoricoNota(LocalDateTime dataValidadeInicial, LocalDateTime dataValidadeFinal,
            Integer numeroAtualizacao, String descricao, String titulo, String usuario, String senha, String urlSite) {
        this.dataValidadeInicial = dataValidadeInicial;
        this.dataValidadeFinal = dataValidadeFinal;
        this.numeroAtualizacao = numeroAtualizacao;
        this.descricao = descricao;
        this.titulo = titulo;
        this.usuario = usuario;
        this.senha = senha;
        this.urlSite = urlSite;
        this.dataCriacao = LocalDateTime.now();
    }

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

    public String montarHistoricoTxt() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LOGGER.info(String.format("Montando linha de histórico, dados:[%s]", this));
        final StringBuilder linha = new StringBuilder();
        linha.append("HISTORICO")
                .append("|")
                .append(formatter.format(this.dataValidadeInicial))
                .append("|")
                .append(formatter.format(this.dataValidadeFinal))
                .append("|")
                .append(this.numeroAtualizacao)
                .append("|")
                .append(this.descricao != null && this.descricao.length() != 0 ? this.descricao : " ")
                .append("|")
                .append(this.titulo)
                .append("|")
                .append(this.usuario)
                .append("|")
                .append(this.senha)
                .append("|")
                .append(this.urlSite != null && this.urlSite.length() != 0 ? this.urlSite : " ");
        LOGGER.info("Linha montada com sucesso.");
        return linha.toString();
    }
}
