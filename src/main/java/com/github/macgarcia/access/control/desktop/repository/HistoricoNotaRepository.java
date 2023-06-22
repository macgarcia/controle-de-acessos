package com.github.macgarcia.access.control.desktop.repository;

import com.github.macgarcia.access.control.desktop.model.HistoricoNota;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author macgarcia
 */
public class HistoricoNotaRepository extends DAOGenerico<HistoricoNota> {

    private final String TODO_O_HISTORICO_DE_UMA_NOTA = "select h from HistoricoNota h where h.nota.id = :idNota order by h.numeroAtualizacao desc";

    public List<HistoricoNota> getTodohistoricoDaNota(final Integer idNota) {
        final EntityManager manager = getEntityManager();
        try {
            final TypedQuery<HistoricoNota> query = manager.createQuery(TODO_O_HISTORICO_DE_UMA_NOTA, HistoricoNota.class);
            query.setParameter("idNota", idNota);
            return query.getResultList();
        } finally {
            manager.clear();
            manager.close();
        }
    }

}
