package com.github.macgarcia.access.control.desktop.enuns;

/**
 *
 * @author macgarcia
 */
public enum ProcessosArmazenados {
    
    PROCEDURE_PROCESSAR_FECHAMENTO_MES("processar_fechamento_mes"),
    PROCEDURE_DESFAZER_FECHAMENTO_MES("desfazer_fechamento_mes")
    ;
    
    private final String nomeProcedure;

    ProcessosArmazenados(String nomeProcedure) {
        this.nomeProcedure = nomeProcedure;
    }

    public String getNomeProcedure() {
        return nomeProcedure;
    }
    
}
