package com.focuson.webshop.service.product;

import java.util.LinkedHashMap;

/**
 * @author Focuson created on 17-3-13.
 */

public interface DAO {

    void save(Object entity);

    void update(Object entity);

    <T> void delete(Class<T> classType, Object entityId);

    <T> void delete(Class<T> classType, Object[] entityIds);

    <T> T find(Class<T> classType, Object entityId);


    //---------------------------- 分页功能实现 ----------------------------
    <T> QueryResult<T> getScrollData(Class<T> classType);

    <T> QueryResult<T> getScrollData(Class<T> classType, int firstIndex, int maxResult);

    <T> QueryResult<T> getScrollData(Class<T> classType, String whereSql,
                                     Object[] queryParams, int firstIndex, int maxResult);

    /**
     * 获取排序后的分页结果
     *
     * @param classType  实体类
     * @param firstIndex 数据起始位置
     * @param maxResult  最多获取数据个数
     * @param orderBy    排序
     * @param <T>        泛型
     * @param whereSql   where 语句字符串
     * @param queryParams     where语句字符串中的参数
     * @return 排序后的分页结果
     */
    <T> QueryResult<T> getScrollData(Class<T> classType, String whereSql, Object[] queryParams,
                                     int firstIndex, int maxResult, LinkedHashMap<String, String> orderBy);
}
