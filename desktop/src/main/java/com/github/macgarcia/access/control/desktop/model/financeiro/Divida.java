package com.github.macgarcia.access.control.desktop.model.financeiro;

import com.github.macgarcia.access.control.desktop.enuns.CategoriaDivida;
import com.github.macgarcia.access.control.desktop.enuns.Mes;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "divida")
public class Divida implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "descricao", nullable = false, length = 45)
    private String descricao;
    
    @Column(name = "valor", nullable = false)
    private Double valor;
    
    @Column(name = "data_divida", nullable = false)
    private LocalDate dataDivida;
    
    @Column(name = "mes_divida")
    @Enumerated(EnumType.STRING)
    private Mes mes;
    
    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private CategoriaDivida categoria;
    
    @ManyToOne
    @JoinColumn(name = "calculo_mensal_id")
    private CalculoMensal calculoMensal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataDivida() {
        return dataDivida;
    }

    public void setDataDivida(LocalDate dataDivida) {
        this.dataDivida = dataDivida;
    }

    public Mes getMes() {
        return mes;
    }

    public void setMes(Mes mes) {
        this.mes = mes;
    }

    public CategoriaDivida getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDivida categoria) {
        this.categoria = categoria;
    }

    public CalculoMensal getCalculoMensal() {
        return calculoMensal;
    }

    public void setCalculoMensal(CalculoMensal calculoMensal) {
        this.calculoMensal = calculoMensal;
    }
    
}
