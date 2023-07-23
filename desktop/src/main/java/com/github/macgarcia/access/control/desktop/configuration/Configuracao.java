package com.github.macgarcia.access.control.desktop.configuration;

import com.github.macgarcia.access.control.desktop.component.LocalDateTimeAdapter;
import com.github.macgarcia.access.control.desktop.enuns.AcaoParaArquivo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Dimension;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author macgarcia
 */
public class Configuracao {

    /**
     * *
     * Método que inicializa uma tela interna no centro.
     *
     * @param desktop
     * @param frame
     */
    public static void setPosicaoInternalFrame(JDesktopPane desktop, JInternalFrame frame) {
        Dimension d = desktop.getSize();
        frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
    }

    /**
     * *
     * Verificando se o JInternalFrame em questão, já esta aberto. Estando
     * aberto retorno igual a verdadeiro, caso contrário falso.
     *
     * @param desktop
     * @param classe
     * @return
     */
    public static boolean verificarJanelaAberta(JDesktopPane desktop, Class classe) {
        JInternalFrame[] allFrames = desktop.getAllFrames();
        for (JInternalFrame f : allFrames) {
            Class<? extends JInternalFrame> aClass = f.getClass();
            if (aClass.equals(classe)) {
                return true;
            }
        }
        return false;
    }

    /**
     * *
     * Método que valida dados de uma classe.
     *
     * @param metodos
     * @param obj
     * @param classe
     * @return
     */
    public static boolean validar(String[] metodos, Object obj, Class classe) {
        boolean valido = true;
        int index = 0;
        int tamanho = metodos.length;
        try {
            while (index < tamanho && valido) {
                String dados[] = metodos[index].split("-");
                Method method = classe.getMethod(dados[1]);
                if (method.invoke(obj) == null || method.invoke(obj).toString().isEmpty()) {
                    valido = false;
                    FactoryMensagem.mensagemAlerta("Informe o campo '" + dados[0] + "'");
                }
                index++;
            }
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            e.getMessage();
            valido = false;
        }
        return valido;
    }

    /**
     * *
     * Método que abre um explorer de arquivos.
     *
     * @param abrirArquivo
     * @param campo
     */
    private static String construir(final boolean abrirArquivo, final String acao) {
        JFileChooser lancador = null;
        if (abrirArquivo) {
            lancador = new JFileChooser();
            FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Arquivos de Texto (*.txt)", "txt");
            lancador.addChoosableFileFilter(txtFilter);
            lancador.setFileFilter(txtFilter);
        } else {
            lancador = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            lancador.setDialogTitle("Selecione um diretório");
            lancador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }

        lancador.setDialogTitle(acao);
        int result = lancador.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            final File selectedFile = lancador.getSelectedFile();
            return selectedFile.getPath();
        }
        return null;
    }

    public static void construirLancador(final boolean abrirArquivo, final JTextField campo, final AcaoParaArquivo acao) {
        campo.setText(construir(abrirArquivo, acao.getValor()));
    }

    public static String construirLancador(final boolean abrirArquivo, final AcaoParaArquivo acao) {
        return construir(abrirArquivo, acao.getValor());
    }

    public static String construirLancadorDeSalvamento(final AcaoParaArquivo acao) {
        JFileChooser lancador = new JFileChooser();
        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Arquivos de Texto (*.txt)", "txt");
        lancador.addChoosableFileFilter(txtFilter);
        lancador.setDialogTitle(acao.getValor());
        int userSelection = lancador.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String path = lancador.getSelectedFile().getPath();
            if (!path.toLowerCase().endsWith(".txt")) {
                path += ".txt";
            }
            return path;
        }
        return null;
    }

    /* Devolve uma instancia do gson configurado com o adapter de data */
    public static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
    }
}
