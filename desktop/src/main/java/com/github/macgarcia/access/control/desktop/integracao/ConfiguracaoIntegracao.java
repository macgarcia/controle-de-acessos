package com.github.macgarcia.access.control.desktop.integracao;

import com.github.macgarcia.access.control.desktop.model.FlagIntegracao;
import com.github.macgarcia.access.control.desktop.repository.EntidadeBase;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author macgarcia
 */
@Entity
@Table(name = "configuracao")
public class ConfiguracaoIntegracao implements Serializable, EntidadeBase {
    
    @Id
    private Integer id;
    
    @Column(name = "ativar_integracao")
    @Enumerated(EnumType.ORDINAL)
    private FlagIntegracao ativarIntegracao;
    
    @Column(name = "intervalo_integracao")
    private int intervaloIntegracao;
    
    @Column(name = "inicio_imediato")
    @Enumerated(EnumType.ORDINAL)
    private FlagIntegracao inicioImediato;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FlagIntegracao getAtivarIntegracao() {
        return ativarIntegracao;
    }

    public void setAtivarIntegracao(FlagIntegracao ativarIntegracao) {
        this.ativarIntegracao = ativarIntegracao;
    }

    public int getIntervaloIntegracao() {
        return intervaloIntegracao;
    }

    public void setIntervaloIntegracao(int intervaloIntegracao) {
        this.intervaloIntegracao = intervaloIntegracao;
    }

    public FlagIntegracao getInicioImediato() {
        return inicioImediato;
    }

    public void setInicioImediato(FlagIntegracao inicioImediato) {
        this.inicioImediato = inicioImediato;
    }
}
