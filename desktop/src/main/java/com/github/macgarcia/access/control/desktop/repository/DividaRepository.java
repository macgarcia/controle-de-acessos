package com.github.macgarcia.access.control.desktop.repository;

import com.github.macgarcia.access.control.desktop.enuns.Mes;
import com.github.macgarcia.access.control.desktop.model.financeiro.Divida;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author macgarcia
 */
public class DividaRepository extends JPARepository<Divida> {
    
    private final String TODAS_AS_DIVIDAS = "select d from Divida d where d.mes = :mes";
    
    public List<Divida> todasAsDividas(final Mes mes) {
        final EntityManager manager = getEntityManager();
        try {
            TypedQuery<Divida> query = manager.createQuery(TODAS_AS_DIVIDAS, Divida.class);
            query.setParameter("mes", mes);
            return query.getResultList();
        } finally {
            manager.clear();
            manager.close();
        }
    }
    
}
