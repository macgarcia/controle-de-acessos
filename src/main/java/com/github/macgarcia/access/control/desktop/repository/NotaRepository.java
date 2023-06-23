package com.github.macgarcia.access.control.desktop.repository;

import com.github.macgarcia.access.control.desktop.model.Nota;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author macgarcia
 */
public class NotaRepository extends DAOGenerico<Nota> {

    private final String TODAS_AS_NOTAS = "select n from Nota n";
    private final String NOTA_COM_HISTORICO = "select n from Nota n join fetch n.historico h where n.id = :idNota";
    private final String NOTAS_POR_PESQUISA = "select n from Nota n where lower(n.titulo) like lower(:chave) or lower(n.descricao) like lower(:chave)";

    public Nota getNotaComHistorico(final Integer idNota) {
        final EntityManager manager = getEntityManager();
        try {
            TypedQuery<Nota> query = manager.createQuery(NOTA_COM_HISTORICO, Nota.class);
            query.setParameter("idNota", idNota);
            return query.getSingleResult();
        } finally {
            manager.clear();
            manager.close();
        }
    }

    public List<Nota> getTodasNotas() {
        final EntityManager manager = getEntityManager();
        try {
            TypedQuery<Nota> query = manager.createQuery(TODAS_AS_NOTAS, Nota.class);
            return query.getResultList();
        } finally {
            manager.clear();
            manager.close();
        }
    }

    public List<Nota> getNotasPorPesquisa(final String chave) {
        final EntityManager manager = getEntityManager();
        try {
            TypedQuery<Nota> query = manager.createQuery(NOTAS_POR_PESQUISA, Nota.class);
            query.setParameter("chave", "%" + chave + "%");
            return query.getResultList();            
        } finally {
            manager.clear();
            manager.close();
        }
    }

}
