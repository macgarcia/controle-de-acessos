package com.github.macgarcia.access.control.desktop.configuration;

import javax.swing.JOptionPane;

/**
 *
 * @author macgarcia
 */
public class FactoryMensagem {
    
    private static final String ALERTA = "Alerta";
    private static final String ERRO = "Erro";
    private static final String INFORMACAO = "Informação";
    
    public static void mensagemOk(final String msg) {
        JOptionPane.showMessageDialog(null, msg, INFORMACAO, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void mensagemAlerta(final String msg) {
        JOptionPane.showMessageDialog(null, msg, ALERTA, JOptionPane.WARNING_MESSAGE);
    }
    
    public static void mensagemErro(final String msg) {
        JOptionPane.showMessageDialog(null, msg, ERRO, JOptionPane.ERROR_MESSAGE);
    }
    
    public static int mensagemConfirmacao() {
        return JOptionPane.showConfirmDialog(null, "Deseja apagar o registro selecionado", INFORMACAO, JOptionPane.YES_NO_OPTION);
    }
}
