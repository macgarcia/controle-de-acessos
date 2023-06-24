package com.github.macgarcia.access.control.desktop.service;

import com.github.macgarcia.access.control.desktop.configuration.FactoryMensagem;
import com.github.macgarcia.access.control.desktop.model.HistoricoNota;
import com.github.macgarcia.access.control.desktop.model.Nota;
import com.github.macgarcia.access.control.desktop.repository.NotaRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macgarcia
 */
public class TelaIntegracaoTxtService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final String TOKEN_NOTA = "NOTA";
    private final String TOKEN_HISTORICO = "HISTORICO";

    private Nota nota = null;
    private HistoricoNota historicoNota = null;
    private ArrayList<HistoricoNota> historicos;

    private final NotaRepository notaRepository;

    public TelaIntegracaoTxtService() {
        this.notaRepository = new NotaRepository();
    }

    public void processarDadosDoArquivo(List<String> linhasDoArquivo) {
        for (String linha : linhasDoArquivo) {
            final String[] dadosLinha = linha.split("\\|");
            montarNota(dadosLinha);
            montarHistorico(dadosLinha);
        }
        this.nota.setHistorico(historicos);
        final boolean salvou = this.notaRepository.salvarEntidade(nota);
        if (salvou) {
            FactoryMensagem.mensagemOk("Integração realizada com sucesso.");
        }
    }

    private void montarNota(final String[] dadosLinha) {
        if (TOKEN_NOTA.equals(dadosLinha[0])) {
            this.nota = new Nota(dadosLinha[1], dadosLinha[2],
                    dadosLinha[3], dadosLinha[4],dadosLinha[5]);
        }
    }

    private void montarHistorico(final String[] dadosLinha) {
        if (TOKEN_HISTORICO.equals(dadosLinha[0])) {
            this.historicoNota = new HistoricoNota(
                    LocalDateTime.parse(dadosLinha[1], formatter),
                    LocalDateTime.parse(dadosLinha[2], formatter),
                    Integer.valueOf(dadosLinha[3]),
                    dadosLinha[4].trim().isEmpty() ? null : dadosLinha[4],
                    dadosLinha[5].trim().isEmpty() ? null : dadosLinha[5],
                    dadosLinha[6].trim().isEmpty() ? null : dadosLinha[6],
                    dadosLinha[7].trim().isEmpty() ? null : dadosLinha[7],
                    dadosLinha[8].trim().isEmpty() ? null : dadosLinha[8],
                    dadosLinha[9].trim().isEmpty() ? null : LocalDateTime.parse(dadosLinha[9], formatter)
            );
            this.getHistoricos();
            this.historicoNota.setNota(nota);
            this.historicos.add(historicoNota);
        }
    }

    public ArrayList<HistoricoNota> getHistoricos() {
        if (this.historicos == null) {
            this.historicos = new ArrayList<>();
        }
        return historicos;
    }
}
