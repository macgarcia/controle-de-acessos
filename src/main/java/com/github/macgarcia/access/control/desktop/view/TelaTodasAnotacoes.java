package com.github.macgarcia.access.control.desktop.view;

import com.github.macgarcia.access.control.desktop.component.ModeloTabelaNota;
import com.github.macgarcia.access.control.desktop.configuration.Configuracao;
import com.github.macgarcia.access.control.desktop.configuration.FactoryMensagem;
import com.github.macgarcia.access.control.desktop.model.Nota;
import com.github.macgarcia.access.control.desktop.service.NotaService;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;

/**
 *
 * @author macgarcia
 */
public class TelaTodasAnotacoes extends javax.swing.JInternalFrame {

    private ModeloTabelaNota model;
    private Nota notaSelecionada;
    private JDesktopPane desktop;
    private final int ZERO = 0;

    /**
     * Creates new form TelaTodasAnotacoes
     */
    public TelaTodasAnotacoes(final JDesktopPane desktopPane) {
        initComponents();
        this.desktop = desktopPane;
        configurarJanela();
        construirTabela();
        acoesDosBotoes();
        acaoDeClickDeSelecaoNaTabela();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableNotas = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        btnLimparPesquisa = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Minhas anotações");

        jTableNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableNotas);

        btnEditar.setText("Editar");

        btnVer.setText("Ver");

        btnApagar.setForeground(new java.awt.Color(255, 0, 0));
        btnApagar.setText("Apagar");

        btnPesquisar.setForeground(new java.awt.Color(51, 153, 0));
        btnPesquisar.setText("Pesquisar");

        btnLimparPesquisa.setForeground(new java.awt.Color(51, 51, 255));
        btnLimparPesquisa.setText("Limpar pesquisa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnApagar)
                        .addGap(149, 149, 149)
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimparPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVer)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnVer)
                    .addComponent(btnApagar)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar)
                    .addComponent(btnLimparPesquisa))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnLimparPesquisa;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnVer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableNotas;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables

    private void configurarJanela() {
        setTitle("Minhas anotações");
        setResizable(false);
    }

    private void construirTabela() {
        this.model = new ModeloTabelaNota();
        this.jTableNotas.setModel(this.model);
        this.jTableNotas.getTableHeader().setReorderingAllowed(false);
    }

    private void acoesDosBotoes() {

        this.btnEditar.addActionListener(ev -> {
            if (notaSelecionada == null) {
                FactoryMensagem.mensagemAlerta("Selecione uma nota na tabela.");
            } else {
                if (Configuracao.verificarJanelaAberta(desktop, TelaCadastroNota.class)) {
                    FactoryMensagem.mensagemAlerta("Tela já esta aberta para edição.");
                } else {
                    TelaCadastroNota tela = new TelaCadastroNota();

                    tela.setAtualizacaoDeNota(true);
                    tela.setNotaParaAtualizar(notaSelecionada);
                    tela.setDadosNaTelaDeAtualização(this.notaSelecionada);

                    this.desktop.add(tela);
                    Configuracao.setPosicaoInternalFrame(desktop, tela);
                    tela.setVisible(true);
                }
            }
        });

        this.btnVer.addActionListener(ev -> {
            if (Configuracao.verificarJanelaAberta(desktop, TelaVerDadosNota.class)) {
                FactoryMensagem.mensagemAlerta("Seus dados ja estão em vizualização.");
            } else {
                final TelaVerDadosNota tela = new TelaVerDadosNota();
                tela.mostrarDados(notaSelecionada);
                this.desktop.add(tela);
                Configuracao.setPosicaoInternalFrame(desktop, tela);
                tela.setVisible(true);
            }
        });

        this.btnApagar.addActionListener(ev -> {
            if (notaSelecionada == null) {
                FactoryMensagem.mensagemAlerta("Selecione uma nota na tabela.");
            } else {
                final int resposta = FactoryMensagem.mensagemConfirmacao();
                if (resposta == ZERO) {
                    new NotaService().apagar(notaSelecionada.getId());
                    construirTabela();
                }
            }
        });
        
        this.btnPesquisar.addActionListener(ev -> {
            final String chave = this.txtPesquisar.getText().trim();
            if (!chave.isEmpty()) {
                this.model.pesquisar(chave);
                this.jTableNotas.updateUI();
            }
        });
        
        this.btnLimparPesquisa.addActionListener(ev ->{
            this.txtPesquisar.setText(null);
            construirTabela();;
        });
    }

    private void acaoDeClickDeSelecaoNaTabela() {
        this.jTableNotas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                notaSelecionada = model.getNota(jTableNotas.getSelectedRow());
            }
        });
    }
}
