package com.focuson.webshop.service.product.repository;

import com.focuson.webshop.bean.product.ProductType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Focuson created on 17-3-12.
 */

@Repository
@Transactional
public interface ProductPagingDao extends PagingAndSortingRepository<ProductType, String> {
}
