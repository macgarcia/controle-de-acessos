package com.github.macgarcia.access.control.desktop.view.configuracoes;

import com.github.macgarcia.access.control.desktop.configuration.FactoryMensagem;
import com.github.macgarcia.access.control.desktop.integracao.ConfiguracaoIntegracao;
import com.github.macgarcia.access.control.desktop.model.FlagIntegracao;
import com.github.macgarcia.access.control.desktop.repository.ConfiguracaoIntegracaoRepository;

/**
 *
 * @author macgarcia
 */
public class TelaConfigurarIntegracaoApi extends javax.swing.JInternalFrame {
    
    private final Integer ID_CONFIGURACAO = 1;

    private ConfiguracaoIntegracao config;
    private final ConfiguracaoIntegracaoRepository repository;

    /**
     * Creates new form TelaConfigurarIntegracaoApi
     */
    public TelaConfigurarIntegracaoApi() {
        initComponents();
        repository = new ConfiguracaoIntegracaoRepository();
        configurarJanela();
        montarComboBox();
        buscarInformacoesBase();
        montarTelaComInformacoes();
        acaoBotao();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        boxOpcoes = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtIntervaloMinutos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        check = new javax.swing.JCheckBox();
        btnSalvar = new javax.swing.JButton();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Ativar integração de backup");

        boxOpcoes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tempo de intervalo da integração(minutos)");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Iniciar integração imediatamente");

        check.setText("Sim");

        btnSalvar.setText("Salvar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(check)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSalvar))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(boxOpcoes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(txtIntervaloMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(boxOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIntervaloMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check)
                        .addContainerGap(27, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvar)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxOpcoes;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox check;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtIntervaloMinutos;
    // End of variables declaration//GEN-END:variables

    private void configurarJanela() {
        setTitle("Configuração de integração de backup");
        setResizable(false);
        montarComboBox();
    }

    private void montarComboBox() {
        this.boxOpcoes.removeAllItems();
        for (FlagIntegracao flag : FlagIntegracao.values()) {
            this.boxOpcoes.addItem(flag.name());
        }

        this.boxOpcoes.addActionListener(ev -> {
            int selectedIndex = this.boxOpcoes.getSelectedIndex();
            if (selectedIndex == 0) {
                this.txtIntervaloMinutos.setEnabled(false);
                this.check.setEnabled(false);
            } else {
                this.txtIntervaloMinutos.setEnabled(true);
                this.check.setEnabled(true);
            }
        });
    }

    private void buscarInformacoesBase() {
        config = repository.consultarPorId(ConfiguracaoIntegracao.class, ID_CONFIGURACAO);
    }
    
    private void montarTelaComInformacoes() {
        if (config != null) {
            this.boxOpcoes.setSelectedIndex(config.getAtivarIntegracao().getValor());
            this.txtIntervaloMinutos.setText(String.valueOf(config.getIntervaloIntegracao()));
            if (config.getInicioImediato() == FlagIntegracao.LIGADO) {
                this.check.setSelected(true);
            }
        }
    }
    
    private void acaoBotao() {
        this.btnSalvar.addActionListener(ev -> {
            recuperarInformacaoesDaTela();
            repository.salvarEntidade(config);
            FactoryMensagem.mensagemOk("Configurações armazendas.");
        });
    }
    
    private void recuperarInformacaoesDaTela() {
        if (config == null) {
            config = new ConfiguracaoIntegracao();
        }
        config.setId(ID_CONFIGURACAO);
        
        int selectedIndex = boxOpcoes.getSelectedIndex();
        switch(selectedIndex) {
            case 0 -> config.setAtivarIntegracao(FlagIntegracao.DESLIGADO);
            case 1 -> config.setAtivarIntegracao(FlagIntegracao.LIGADO);
            default -> throw new IllegalArgumentException("Erro na seleção");
        }
        
        if (check.isSelected()) {
            config.setInicioImediato(FlagIntegracao.LIGADO);
        } else {
            config.setInicioImediato(FlagIntegracao.DESLIGADO);
        }
        
        config.setIntervaloIntegracao(Integer.parseInt(txtIntervaloMinutos.getText()));
    }

}
