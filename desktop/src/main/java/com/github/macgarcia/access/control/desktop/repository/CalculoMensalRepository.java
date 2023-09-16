package com.github.macgarcia.access.control.desktop.repository;

import com.github.macgarcia.access.control.desktop.enuns.Mes;
import com.github.macgarcia.access.control.desktop.enuns.ProcessosArmazenados;
import com.github.macgarcia.access.control.desktop.enuns.Situacao;
import com.github.macgarcia.access.control.desktop.model.financeiro.CalculoMensal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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

    public CalculoMensal existeCalculoMensal(final Mes mes) {
        final EntityManager manager = getEntityManager();
        try {
            TypedQuery<CalculoMensal> query = manager.createNamedQuery("CalculoMensal.existeCalculoMensal", CalculoMensal.class);
            query.setParameter("mes", mes)
                    .setParameter("ano", LocalDate.now().getYear())
                    .setParameter("situacao", Situacao.FECHADO);
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            manager.clear();
            manager.close();
        }
    }

    public List<CalculoMensal> todosOsCalculos() {
        final EntityManager manager = getEntityManager();
        try {
            TypedQuery<CalculoMensal> query = manager.createNamedQuery("CalculoMensal.todosOsCalculos", CalculoMensal.class);
            return query.getResultList();
        } finally {
            manager.clear();
            manager.close();
        }
    }

    public boolean executeProcedure(ProcessosArmazenados procedure, Map<String, Object> parametros) {
        final EntityManager manager = getEntityManager();
        try {
            StoredProcedureQuery callProcedure = manager.createStoredProcedureQuery(procedure.getNomeProcedure());
            for (Map.Entry<String, Object> entry : parametros.entrySet()) {
                callProcedure.registerStoredProcedureParameter(entry.getKey(), entry.getValue().getClass(), ParameterMode.IN);
                callProcedure.setParameter(entry.getKey(), entry.getValue());
            }
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
