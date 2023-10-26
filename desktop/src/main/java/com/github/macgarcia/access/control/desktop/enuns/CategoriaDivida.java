package com.github.macgarcia.access.control.desktop.enuns;

/**
 *
 * @author macgarcia
 */
public enum CategoriaDivida {

    TODAS, CARTAO_CREDITO, ESTUDO, CASA, SAUDE, DIVERSOS;
    
    public static CategoriaDivida get(int index) {
        return switch (index) {
            case 0 -> null;
            case 1 -> CARTAO_CREDITO;
            case 2 -> ESTUDO;
            case 3 -> CASA;
            case 4 -> SAUDE;
            case 5 -> DIVERSOS;
            default -> throw new IllegalArgumentException("Tipo de categoria não existe.");
        };
    }
    
}
