package com.github.macgarcia.access.control.desktop;

import com.github.macgarcia.access.control.desktop.component.ModeloIntegracao;
import com.github.macgarcia.access.control.desktop.configuration.Configuracao;
import com.github.macgarcia.access.control.desktop.configuration.FactoryMensagem;
import com.github.macgarcia.access.control.desktop.configuration.FactoryTela;
import com.github.macgarcia.access.control.desktop.view.anotacoes.TelaCadastroNota;
import com.github.macgarcia.access.control.desktop.view.anotacoes.TelaDeHistoricoNota;
import com.github.macgarcia.access.control.desktop.view.anotacoes.TelaTodasAnotacoes;
import com.github.macgarcia.access.control.desktop.view.configuracoes.TelaConfigurarIntegracaoApi;
import com.github.macgarcia.access.control.desktop.view.configuracoes.TelaDeLogs;
import com.github.macgarcia.access.control.desktop.view.editor.TelaEditorTexto;
import com.github.macgarcia.access.control.desktop.view.integracoes.TelaDeExportacaoDados;
import com.github.macgarcia.access.control.desktop.view.integracoes.TelaIntegracaoJson;
import com.github.macgarcia.access.control.desktop.view.integracoes.TelaIntegracaoTxt;

/**
 *
 * @author macgarcia
 */
public class TelaInicial extends javax.swing.JFrame {

    private final String MENSAGEM_DE_TELA_ABERTA = "A tela já esta aberta em sua area de trabalho.";
    private final ModeloIntegracao integracao;

