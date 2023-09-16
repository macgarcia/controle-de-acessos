package com.github.macgarcia.access.control.desktop.repository;

import com.github.macgarcia.access.control.desktop.enuns.FlagIntegracao;
import com.github.macgarcia.access.control.desktop.model.anotacoes.Nota;
import com.github.macgarcia.access.control.desktop.pojo.PojoDadosExportacao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author macgarcia
 */
public class NotaRepository extends JPARepository<Nota> {

    private final int RESULTATO_MAXIMO = 10;

    public Nota getNotaComHistorico(final Integer idNota) {
        final EntityManager manager = getEntityManager();
        try {
            TypedQuery<Nota> query = manager.createNamedQuery("Nota.notaComHistorico", Nota.class);
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
            TypedQuery<Nota> query = manager.createNamedQuery("Nota.todasAsNotas", Nota.class);
            return query.getResultList();
        } finally {
            manager.clear();
            manager.close();
        }
    }

    public List<Nota> getTodasNotasPaginado(final int pagina) {
        final EntityManager manager = getEntityManager();
        try {
            TypedQuery<Nota> query = manager.createNamedQuery("Nota.todasAsNotas", Nota.class);
            query.setFirstResult((pagina - 1) * RESULTATO_MAXIMO)
                    .setMaxResults(RESULTATO_MAXIMO);
            return query.getResultList();
        } finally {
            manager.clear();
            manager.close();
        }
    }

    public List<Nota> getNotasPorPesquisa(final String chave, final int pagina) {
        final EntityManager manager = getEntityManager();
        try {
            TypedQuery<Nota> query = manager.createNamedQuery("Nota.notaPorPesquisa", Nota.class);
            query.setParameter("chave", "%" + chave + "%");
            query.setFirstResult((pagina - 1) * RESULTATO_MAXIMO)
                    .setMaxResults(RESULTATO_MAXIMO);
            return query.getResultList();
        } finally {
            manager.clear();
            manager.close();
        }
    }

    public List<Nota> getNotasParaExportacao(final PojoDadosExportacao filtro) {
        final EntityManager manager = getEntityManager();
        try {
            TypedQuery<Nota> query = manager.createNamedQuery("Nota.notasParaExportacao", Nota.class);
            query.setParameter("dataInicial", filtro.getDataInicial())
                    .setParameter("dataFinal", filtro.getDataFinal());
            return query.getResultList();
        } finally {
            manager.clear();
            manager.close();
        }
    }

    public List<Nota> notasParaIntegrar() {
        final EntityManager manager = getEntityManager();
        try {
            final TypedQuery<Nota> query = manager.createNamedQuery("Nota.notasParaExportacao", Nota.class);
            query.setParameter("flagIntegrado", FlagIntegracao.LIGADO);
            query.setMaxResults(10);
            return query.getResultList();
        } finally {
            manager.clear();
            manager.close();
        }
    }
}
