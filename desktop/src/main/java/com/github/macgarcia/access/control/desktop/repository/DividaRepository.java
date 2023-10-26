package com.github.macgarcia.access.control.desktop.repository;

import com.github.macgarcia.access.control.desktop.model.financeiro.Divida;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author macgarcia
 */
public class DividaRepository extends JPARepository<Divida> implements RegraDeConsulta<DividaRepository> {
    
    private final String SELECT = " select d from Divida d ";
    private final String WHERE = " where ";
    private final String AND = " and ";
    private final String OR = " or ";

    private StringBuilder jpql;
    private Map<String, Object> parametros;

    @Override
    public DividaRepository selecionarRegistros() {
        this.jpql = new StringBuilder();
        jpql.append(SELECT);
        return this;
    }
    
    @Override
    public DividaRepository where() {
        jpql.append(WHERE);
        return this;
    }

    @Override
    public DividaRepository and() {
        jpql.append(AND);
        return this;
    }

    @Override
    public DividaRepository or() {
        jpql.append(OR);
        return this;
    }

    public DividaRepository mesIgualAoMesAtual() {
        jpql.append(" d.mes = :mes ");
        return this;
    }

    public DividaRepository categoriaIgualACategoria() {
        jpql.append(" d.categoria = :categoria ");
        return this;
    }

    public DividaRepository anoIgualAno() {
        jpql.append(" d.ano = :ano ");
        return this;
    }

    @Override
    public DividaRepository parametros(Map<String, Object> parametros) {
        this.parametros = parametros;
        return this;
    }

    @Override
    public List<Divida> executarConsulta() {
        final EntityManager manager = getEntityManager();
        try {
            TypedQuery<Divida> query = manager.createQuery(jpql.toString(), Divida.class);
            for (String p : parametros.keySet()) {
                query.setParameter(p, parametros.get(p));
            }
            return query.getResultList();
        } finally {
            manager.clear();
            manager.close();
        }

    }

}
