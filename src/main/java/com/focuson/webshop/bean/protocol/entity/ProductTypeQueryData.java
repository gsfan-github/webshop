package com.focuson.webshop.bean.protocol.entity;

import com.focuson.webshop.bean.product.ProductType;
import lombok.Data;

import java.util.List;

/**
 * @author Focuson created on 17-4-12.
 */

@Data
public class ProductTypeQueryData {
    private long totalRecords;
    private List<ProductType> productTypes;
}
