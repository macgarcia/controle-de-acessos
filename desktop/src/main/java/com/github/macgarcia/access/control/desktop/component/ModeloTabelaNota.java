package com.github.macgarcia.access.control.desktop.component;

import com.github.macgarcia.access.control.desktop.model.anotacoes.Nota;
import com.github.macgarcia.access.control.desktop.repository.NotaRepository;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author macgarcia
 */
public class ModeloTabelaNota extends AbstractTableModel {
    
    private final int PAGINA_INICIAL = 1;
    private final int NUMERO_COLUNAS = 6;
    
    private NotaRepository notaRepository;
    private List<Nota> notas;
        
    public ModeloTabelaNota() {
        this.notas = getNotaRepository().getTodasNotasPaginado(PAGINA_INICIAL);
    }
    
    @Override
    public int getRowCount() {
        return this.notas.size();
    }

    @Override
    public int getColumnCount() {
        return this.NUMERO_COLUNAS;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final Nota nota = notas.get(rowIndex);
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return switch (columnIndex) {
            case 0 -> nota.getDescricao() == null ? "--": nota.getDescricao();
            case 1 -> nota.getTitulo();
            case 2 -> dtf.format(nota.getDataCriacao());
            case 3 -> nota.getUsuario();
            case 4 -> nota.getUrlSite() == null ? "--" : nota.getUrlSite();
            case 5 -> nota.getDataAtualizacao() == null ? "--" : dtf.format(nota.getDataAtualizacao());
            default -> throw new IllegalArgumentException("Erro interno na construção da tabela");
        };
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Descrição";
            case 1 -> "Titulo";
            case 2 -> "Data da cricação";
            case 3 -> "Usuário";
            case 4 -> "Url";
            case 5 -> "Data de atualização";
            default -> throw new IllegalArgumentException("Erro interno na construção da tabela");
        };
    }
    
    private NotaRepository getNotaRepository() {
        if (this.notaRepository == null) {
            this.notaRepository = new NotaRepository();
        }
        return this.notaRepository;
    }
    
    /* Recuperar a nota selecionada na tabela */
    public Nota getNota(final int linha) {
        return notas.get(linha);
    }
    
    public void pesquisar(final String chave, final int pagina) {
        this.notas = notaRepository.getNotasPorPesquisa(chave, pagina);
    }
    
    public void getNotasPaginada(final int pagina) {
        this.notas = notaRepository.getTodasNotasPaginado(pagina);
    }
    
    /* Paginação padão para os dados de tabela de todas as notas */
    public void paginaSolicitada(final int pagina, final boolean estaFiltrada, final String chave) {
        if (estaFiltrada) {
            this.pesquisar(chave, pagina);
        } else {
            this.getNotasPaginada(pagina);
        }
    }
    
}
