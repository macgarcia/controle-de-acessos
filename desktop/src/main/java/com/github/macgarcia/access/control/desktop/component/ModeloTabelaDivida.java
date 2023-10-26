package com.github.macgarcia.access.control.desktop.component;

import com.github.macgarcia.access.control.desktop.enuns.CategoriaDivida;
import com.github.macgarcia.access.control.desktop.enuns.Mes;
import com.github.macgarcia.access.control.desktop.model.financeiro.Divida;
import com.github.macgarcia.access.control.desktop.repository.DividaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author macgarcia
 */
public final class ModeloTabelaDivida extends AbstractTableModel {

    private final int NUMERO_COLUNAS = 4;

    private DividaRepository dividaRepository;
    private List<Divida> dividas;

    public ModeloTabelaDivida() {
        int digitoMesAtual = LocalDate.now().getMonthValue();
        Mes mesAtual = Mes.getMesComDigito(digitoMesAtual - 1);
        Map<String, Object> params = Map.of("mes", mesAtual, "ano", LocalDate.now().getYear());
        this.dividas = getDividaRepository()
                .selecionarRegistros()
                .where().mesIgualAoMesAtual()
                .and().anoIgualAno()
                .parametros(params)
                .executarConsulta();
    }

    public void reconstruirTabela(Mes mes, CategoriaDivida categoria) {
        if (Objects.isNull(categoria)) {
            Map<String, Object> params = Map.of("mes", mes, "ano", LocalDate.now().getYear());
            this.dividas = getDividaRepository()
                    .selecionarRegistros()
                    .where().mesIgualAoMesAtual()
                    .and().anoIgualAno()
                    .parametros(params)
                    .executarConsulta();
        } else {
            Map<String, Object> params = Map.of("mes", mes, "ano", LocalDate.now().getYear(), "categoria", categoria);
            this.dividas = getDividaRepository()
                    .selecionarRegistros()
                    .where().mesIgualAoMesAtual()
                    .and().anoIgualAno()
                    .and().categoriaIgualACategoria()
                    .parametros(params)
                    .executarConsulta();
        }
    }

    @Override
    public int getRowCount() {
        return this.dividas.size();
    }

    @Override
    public int getColumnCount() {
        return NUMERO_COLUNAS;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final Divida divida = this.dividas.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                divida.getDescricao();
            case 1 ->
                divida.getValor();
            case 2 ->
                divida.getMes();
            case 3 ->
                divida.getCategoria();
            default ->
                throw new IllegalArgumentException("Erro interno na construção da tabela de dividas.");
        };
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 ->
                "Descrição";
            case 1 ->
                "Valor";
            case 2 ->
                "Mes";
            case 3 ->
                "Categoria";
            default ->
                throw new IllegalArgumentException("Erro interno na construção da tabela de dividas.");
        };
    }

    public Double getValorTotalDivida() {
        return dividas.stream()
                .mapToDouble(Divida::getValor)
                .sum();
    }

    public Divida getDivida(final int linha) {
        return dividas.get(linha);
    }

    public DividaRepository getDividaRepository() {
        if (this.dividaRepository == null) {
            this.dividaRepository = new DividaRepository();
        }
        return this.dividaRepository;
    }

    /* Processos de trabalho com o banco de dados */
    public void salvarDivida(final Divida divida) {
        this.dividaRepository.salvarEntidade(divida);
    }

    public void excluirDivida(final Integer idDivida) {
        this.dividaRepository.apagarEntidade(Divida.class, idDivida);
    }

}
