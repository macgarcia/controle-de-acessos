package com.github.macgarcia.access.control.desktop.enuns;

/**
 *
 * @author macgarcia
 */
public enum Mes {
    
    JANEIRO(1), FEVEREIRO(2), MARCO(3), ABRIL(4), MAIO(5), JUNHO(6),
    JULHO(7), AGOSTO(8), SETEMBRO(9), OUTUBRO(10), NOVEMBRO(11), DEZEMBRO(12);
    
    private final int valor;
    
    Mes(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
    public static Mes getMesComDigito(int digito) {
        return switch (digito) {
            case 1 -> JANEIRO;
            case 2 -> FEVEREIRO;
            case 3 -> MARCO;
            case 4 -> ABRIL;
            case 5 -> MAIO;
            case 6 -> JUNHO;
            case 7 -> JULHO;
            case 8 -> AGOSTO;
            case 9 -> SETEMBRO;
            case 10 -> OUTUBRO;
            case 11 -> NOVEMBRO;
            case 12 -> DEZEMBRO;
            default -> throw new IllegalArgumentException("Digito incorreto");
        };
    }
    
}
