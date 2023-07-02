package com.github.macgarcia.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.macgarcia.api.dto.HistoricoDtoEntrada;
import com.github.macgarcia.api.dto.NotaDtoEntrada;
import com.github.macgarcia.api.model.HistoricoNota;
import com.github.macgarcia.api.model.Nota;
import com.github.macgarcia.api.repository.NotaRepository;

@Service
public class NotaService {

	@Autowired
	private NotaRepository repository;

	public void processar(final NotaDtoEntrada dto) throws Exception {

		final Nota nota = repository.findByIntegracao(dto.idIntegracao());

		if (nota != null) {
			nota.atualizarNota(dto);
			if (nota.getHistorico() != null) {
				for (HistoricoDtoEntrada h : dto.historico()) {
					final boolean encontrou = nota.getHistorico().stream()
							.anyMatch(e -> e.getNumeroAtualizacao() == h.numeroAtualizacao());
					if (!encontrou) {
						final HistoricoNota novoHistorico = new HistoricoNota(h);
						novoHistorico.setNota(nota);
						nota.getHistorico().add(novoHistorico);
					}
				}
			}
			repository.saveAndFlush(nota);
		} else {
			final Nota novaNota = new Nota(dto);
			if (dto.historico() != null) {
				novaNota.setHistorico(dto.historico().stream().map(HistoricoNota::new).toList());
				novaNota.getHistorico().forEach(h -> h.setNota(novaNota));
			}
			repository.saveAndFlush(novaNota);
		}
	}
}
