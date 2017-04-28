package com.focuson.webshop.bean.protocol.response;

import com.focuson.webshop.bean.protocol.PageView;
import com.focuson.webshop.bean.protocol.entity.ResponseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Focuson created on 17-4-12.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductTypeScrollResponse extends BaseResponse {

//    private long total;
//    private List<ProductType> rows;

    private PageView pageView;

    public ProductTypeScrollResponse(ResponseStatus status, String message, PageView pageView) {
        super(status, message);
        this.pageView = pageView;
    }

//    public ProductTypeScrollResponse(String status, String message, List<ProductType> rows, long total) {
//        super(status, message);
//        this.rows = rows;
//        this.total = total;
//    }
}
