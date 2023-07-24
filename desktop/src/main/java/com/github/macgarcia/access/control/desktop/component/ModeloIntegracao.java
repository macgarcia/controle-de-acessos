package com.github.macgarcia.access.control.desktop.component;

import com.github.macgarcia.access.control.desktop.configuration.Configuracao;
import com.github.macgarcia.access.control.desktop.configuration.FactoryLog;
import com.github.macgarcia.access.control.desktop.integracao.ClientApiBackup;
import com.github.macgarcia.access.control.desktop.model.ConfiguracaoIntegracao;
import com.github.macgarcia.access.control.desktop.integracao.HistoricoDtoRequest;
import com.github.macgarcia.access.control.desktop.integracao.IntegracaoResponse;
import com.github.macgarcia.access.control.desktop.integracao.NotaDtoRequest;
import com.github.macgarcia.access.control.desktop.enuns.FlagIntegracao;
import com.github.macgarcia.access.control.desktop.model.Nota;
import com.github.macgarcia.access.control.desktop.repository.ConfiguracaoIntegracaoRepository;
import com.github.macgarcia.access.control.desktop.repository.NotaRepository;
import com.google.gson.Gson;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 *
 * @author macgarcia
 */
public class ModeloIntegracao extends TimerTask {

    private static final Logger LOGGER = FactoryLog.getLog();

    private final Integer ID_CONFIGURACAO = 1;
    private final Integer STATUS_OK = 201;

    private static Timer timer;
    private ConfiguracaoIntegracaoRepository configuracaoRepository;
    private NotaRepository notaRepository;
    private ConfiguracaoIntegracao config;

    public ModeloIntegracao() {
        buscarConfiguracaoDeIntegracao();
        if (config != null && config.getAtivarIntegracao().equals(FlagIntegracao.LIGADO)) {
            notaRepository = new NotaRepository();
            montarTimerDeIntegracao();
        }
    }

    private void buscarConfiguracaoDeIntegracao() {
        try {
            configuracaoRepository = new ConfiguracaoIntegracaoRepository();
            this.config = configuracaoRepository.consultarPorId(ConfiguracaoIntegracao.class, ID_CONFIGURACAO);
        } catch (InternalError e) {
            config = null;
        }

    }

    private void montarTimerDeIntegracao() {
        timer = new Timer();
        int espera = 0;
        final int periodo = config.getIntervaloIntegracao() * 60_000;
        if (config.getInicioImediato().equals(FlagIntegracao.DESLIGADO)) {
            espera = periodo;
        }
        timer.schedule(this, espera, periodo);
        LOGGER.info("Integração configurada e iniciada.");
    }

    public static Timer getTimer() {
        return timer;
    }

    @Override
    public void run() {

        LOGGER.info(String.format("Iniciando de integração de dados em [%s]",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now())));

        var notas = notaRepository.notasParaIntegrar();

        if (notas.isEmpty()) {
            timer.cancel();
            LOGGER.info("Não há dados para integrar.");
            LOGGER.info("Integração desligada.");
            timer = null;
        }

        final ClientApiBackup cliente = new ClientApiBackup();

        final Gson gson = Configuracao.getGson();

        List<HistoricoDtoRequest> historico = null;

        for (Nota n : notas) {
            if (n.getHistorico() != null && !n.getHistorico().isEmpty()) {
                historico = n.getHistorico().stream().map(HistoricoDtoRequest::new).toList();
            }
            NotaDtoRequest notaParaIntegrar = new NotaDtoRequest(n.getId().longValue(),
                    n.getDescricao(),
                    n.getTitulo(),
                    n.getDataCriacao(),
                    n.getUsuario(),
                    n.getSenha(),
                    n.getUrlSite(),
                    n.getDataAtualizacao(),
                    historico);

            final String json = gson.toJson(notaParaIntegrar);
            LOGGER.info(String.format("Nota de id:[%s], pronta para envio.", n.getId()));

            LOGGER.info("Enviando os dados...");
            final IntegracaoResponse response = cliente.postIntegrarDados(json);

            LOGGER.info(String.format("Retorno da api para a nota de id:[%s] -> [%s]", n.getId(), response));

            if (STATUS_OK.equals(response.codigo)) {
                n.setFlagIntegrado(FlagIntegracao.DESLIGADO);
                notaRepository.salvarEntidade(n);
                LOGGER.info("Nota integrada.");
            }
        }
    }

}
