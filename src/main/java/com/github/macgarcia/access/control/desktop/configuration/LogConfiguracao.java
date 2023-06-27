package com.github.macgarcia.access.control.desktop.configuration;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author macgarcia
 */
public class LogConfiguracao {

    private static Logger logger;
    private static final String DIR_NAME = "log";
    private static final String LOG_FILE = DIR_NAME + File.separator + "arq_log.log";

    public static Logger getLogger(Class<?> clazz) {
        montarDiretorio();
        try {
            logger = Logger.getLogger(clazz.getName());
            FileHandler fh = new FileHandler(LOG_FILE, true);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.addHandler(fh);
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(LogConfiguracao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logger;
    }
    
    private static void montarDiretorio() {
        File file = new File(DIR_NAME);
        if (!file.exists()) {
            file.mkdir();
        }
    }

}
