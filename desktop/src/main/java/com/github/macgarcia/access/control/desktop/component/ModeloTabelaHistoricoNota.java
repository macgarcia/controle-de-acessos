package com.github.macgarcia.access.control.desktop.component;

import com.github.macgarcia.access.control.desktop.model.HistoricoNota;
import com.github.macgarcia.access.control.desktop.repository.HistoricoNotaRepository;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author macgarcia
 */
public class ModeloTabelaHistoricoNota extends AbstractTableModel {
    
    private final int NUMERO_COLUNAS = 9;
    
    private HistoricoNotaRepository repository;
    private List<HistoricoNota> historicos;
    
    public ModeloTabelaHistoricoNota(final Integer id) {
        this.repository = new HistoricoNotaRepository();
        if (id != 0) {
            this.historicos = repository.getTodohistoricoDaNota(id);
        } else {
            this.historicos = new ArrayList<>();
        }
    }

    @Override
    public int getRowCount() {
        return historicos.size();
    }

    @Override
    public int getColumnCount() {
        return this.NUMERO_COLUNAS;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        HistoricoNota historicoNota = this.historicos.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> dtf.format(historicoNota.getDataValidadeInicial());
            case 1 -> dtf.format(historicoNota.getDataValidadeFinal());
            case 2 -> historicoNota.getNumeroAtualizacao();
            case 3 -> historicoNota.getDescricao() == null ? "--" : historicoNota.getDescricao();
            case 4 -> historicoNota.getTitulo();
            case 5 -> dtf.format(historicoNota.getDataCriacao());
            case 6 -> historicoNota.getUsuario();
            case 7 -> historicoNota.getSenha();
            case 8 -> historicoNota.getUrlSite() == null ? "--" : historicoNota.getUrlSite();
            default -> throw new IllegalArgumentException("Erro interno na construção da tabela");
        };
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Validade inicial";
            case 1 -> "Data de vencimento";
            case 2 -> "Ordem da atualização";
            case 3 -> "Descrição";
            case 4 -> "Título";
            case 5 -> "Data da criação";
            case 6 -> "Usuário";
            case 7 -> "Senha";
            case 8 -> "Url do site";
            default -> throw new IllegalArgumentException("Erro interno na construção da tabela");
        };
    }
}
