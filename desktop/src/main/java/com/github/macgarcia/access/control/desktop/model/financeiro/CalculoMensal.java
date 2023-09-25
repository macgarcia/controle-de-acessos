package com.github.macgarcia.access.control.desktop.model.financeiro;

import com.github.macgarcia.access.control.desktop.enuns.Mes;
import com.github.macgarcia.access.control.desktop.enuns.Situacao;
import com.github.macgarcia.access.control.desktop.repository.EntidadeBase;
import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author macgarcia
 */
@Entity
@Table(name = "calculo_mensal")
@NamedQueries({
    @NamedQuery(name = "CalculoMensal.todosOsCalculos", query = "select c from CalculoMensal c"),
    @NamedQuery(name = "CalculoMensal.existeCalculoMensal", query = "select c from CalculoMensal c where c.mes = :mes and c.ano = :ano and c.situacao = :situacao")
})
public class CalculoMensal implements Serializable, EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor_saldo_mensal", nullable = false, precision = 10, scale = 2)
    private Double valorSaldoMensal;

    @Column(name = "mes")
    @Enumerated(EnumType.STRING)
    private Mes mes;

    @Column(name = "valor_total_divida", nullable = false,precision = 10, scale = 2)
    private Double valorTotalDividas;

    @Column(name = "valor_resultante", nullable = false, precision = 10, scale = 2)
    private Double valorResultante;

    @Column(name = "situacao")
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
    
    @Column(name = "ano")
    private Integer ano;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "calculoMensal", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Divida> dividas;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValorSaldoMensal() {
        return valorSaldoMensal;
    }

    public void setValorSaldoMensal(Double valorSaldoMensal) {
        this.valorSaldoMensal = valorSaldoMensal;
    }

    public Mes getMes() {
        return mes;
    }

    public void setMes(Mes mes) {
        this.mes = mes;
    }

    public List<Divida> getDividas() {
        return dividas;
    }

    public void setDividas(List<Divida> dividas) {
        this.dividas = dividas;
    }

    public Double getValorTotalDividas() {
        return valorTotalDividas;
    }

    public void setValorTotalDividas(Double valorTotalDividas) {
        this.valorTotalDividas = valorTotalDividas;
    }

    public Double getValorResultante() {
        return valorResultante;
    }

    public void setValorResultante(Double valorResultante) {
        this.valorResultante = valorResultante;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

}
