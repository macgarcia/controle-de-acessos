package com.github.macgarcia.access.control.desktop.model.financeiro;

import com.github.macgarcia.access.control.desktop.enuns.Mes;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author macgarcia
 */
@Entity
@Table(name = "calculo_mensal")
public class CalculoMensal implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "valor_saldo_mensal", nullable = false)
    private Double valorSaldoMensal;
    
    @Column(name = "mes")
    @Enumerated(EnumType.STRING)
    private Mes mes;
    
    @Column(name = "valor_total_divida")
    private Double valorTotalDividas;
    
    @Column(name = "valor_resultante")
    private Double valorResultante;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "calculoMensal", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Divida> dividas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    
}
