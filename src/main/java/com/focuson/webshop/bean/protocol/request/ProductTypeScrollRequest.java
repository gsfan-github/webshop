package com.focuson.webshop.bean.protocol.request;

import lombok.Data;

/**
 * @author Focuson created on 17-3-15.
 */

@Data
public class ProductTypeScrollRequest {

    private Integer firstIndex;

    private Integer maxResult;
}
