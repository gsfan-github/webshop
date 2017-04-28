package com.focuson.webshop.bean.protocol.request;

import lombok.Data;

/**
 * @author Focuson created on 17-3-14.
 */

@Data
public class CreateProductTypeRequest {
    private String name;
    private String note;
    private Integer parentId;
}
