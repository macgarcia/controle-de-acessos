package com.github.macgarcia.access.control.desktop.integracao;

import java.io.Serializable;

/**
 *
 * @author macgarcia
 */
public class IntegracaoResponse implements Serializable {
    
    public Integer codigo;
    public String metodo;
    public String erro;

    @Override
    public String toString() {
        return "IntegracaoResponse{" + "codigo=" + codigo + ", metodo=" + metodo + ", erro=" + erro + '}';
    }
}
