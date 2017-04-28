package com.focuson.webshop.service.product;

import lombok.Data;

import java.util.List;

/**
 * @author Focuson created on 17-3-15.
 */

@Data
public class QueryResult<T> {

    private List<T> resultList;

    private Long totalRecords;

}
