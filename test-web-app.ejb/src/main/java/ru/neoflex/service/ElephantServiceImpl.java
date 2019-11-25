package ru.neoflex.service;

import ru.neoflex.domain.ElephantType;
import ru.neoflex.domain.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Реализация сервиса, обеспечивающего работу магазина
 */

@Stateless
public class ElephantServiceImpl implements ElephantService {

    @PersistenceContext(unitName = "oracleelephant")
    EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ElephantType> searchElephant(String pattern) {
        if (pattern == null) {
            pattern = "";
        }
        /*TypedQuery<ElephantType> query = em.createNamedQuery(
                ElephantType.SEARCH_QUERY, ElephantType.class);*/
        TypedQuery<ElephantType> query = em.createNamedQuery(
                "select * from el_type e", ElephantType.class);
        query.setParameter(ElephantType.PARAMETER_PATTERN,
                "%" + pattern.toUpperCase() + "%");
        List<ElephantType> resultList = query.getResultList();
        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrder(Order order) {
        if (order.getId() == null) {
            em.persist(order);
        } else {
            em.merge(order);
        }
    }
}