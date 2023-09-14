package com.github.macgarcia.access.control.desktop.repository;

import com.github.macgarcia.access.control.desktop.enuns.CategoriaDivida;
import com.github.macgarcia.access.control.desktop.enuns.Mes;
import com.github.macgarcia.access.control.desktop.model.financeiro.Divida;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author macgarcia
 */
public class DividaRepository extends JPARepository<Divida> {

    private final String TODAS_AS_DIVIDAS = "select d from Divida d where d.mes = :mes and d.ano = :ano";
    private final String DIVIDAS_POR_MES_CATEGORIA = "select d from Divida d where d.mes = :mes and d.ano = :ano and d.categoria = :categoria";

    public List<Divida> todasAsDividas(final Mes mes) {
        final EntityManager manager = getEntityManager();
        try {
            TypedQuery<Divida> query = manager.createQuery(TODAS_AS_DIVIDAS, Divida.class);
            query.setParameter("mes", mes)
                    .setParameter("ano", LocalDate.now().getYear());
            return query.getResultList();
        } finally {
            manager.clear();
            manager.close();
        }
    }

    public List<Divida> todasAsDividasPorMesECategoria(Mes mes, CategoriaDivida categoria) {
        final EntityManager manager = getEntityManager();
        try {
            final String sql = categoria == null ? TODAS_AS_DIVIDAS : DIVIDAS_POR_MES_CATEGORIA;
            
            TypedQuery<Divida> query = manager.createQuery(sql, Divida.class);
            query.setParameter("mes", mes)
                    .setParameter("ano", LocalDate.now().getYear());
            
            if (!Objects.isNull(categoria)) {
                query.setParameter("categoria", categoria);
            }
            
            return query.getResultList();
        } finally {
            manager.clear();
            manager.close();
        }
    }

}
