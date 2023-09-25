package com.github.macgarcia.access.control.desktop.component;

import com.github.macgarcia.access.control.desktop.configuration.FactoryMensagem;
import com.github.macgarcia.access.control.desktop.enuns.Mes;
import com.github.macgarcia.access.control.desktop.enuns.ProcessosArmazenados;
import com.github.macgarcia.access.control.desktop.model.financeiro.CalculoMensal;
import com.github.macgarcia.access.control.desktop.repository.CalculoMensalRepository;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author macgarcia
 */
public class ModeloTabelaCalculoMensal extends AbstractTableModel {

    private final int NUMERO_COLUNAS = 5;
    private CalculoMensalRepository calculoMensalRepository;
    private List<CalculoMensal> calculos;

    public ModeloTabelaCalculoMensal() {
        calculoMensalRepository = new CalculoMensalRepository();
        calculos = calculoMensalRepository.todosOsCalculos();
    }
    
    @Override
    public int getRowCount() {
        return calculos.size();
    }

    @Override
    public int getColumnCount() {
        return NUMERO_COLUNAS;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final CalculoMensal calculoMensal = calculos.get(rowIndex);
        return switch(columnIndex) {
            case 0 -> calculoMensal.getMes();
            case 1 -> calculoMensal.getValorSaldoMensal();
            case 2 -> calculoMensal.getValorResultante();
            case 3 -> calculoMensal.getValorTotalDividas();
            case 4 -> calculoMensal.getSituacao();
            case 5 -> calculoMensal.getAno();
            default -> throw new IllegalArgumentException("Erro interno na construção da tabela de calculos.");
        };
    }
    
    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Mês";
            case 1 -> "Valor mensal recebido";
            case 2 -> "Valor resultante mensal";
            case 3 -> "Valor total de dividas";
            case 4 -> "Situação";
            case 5 -> "Ano";
            default -> throw new IllegalArgumentException("Erro interno na construção da tabela de calculos.");
        };
    }
    
    public CalculoMensal getCalculoMensal(final int index) {
        return calculos.get(index);
    }
    
    public void processarFechamentoMensal(Mes mes, Double valorMensalInformado) {
        Map<String, Object> parameters = Map.of("mes_selecionado_p", mes.name(), "valor_saldo_mensal_p", valorMensalInformado.doubleValue());
        boolean processou = calculoMensalRepository.executeProcedure(ProcessosArmazenados.PROCEDURE_PROCESSAR_FECHAMENTO_MES, parameters);
        if (processou) {
            FactoryMensagem.mensagemOk("Mês fechado com sucesso");
        } else {
            FactoryMensagem.mensagemErro("Mês selecionado já esta fechado, desfaça o processo caso necessite.");
        }
    }
    
    public void desfazerFechamentoMensal(Integer id) {
        Map<String, Object> parameters = Map.of("id_calculo_mensal_p", id.intValue());
        boolean desfez = calculoMensalRepository.executeProcedure(ProcessosArmazenados.PROCEDURE_DESFAZER_FECHAMENTO_MES, parameters);
        if (desfez) {
            FactoryMensagem.mensagemOk("Processo realizado com sucesso, mês aberto para fechamento.");
        } else {
            FactoryMensagem.mensagemErro("Erro ao desfazer o fechamento. Procure o administrador do sistema.");
        }
    }
    
}
