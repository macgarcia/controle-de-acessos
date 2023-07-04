package com.github.macgarcia.access.control.desktop.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author macgarcia
 */
public class DAOGenerico<T extends EntidadeBase> {
    
    private final String UNIDADE_PERSISTENCIA = "NomeUnidadeDePersistencia";

    public EntityManager getEntityManager() {
        try {
            final EntityManagerFactory emf = Persistence.createEntityManagerFactory(UNIDADE_PERSISTENCIA);
            return emf.createEntityManager();
        } catch (Exception e) {
            throw new InternalError("Banco de dados fora do ar... | " + e.getMessage());
        }
    }

    public boolean salvarEntidade(T t) {
        final EntityManager manager = this.getEntityManager();
        try {
            manager.getTransaction().begin();
            if (t.getId() != null) {
                manager.merge(t);
            } else {
                manager.persist(t);
            }
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            return false;
        } finally {
            manager.clear();
            manager.close();
        }
    }

    public boolean apagarEntidade(final Class<T> classe, final Integer id) {
        final EntityManager manager = this.getEntityManager();
        final T t = manager.find(classe, id);
        try {
            manager.getTransaction().begin();
            manager.remove(t);
            manager.getTransaction().commit();
            return true;
        } finally {
            manager.clear();
            manager.close();
        }
    }

    public T consultarPorId(final Class<T> classe, final Integer id) {
        final EntityManager manager = this.getEntityManager();
        T t = null;
        try {
            t = manager.find(classe, id);
        } finally {
            manager.clear();
            manager.close();
        }
        return t;
    }
}
