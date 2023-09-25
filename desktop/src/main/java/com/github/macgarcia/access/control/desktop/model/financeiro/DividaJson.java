package com.github.macgarcia.access.control.desktop.model.financeiro;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author macgarcia
 */
@Entity
@Table(name = "divida_json_backup")
public class DividaJson implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Integer id;
    
    @Column(name = "divida_json", nullable = false, length = 400)
    private String json;
    
    @OneToOne
    @JoinColumn(name = "divida_id", nullable = false, unique = true)
    private Divida divida;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Divida getDivida() {
        return divida;
    }

    public void setDivida(Divida divida) {
        this.divida = divida;
    }
    
}
