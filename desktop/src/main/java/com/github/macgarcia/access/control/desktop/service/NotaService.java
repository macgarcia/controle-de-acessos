package com.github.macgarcia.access.control.desktop.service;

import com.github.macgarcia.access.control.desktop.model.anotacoes.Nota;
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
    }
    
}
