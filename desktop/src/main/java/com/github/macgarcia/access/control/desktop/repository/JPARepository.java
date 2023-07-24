package com.github.macgarcia.access.control.desktop.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author macgarcia
 * @param <T>
 */
public class JPARepository<T extends EntidadeBase> {
    
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
    
    public void persistAll(final List<T> list) {
        final EntityManager manager = getEntityManager();
        try {
            manager.getTransaction().begin();
            final int bachSize = 50;
            final int listSize = list.size();
            for (int i = 0; i < listSize; i++) {
                manager.persist(list.get(i));
                if (i % bachSize == 0 || i == listSize -1) {
                    manager.flush();
                    manager.clear();
                }
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction() != null && manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
        } finally {
            manager.clear();
            manager.close();
        }
    }
}
