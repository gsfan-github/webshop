package com.focuson.webshop.bean.protocol.request;

import lombok.Data;

import java.util.List;

/**
 * @author Focuson created on 17-3-15.
 */

@Data
public class DeleteRequest {
    private List<Integer> ids;
}
