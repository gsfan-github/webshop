package com.focuson.webshop.service.product.repository;

import com.focuson.webshop.bean.product.ProductType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Focuson created on 17-3-6.
 */

@Transactional
public interface ProductDao extends CrudRepository<ProductType, String> {

}
