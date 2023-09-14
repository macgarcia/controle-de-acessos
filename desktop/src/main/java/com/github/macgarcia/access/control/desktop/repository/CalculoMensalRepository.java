package com.github.macgarcia.access.control.desktop.repository;

import com.github.macgarcia.access.control.desktop.enuns.Mes;
import com.github.macgarcia.access.control.desktop.enuns.Situacao;
import com.github.macgarcia.access.control.desktop.model.financeiro.CalculoMensal;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author macgarcia
 */
public class CalculoMensalRepository extends JPARepository<CalculoMensal> {

    private final String EXISTE_CALCULO_MENSAL = "select c from CalculoMensal c where mes = :mes and ano = :ano and situacao = :situacao";

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
}
