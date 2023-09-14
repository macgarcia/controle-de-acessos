package com.github.macgarcia.access.control.desktop.relatorio;

import com.github.macgarcia.access.control.desktop.model.anotacoes.Nota;
import com.github.macgarcia.access.control.desktop.repository.NotaRepository;
import java.io.InputStream;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author macgarcia
 */
public class Relatorio {
    
    private final String RELATORIO_NOTAS = "/report/anotacoes.jrxml";
    
    private List<Nota> notas;
    
    private void buscarDadosParaRelatorio() {
        notas = new NotaRepository().getTodasNotas();
    }
    
    public JasperPrint criarRelatorio() throws JRException {
        
        buscarDadosParaRelatorio();
        
        /* Carrega o relatorio */
        InputStream source = Relatorio.class.getResourceAsStream(RELATORIO_NOTAS);

        /* Compila o relatorio */
        JasperReport report = JasperCompileManager.compileReport(source);
        
        /* Gera o relatorio */
        return JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(notas));
        
//        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(notas));
//        
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        JasperExportManager.exportReportToPdfStream(print, outputStream);
        
        //JasperViewer.viewReport(print, false);
    }

}
