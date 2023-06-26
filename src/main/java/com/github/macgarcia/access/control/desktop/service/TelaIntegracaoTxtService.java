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
    private int qtdeHistoricosPorNota;

    private List<Nota> notas;

    private final NotaRepository notaRepository;

    public TelaIntegracaoTxtService() {
        this.notaRepository = new NotaRepository();
    }

    public void processarDadosDoArquivo(List<String> linhasDoArquivo) throws Exception {
        for (String linha : linhasDoArquivo) {
            final String[] dadosLinha = linha.split("\\|");
            montarNota(dadosLinha);
            montarHistorico(dadosLinha);
            if (qtdeHistoricosPorNota == getHistoricos().size()) {
                this.nota.setHistorico(historicos);
                getNotas().add(nota);
                this.historicos = null;
            }
        }
    }

    public void salvarNotas() {
        getNotas().forEach(notaRepository::salvarEntidade);
        FactoryMensagem.mensagemOk("Integração realizada com sucesso.");
    }

    private void montarNota(final String[] dadosLinha) {
        if (TOKEN_NOTA.equals(dadosLinha[0])) {

            /* Quantidade de historicos que a nota possui */
            this.qtdeHistoricosPorNota = Integer.parseInt(dadosLinha[1]);

            this.nota = new Nota(dadosLinha[2], dadosLinha[3],
                    dadosLinha[4], dadosLinha[5], dadosLinha[6]);
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

    public List<Nota> getNotas() {
        if (this.notas == null) {
            this.notas = new ArrayList<>();
        }
        return notas;
    }
}