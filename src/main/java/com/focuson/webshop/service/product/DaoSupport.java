package com.focuson.webshop.service.product;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Focuson created on 17-3-13.
 */

@Transactional
public abstract class DaoSupport implements DAO {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void save(Object entity) {
        em.persist(entity);
    }

    @Override
    public void update(Object entity) {
        em.merge(entity);
    }

    @Override
    public <T> void delete(Class<T> classType, Object entityId) {
        delete(classType, new Object[]{entityId});
    }

    @Override
    public <T> void delete(Class<T> classType, Object[] entityIds) {
        for (Object entityId : entityIds) {
            em.remove(em.getReference(classType, entityId));
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public <T> T find(Class<T> classType, Object entityId) {
        return em.find(classType, entityId);
    }

    /**
     * 获取实体名称
     *
     * @param entityClass 实体类型
     * @param <T>         泛型
     * @return 实体名称
     */
    private <T> String getEntityName(Class<T> entityClass) {
        Entity entity = entityClass.getAnnotation(Entity.class);
        String table = entityClass.getSimpleName();
        if (entity != null && !StringUtils.isEmpty(entity.name())) {
            table = entity.name();
        }
        return table;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public <T> QueryResult<T> getScrollData(Class<T> classType) {
        return getScrollData(classType, -1, -1);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public <T> QueryResult<T> getScrollData(Class<T> classType, int firstIndex, int maxResult) {
        return getScrollData(classType, null, null, firstIndex, maxResult);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public <T> QueryResult<T> getScrollData(Class<T> classType, String whereSql,
                                            Object[] queryParams, int firstIndex, int maxResult) {
        return getScrollData(classType, whereSql, queryParams, firstIndex, maxResult, null);
    }


    /**
     * 获取order by 字符串
     *
     * @param orderBy orderBy HashMap
     * @return order by string, such as "order by o.key1 desc, o.key2 asc"
     */
    private String buildOrderBy(LinkedHashMap<String, String> orderBy) {
        StringBuffer orderByStr = new StringBuffer("");
        if (orderBy != null && orderBy.size() > 0) {
            orderByStr.append(" order by ");
            for (Map.Entry<String, String> entry : orderBy.entrySet()) {

                String key = entry.getKey();
                String value = entry.getValue();
                orderByStr.append("o.").append(key).append(" ").append(value).append(",");
            }
            orderByStr.deleteCharAt(orderByStr.length() - 1);
        }
        return orderByStr.toString();
    }

    /**
     * 获取排序后的分页结果
     *
     * @param classType  实体类
     * @param firstIndex 数据起始位置
     * @param maxResult  最多获取数据个数
     * @param orderBy    排序
     * @param <T>        泛型
     * @return 排序后的分页结果
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public <T> QueryResult<T> getScrollData(Class<T> classType, String whereSql, Object[] queryParams,
                                            int firstIndex, int maxResult, LinkedHashMap<String, String> orderBy) {
        QueryResult<T> queryResult = new QueryResult<>();

        String table = getEntityName(classType);
        Query query = em.createQuery("select o from " + table + " o "
                + (whereSql == null? "" : "where " + whereSql) + buildOrderBy(orderBy));

        setQueryParams(query, queryParams);

        if (firstIndex != -1 && maxResult != -1) {
            query.setFirstResult(firstIndex).setMaxResults(maxResult);
        }
        queryResult.setResultList(query.getResultList());

        query = em.createQuery("select count(o) from " + table + " o");
        queryResult.setTotalRecords((Long) query.getSingleResult());
        return queryResult;
    }

    private void setQueryParams(Query query, Object[] queryParams) {
        if (queryParams != null && queryParams.length > 0) {
            for (int i = 0; i < queryParams.length; i++) {
                query.setParameter(i+1, queryParams[i]);
            }
        }
    }
}
