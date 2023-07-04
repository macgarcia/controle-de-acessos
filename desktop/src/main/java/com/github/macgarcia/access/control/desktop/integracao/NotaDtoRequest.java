package com.github.macgarcia.access.control.desktop.integracao;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author macgarcia
 */
public record NotaDtoRequest(Long idIntegracao,
        String descricao,
        String titulo,
        LocalDateTime dataCriacao,
        String usuario,
        String senha,
        String urlSite,
        LocalDateTime dataAtualizacao,
        List<HistoricoDtoRequest> historico) {

}
