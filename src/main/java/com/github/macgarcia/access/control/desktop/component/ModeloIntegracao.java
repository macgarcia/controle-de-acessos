package com.github.macgarcia.access.control.desktop.component;

import com.github.macgarcia.access.control.desktop.integracao.ClientApiBackup;
import com.github.macgarcia.access.control.desktop.integracao.ConfiguracaoIntegracao;
import com.github.macgarcia.access.control.desktop.integracao.IntegracaoResponse;
import com.github.macgarcia.access.control.desktop.model.FlagIntegracao;
import com.github.macgarcia.access.control.desktop.model.Nota;
import com.github.macgarcia.access.control.desktop.repository.ConfiguracaoIntegracaoRepository;
import com.github.macgarcia.access.control.desktop.repository.NotaRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author macgarcia
 */
public class ModeloIntegracao extends TimerTask {

    private final Integer ID_CONFIGURACAO = 1;
    private final Integer STATUS_OK = 200;
    private final Integer STATUS_ERRO = 500;

    private Timer timer;
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
        configuracaoRepository = new ConfiguracaoIntegracaoRepository();
        this.config = configuracaoRepository.consultarPorId(ConfiguracaoIntegracao.class, ID_CONFIGURACAO);
    }

    private void montarTimerDeIntegracao() {
        timer = new Timer();
        int espera = 0;
        final int periodo = config.getIntervaloIntegracao() * 60_000;
        if (config.getInicioImediato().equals(FlagIntegracao.DESLIGADO)) {
            espera = periodo;
        }
        //timer.schedule(this, espera, periodo);
        timer.schedule(this, 0, 120_000);
    }

    @Override
    public void run() {
        var notas = notaRepository.notasParaIntegrar();
        if (notas.isEmpty()) {
            timer.cancel();
        }
            final ClientApiBackup cliente = new ClientApiBackup();
            
            final Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
            
            for (Nota n : notas) {
                System.out.println(n.getDataCriacao().toString());
                final String json = gson.toJson(n);
                final IntegracaoResponse response = cliente.postIntegrarDados(json);
                if (response.codigo.equals(STATUS_OK)) {
                    System.out.println("Integração realizada" + response);
                }
            }

    }

}
