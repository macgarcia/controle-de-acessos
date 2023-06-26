package com.github.macgarcia.access.control.desktop.pojo;

import java.time.LocalDateTime;

/**
 *
 * @author macgarcia
 * Informações da tela de exportação.
 */
public class PojoDadosExportacao {
    
    private final String caminho;
    private final LocalDateTime dataInicial;
    private final LocalDateTime dataFinal;

    public PojoDadosExportacao(String caminho, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        this.caminho = caminho;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public String getCaminho() {
        return caminho;
    }

    public LocalDateTime getDataInicial() {
        return dataInicial;
    }

    public LocalDateTime getDataFinal() {
        return dataFinal;
    }

    @Override
    public String toString() {
        return "PojoDadosExportacao{" + "caminho=" + caminho + ", dataInicial=" + dataInicial + ", dataFinal=" + dataFinal + '}';
    }
    
}
