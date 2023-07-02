package com.github.macgarcia.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.macgarcia.api.dto.NotaDtoEntrada;
import com.github.macgarcia.api.model.HistoricoNota;
import com.github.macgarcia.api.model.Nota;
import com.github.macgarcia.api.repository.NotaRepository;

@Service
public class NotaService {

	@Autowired
	private NotaRepository repository;

	private List<HistoricoNota> historico;

	public void processar(final NotaDtoEntrada dto) {
		final Nota novaNota = new Nota(dto);
		historico = null;
		if (!dto.historico().isEmpty()) {
			historico = dto.historico().stream().map(HistoricoNota::new).toList();
			historico.forEach(h -> h.setNota(novaNota));
		}
		novaNota.setHistorico(historico);
		repository.saveAndFlush(novaNota);
	}
}
