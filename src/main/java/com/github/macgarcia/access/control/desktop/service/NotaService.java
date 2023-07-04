package com.github.macgarcia.access.control.desktop.service;

import com.github.macgarcia.access.control.desktop.configuration.FactoryMensagem;
import com.github.macgarcia.access.control.desktop.model.Nota;
import com.github.macgarcia.access.control.desktop.repository.NotaRepository;

/**
 *
 * @author macgarcia
 */
public class NotaService {
    
    final NotaRepository repository;
    
    public NotaService() {
        this.repository = new NotaRepository();
    }
    
    public boolean salvarNota(final Nota novaNota) {
        return repository.salvarEntidade(novaNota);
    }
    
    public void apagar(final Integer id) {
        this.repository.apagarEntidade(Nota.class, id);
        FactoryMensagem.mensagemOk("Registro apagado com sucesso.");
    }
    
    public Long contarhistorico(final Integer idNota) {
        return repository.contarhistoricoDaNota(idNota);
    }
}
