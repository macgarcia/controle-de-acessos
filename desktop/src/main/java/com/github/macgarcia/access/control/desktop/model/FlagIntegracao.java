package com.github.macgarcia.access.control.desktop.model;

/**
 *
 * @author macgarcia
 */
public enum FlagIntegracao {
    
    DESLIGADO(0), LIGADO(1);
    
    private final int valor;

    FlagIntegracao(int valor) {
        this.valor = valor;
    }
    

    public int getValor() {
        return valor;
    }
    
}
