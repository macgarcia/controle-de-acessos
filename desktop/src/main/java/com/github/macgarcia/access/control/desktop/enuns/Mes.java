package com.github.macgarcia.access.control.desktop.enuns;

/**
 *
 * @author macgarcia
 */
public enum Mes {
    
    JANEIRO, FEVEREIRO, MARCO, ABRIL, MAIO, JUNHO,
    JULHO, AGOSTO, SETEMBRO, OUTUBRO, NOVEMBRO, DEZEMBRO;
        
    public static Mes getMesComDigito(int digito) {
        return switch (digito) {
            case 0 -> JANEIRO;
            case 1 -> FEVEREIRO;
            case 2 -> MARCO;
            case 3 -> ABRIL;
            case 4 -> MAIO;
            case 5 -> JUNHO;
            case 6 -> JULHO;
            case 7 -> AGOSTO;
            case 8 -> SETEMBRO;
            case 9 -> OUTUBRO;
            case 10 -> NOVEMBRO;
            case 11 -> DEZEMBRO;
            default -> throw new IllegalArgumentException("Digito incorreto");
        };
    }
    
}
