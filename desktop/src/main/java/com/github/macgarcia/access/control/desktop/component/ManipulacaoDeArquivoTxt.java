package com.github.macgarcia.access.control.desktop.component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macgarcia
 */
public class ManipulacaoDeArquivoTxt {
    
    private static final int NUMERO_DE_LINHAS_PADRAO = 1000000;
    
    public static List<String> lerAqruivo(final String caminho, final int numeroLinhas) throws FileNotFoundException, IOException {
        
        final int numeroDeLinhas = numeroLinhas <= 0 ? NUMERO_DE_LINHAS_PADRAO : numeroLinhas;
        final List<String> linhasDoArquivo = new ArrayList<>();
        int contagemLinhas = 0;
        String linhaCorrente;
        
        try(BufferedReader reader = new BufferedReader(new FileReader(caminho, StandardCharsets.UTF_8))) {
            while ((linhaCorrente = reader.readLine()) != null && contagemLinhas <= numeroDeLinhas) {
                linhasDoArquivo.add(linhaCorrente);
                contagemLinhas++;
            }
        }
        return linhasDoArquivo;
    }
    
    public static List<String> lerArquivo(final String caminho) throws IOException {
        final Path path = Paths.get(caminho);
        return Files.readAllLines(path);
    }
    
}
