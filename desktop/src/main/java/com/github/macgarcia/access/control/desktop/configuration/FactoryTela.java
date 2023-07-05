package com.github.macgarcia.access.control.desktop.configuration;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author macgarcia
 */
public class FactoryTela {
    
    private static final Logger LOGGER = FactoryLog.getLog();

    public static <C extends JInternalFrame> C criarTela(Class<C> classe, final JDesktopPane desktop) {
        try {
            final C tela = classe.getDeclaredConstructor().newInstance();
            desktop.add(tela);
            Configuracao.setPosicaoInternalFrame(desktop, tela);
            tela.setVisible(true);
            return tela;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            LOGGER.severe(String.format("Erro para abrir a tela.: [%s]", classe));
            ex.printStackTrace();
        }
        return null;
    }
    
}
