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
    
    public void salvarNota(final Nota novaNota) {
        final boolean salvou = repository.salvarEntidade(novaNota);
        if (salvou) {
            FactoryMensagem.mensagemOk("Nota salva com sucesso.");
        } else {
            FactoryMensagem.mensagemErro("Erro ao salvar a nota.");
        }
    }
    
    public void apagar(final Integer id) {
        this.repository.apagarEntidade(Nota.class, id);
        FactoryMensagem.mensagemOk("Registro apagado com sucesso.");
    }
}
