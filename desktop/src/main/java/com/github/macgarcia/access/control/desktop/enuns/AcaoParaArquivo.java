package com.github.macgarcia.access.control.desktop.enuns;

/**
 *
 * @author macgarcia
 */
public enum AcaoParaArquivo {
    
    SALVAR("Salvar..."), ABRIR("Abrir...");
    
    private final String valor;

    AcaoParaArquivo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
    
}
