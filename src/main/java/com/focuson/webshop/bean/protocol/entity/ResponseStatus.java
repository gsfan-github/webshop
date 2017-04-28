package com.focuson.webshop.bean.protocol.entity;

/**
 * @author Focuson created on 17-4-27.
 */


public enum ResponseStatus {
    SUCCESS("SUCCESS"),
    FAILURE("FAILURE");

    private final String status;

    ResponseStatus(String status) {
        this.status = status;
    }
}
