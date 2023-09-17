package com.github.macgarcia.access.control.desktop.enuns;

/**
 *
 * @author macgarcia
 * <p>Enum para organizar todas as procedures que estão armazenadas no banco de dados</p>
 */
public enum ProcessosArmazenados {
    
    PROCEDURE_PROCESSAR_FECHAMENTO_MES("processar_fechamento_mes"),
    PROCEDURE_DESFAZER_FECHAMENTO_MES("desfazer_fechamento_mes")
    ;
    
    private final String nomeProcedure;

    ProcessosArmazenados(String nomeProcedure) {
        this.nomeProcedure = nomeProcedure;
    }

    /***
     * 
     * @return
     * <p>Retorna o nome da procedure.</p>
     */
    public String getNomeProcedure() {
        return nomeProcedure;
    }
    
}