    /**
     * Creates new form TelaInicial
     */
    public TelaInicial() {
        initComponents();
        setExtendedState(TelaInicial.MAXIMIZED_BOTH);
        configurarJanela();
        acoesDoMenuAnotacoes();
        acoesDoMenuIntegracao();
        acoesDoMenuConfiguracao();
        acoesDoMenuEditorTexto();
        integracao = new ModeloIntegracao();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPanel = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        btnMenuAnotacoes = new javax.swing.JMenu();
        btnItemMenuNovaNota = new javax.swing.JMenuItem();
        btnItemMenuTodasNotas = new javax.swing.JMenuItem();
        btnItemHistoricoDeNotas = new javax.swing.JMenuItem();
        btnItemMenuSair = new javax.swing.JMenuItem();
        btnMenuIntegracao = new javax.swing.JMenu();
        btnItemMenuImportarDocumentoTxt = new javax.swing.JMenuItem();
        btnItemMenuimportarDocumentoJson = new javax.swing.JMenuItem();
        btnItemMenuExportarDados = new javax.swing.JMenuItem();
        btnMenuConfiguracoes = new javax.swing.JMenu();
        btnItemMenuLogs = new javax.swing.JMenuItem();
        btnItemMenuConfigurarIntegracao = new javax.swing.JMenuItem();
        btnMenuEditorTexto = new javax.swing.JMenu();
        btnItemMenuNovoEditor = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout desktopPanelLayout = new javax.swing.GroupLayout(desktopPanel);
        desktopPanel.setLayout(desktopPanelLayout);
        desktopPanelLayout.setHorizontalGroup(
            desktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 798, Short.MAX_VALUE)
        );
        desktopPanelLayout.setVerticalGroup(
            desktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 414, Short.MAX_VALUE)
        );

        btnMenuAnotacoes.setText("Anotações");

        btnItemMenuNovaNota.setText("Cadastrar nova nota");
        btnMenuAnotacoes.add(btnItemMenuNovaNota);

        btnItemMenuTodasNotas.setText("Todas as notas");
        btnMenuAnotacoes.add(btnItemMenuTodasNotas);

        btnItemHistoricoDeNotas.setText("Histórico de atualizações das notas");
        btnMenuAnotacoes.add(btnItemHistoricoDeNotas);

        btnItemMenuSair.setText("Sair");
        btnMenuAnotacoes.add(btnItemMenuSair);

        jMenuBar1.add(btnMenuAnotacoes);

        btnMenuIntegracao.setText("Integração");

        btnItemMenuImportarDocumentoTxt.setText("Importar documento .txt");
        btnMenuIntegracao.add(btnItemMenuImportarDocumentoTxt);

        btnItemMenuimportarDocumentoJson.setText("Importar documento .json");
        btnMenuIntegracao.add(btnItemMenuimportarDocumentoJson);

        btnItemMenuExportarDados.setText("Exportar dados");
        btnMenuIntegracao.add(btnItemMenuExportarDados);

        jMenuBar1.add(btnMenuIntegracao);

        btnMenuConfiguracoes.setText("Configurações");

        btnItemMenuLogs.setText("Logs");
        btnMenuConfiguracoes.add(btnItemMenuLogs);

        btnItemMenuConfigurarIntegracao.setText("Configurar integração backup api");
        btnMenuConfiguracoes.add(btnItemMenuConfigurarIntegracao);

        jMenuBar1.add(btnMenuConfiguracoes);

        btnMenuEditorTexto.setText("Editor de texto");

        btnItemMenuNovoEditor.setText("Novo editor");
        btnMenuEditorTexto.add(btnItemMenuNovoEditor);

        jMenuBar1.add(btnMenuEditorTexto);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPanel)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnItemHistoricoDeNotas;
    private javax.swing.JMenuItem btnItemMenuConfigurarIntegracao;
    private javax.swing.JMenuItem btnItemMenuExportarDados;
    private javax.swing.JMenuItem btnItemMenuImportarDocumentoTxt;
    private javax.swing.JMenuItem btnItemMenuLogs;
    private javax.swing.JMenuItem btnItemMenuNovaNota;
    private javax.swing.JMenuItem btnItemMenuNovoEditor;
    private javax.swing.JMenuItem btnItemMenuSair;
    private javax.swing.JMenuItem btnItemMenuTodasNotas;
    private javax.swing.JMenuItem btnItemMenuimportarDocumentoJson;
    private javax.swing.JMenu btnMenuAnotacoes;
    private javax.swing.JMenu btnMenuConfiguracoes;
    private javax.swing.JMenu btnMenuEditorTexto;
    private javax.swing.JMenu btnMenuIntegracao;
    private javax.swing.JDesktopPane desktopPanel;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables

    private void configurarJanela() {
        setTitle("Controle de acessos pessoais");
        setResizable(false);
    }

    private void acoesDoMenuAnotacoes() {

        this.btnItemMenuNovaNota.addActionListener(ev -> {
            if (Configuracao.verificarJanelaAberta(this.desktopPanel, TelaCadastroNota.class)) {
                FactoryMensagem.mensagemAlerta(MENSAGEM_DE_TELA_ABERTA);
            } else {
                final TelaCadastroNota tela = FactoryTela.criarTela(TelaCadastroNota.class, desktopPanel);
                tela.setAtualizacaoDeNota(false);
            }
        });

        this.btnItemMenuTodasNotas.addActionListener(ev -> {
            if (Configuracao.verificarJanelaAberta(desktopPanel, TelaTodasAnotacoes.class)) {
                FactoryMensagem.mensagemAlerta(MENSAGEM_DE_TELA_ABERTA);
            } else {
                final TelaTodasAnotacoes tela = FactoryTela.criarTela(TelaTodasAnotacoes.class, desktopPanel);
                tela.setDesktop(desktopPanel);
            }
        });

        this.btnItemHistoricoDeNotas.addActionListener(ev -> {
            if (Configuracao.verificarJanelaAberta(desktopPanel, TelaDeHistoricoNota.class)) {
                FactoryMensagem.mensagemAlerta(MENSAGEM_DE_TELA_ABERTA);
            } else {
                FactoryTela.criarTela(TelaDeHistoricoNota.class, desktopPanel);
            }
        });

        this.btnItemMenuSair.addActionListener(ev -> {
            System.exit(0);
        });
    }

    private void acoesDoMenuIntegracao() {

        this.btnItemMenuImportarDocumentoTxt.addActionListener(ev -> {
            if (Configuracao.verificarJanelaAberta(this.desktopPanel, TelaIntegracaoTxt.class)) {
                FactoryMensagem.mensagemAlerta(MENSAGEM_DE_TELA_ABERTA);
            } else {
                FactoryTela.criarTela(TelaIntegracaoTxt.class, desktopPanel);
            }
        });
        
        this.btnItemMenuimportarDocumentoJson.addActionListener(ev -> {
            if (Configuracao.verificarJanelaAberta(desktopPanel, TelaIntegracaoJson.class)) {
                FactoryMensagem.mensagemAlerta(MENSAGEM_DE_TELA_ABERTA);
            } else {
                FactoryTela.criarTela(TelaIntegracaoJson.class, desktopPanel);
            }
        });

        this.btnItemMenuExportarDados.addActionListener(ev -> {
            if (Configuracao.verificarJanelaAberta(this.desktopPanel, TelaDeExportacaoDados.class)) {
                FactoryMensagem.mensagemAlerta(MENSAGEM_DE_TELA_ABERTA);
            } else {
                FactoryTela.criarTela(TelaDeExportacaoDados.class, desktopPanel);
            }
        });
    }

    private void acoesDoMenuConfiguracao() {

        this.btnItemMenuLogs.addActionListener(ev -> {
            if (Configuracao.verificarJanelaAberta(this.desktopPanel, TelaDeLogs.class)) {
                FactoryMensagem.mensagemAlerta(MENSAGEM_DE_TELA_ABERTA);
            } else {
                FactoryTela.criarTela(TelaDeLogs.class, desktopPanel);
            }
        });

        this.btnItemMenuConfigurarIntegracao.addActionListener(ev -> {
            if (Configuracao.verificarJanelaAberta(this.desktopPanel, TelaConfigurarIntegracaoApi.class)) {
                FactoryMensagem.mensagemAlerta(MENSAGEM_DE_TELA_ABERTA);
            } else {
                FactoryTela.criarTela(TelaConfigurarIntegracaoApi.class, desktopPanel);
            }
        });
    }
    
    private void acoesDoMenuEditorTexto() {
        
        this.btnItemMenuNovoEditor.addActionListener(ev -> {
            FactoryTela.criarTela(TelaEditorTexto.class, desktopPanel);
        });
    }
}
