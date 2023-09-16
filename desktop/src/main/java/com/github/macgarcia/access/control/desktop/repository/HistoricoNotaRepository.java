package com.github.macgarcia.access.control.desktop.repository;

import com.github.macgarcia.access.control.desktop.model.anotacoes.HistoricoNota;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author macgarcia
 */
public class HistoricoNotaRepository extends JPARepository<HistoricoNota> {

    public List<HistoricoNota> getTodohistoricoDaNota(final Integer idNota) {
        final EntityManager manager = getEntityManager();
        try {
            final TypedQuery<HistoricoNota> query = manager.createNamedQuery("HistoricoNota.todoOHistoricoDeUmaNota", HistoricoNota.class);
            query.setParameter("idNota", idNota);
            return query.getResultList();
        } finally {
            manager.clear();
            manager.close();
        }
    }

}
