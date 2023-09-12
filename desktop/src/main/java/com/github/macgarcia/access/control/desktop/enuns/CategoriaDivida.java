package com.github.macgarcia.access.control.desktop.enuns;

/**
 *
 * @author macgarcia
 */
public enum CategoriaDivida {

    CARTAO_CREDITO(1), ESTUDO(2), CASA(3), SAUDE(4), DIVERSOS(5);

    private final int valor;
    
    CategoriaDivida(final int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
}
