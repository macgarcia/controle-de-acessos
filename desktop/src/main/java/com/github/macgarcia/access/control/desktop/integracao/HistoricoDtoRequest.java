package com.github.macgarcia.access.control.desktop.integracao;

import com.github.macgarcia.access.control.desktop.model.HistoricoNota;
import java.time.LocalDateTime;

/**
 *
 * @author macgarcia
 */
public record HistoricoDtoRequest(LocalDateTime dataValidadeInicial,
        LocalDateTime dataValidadeFinal,
        Integer numeroAtualizacao,
        String descricao,
        String titulo,
        LocalDateTime dataCriacao,
        String usuario,
        String senha,
        String urlSite) {

    public HistoricoDtoRequest(HistoricoNota historico) {
        this(historico.getDataValidadeInicial(),
                historico.getDataValidadeFinal(),
                historico.getNumeroAtualizacao(),
                historico.getDescricao(),
                historico.getTitulo(),
                historico.getDataCriacao(),
                historico.getUsuario(),
                historico.getSenha(),
                historico.getUrlSite());
    }

}
