package com.focuson.webshop.service.product.impl;

import com.focuson.webshop.service.product.DaoSupport;
import com.focuson.webshop.service.product.ProductTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

/**
 * @author Focuson created on 17-3-13.
 */

@Service
@Transactional
public class ProductTypeServiceImpl extends DaoSupport implements ProductTypeService {

    @Override
    public <T> void delete(Class<T> classType, Object entityId) {
        delete(classType, new Object[]{entityId});
    }

    @Override
    public <T> void delete(Class<T> classType, Object[] entityIds) {
        if (entityIds != null && entityIds.length > 0) {
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < entityIds.length; i++) {
                str.append("?").append(i + 2).append(",");
            }
            str.deleteCharAt(str.length() - 1);
            Query query = em.createQuery("update tbl_product_type o set o.visible = ?1 where typeId in(" + str + ")");

            query.setParameter(1, false);
            for (int i = 0; i < entityIds.length; i++) {
                query.setParameter(i + 2, entityIds[i]);
            }
            query.executeUpdate();
        }
    }
}
