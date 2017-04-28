package com.focuson.webshop.bean.protocol.response;

import com.focuson.webshop.bean.protocol.entity.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Focuson created on 17-4-12.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private ResponseStatus status;
    private String message;

}
