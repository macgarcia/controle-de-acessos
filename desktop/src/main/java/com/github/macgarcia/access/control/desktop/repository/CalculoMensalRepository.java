package com.github.macgarcia.access.control.desktop.repository;

import com.github.macgarcia.access.control.desktop.enuns.Mes;
import com.github.macgarcia.access.control.desktop.enuns.Situacao;
import com.github.macgarcia.access.control.desktop.model.financeiro.CalculoMensal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

/**
 *
 * @author macgarcia
 */
public class CalculoMensalRepository extends JPARepository<CalculoMensal> {

    private final String TODOS_OS_CALCULOS = "select c from CalculoMensal c";
    
    private final String EXISTE_CALCULO_MENSAL = "select c from CalculoMensal c where c.mes = :mes and c.ano = :ano and c.situacao = :situacao";
    
    private final String PROCEDURE_PROCESSAR_FECHAMENTO_MESNAL = "processar_fechamento_mes";
    
    private final String PROCEDURE_DESFAZER_FECHAMENTO_MENSAL = "desfazer_fechamento_mes";

    public CalculoMensal existeCalculoMensal(final Mes mes) {
        final EntityManager manager = getEntityManager();
        try {
            TypedQuery<CalculoMensal> query = manager.createQuery(EXISTE_CALCULO_MENSAL, CalculoMensal.class);
            query.setParameter("mes", mes)
                    .setParameter("ano", LocalDate.now().getYear())
                    .setParameter("situacao", Situacao.FECHADO);
            return query.getSingleResult();
        } catch(NoResultException nre) {
            return null;
        } finally {
            manager.clear();
            manager.close();
        }
    }
    
    public List<CalculoMensal> todosOsCalculos() {
        final EntityManager manager = getEntityManager();
        try {
            TypedQuery<CalculoMensal> query = manager.createQuery(TODOS_OS_CALCULOS, CalculoMensal.class);
            return query.getResultList();
        } finally {
            manager.clear();
            manager.close();
        }
    }
    
    public boolean processar(Mes mes, Double valorMensalInformado) {
        final EntityManager manager = getEntityManager();
        try {
            StoredProcedureQuery callProcedure = manager.createStoredProcedureQuery(PROCEDURE_PROCESSAR_FECHAMENTO_MESNAL);
            callProcedure.registerStoredProcedureParameter("mes_selecionado_p", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("valor_saldo_mensal_p", Double.class, ParameterMode.IN);
            callProcedure.setParameter("mes_selecionado_p", mes.name());
            callProcedure.setParameter("valor_saldo_mensal_p", valorMensalInformado);
            callProcedure.execute();
            return true;
        } catch(PersistenceException e) {
            return false;
        } finally {
            manager.clear();
            manager.close();
        }
    }
    
    public boolean desfazer(Integer id) {
        final EntityManager manager = getEntityManager();
        try {
            StoredProcedureQuery callProcedure = manager.createStoredProcedureQuery(PROCEDURE_DESFAZER_FECHAMENTO_MENSAL);
            callProcedure.registerStoredProcedureParameter("id_calculo_mensal_p", Integer.class, ParameterMode.IN);
            callProcedure.setParameter("id_calculo_mensal_p", id);
            callProcedure.execute();
            return true;
        } catch (PersistenceException e) {
            return false;
        } finally {
            manager.clear();
            manager.close();
        }
    }
}
