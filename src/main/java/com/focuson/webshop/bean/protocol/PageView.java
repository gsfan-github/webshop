package com.focuson.webshop.bean.protocol;

import lombok.Data;

import java.util.List;

/**
 * @author Focuson created on 17-4-17.
 */

@Data
public class PageView<T> {

    private long total;

    private List<T> rows;

}
