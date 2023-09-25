package com.github.macgarcia.access.control.desktop.enuns;

/**
 *
 * @author macgarcia
 * <p>Enum para organizar todas as procedures que estão armazenadas no banco de dados</p>
 */
public enum ProcessosArmazenados {
    
    PROCEDURE_PROCESSAR_FECHAMENTO_MES("processar_fechamento_mes", "call processar_fechamento_mes(:mes_selecionado_p, :valor_saldo_mensal_p)"),
    PROCEDURE_DESFAZER_FECHAMENTO_MES("desfazer_fechamento_mes", "call desfazer_fechamento_mes(:id_calculo_mensal_p)")
    ;
    
    private final String nomeProcedure;
    private final String chamadaNativaProcesso;

    ProcessosArmazenados(String nomeProcedure, String chamadaNativaProcesso) {
        this.nomeProcedure = nomeProcedure;
        this.chamadaNativaProcesso = chamadaNativaProcesso;
    }

    /***
     * 
     * @return
     * <p>Retorna o nome da procedure.</p>
     */
    public String getNomeProcedure() {
        return nomeProcedure;
    }

    public String getChamadaNativaProcesso() {
        return chamadaNativaProcesso;
    }
 
}
