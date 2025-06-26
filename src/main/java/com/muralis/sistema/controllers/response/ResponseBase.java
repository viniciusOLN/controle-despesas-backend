package com.muralis.sistema.controllers.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseBase<T> {

    private T data;
    private boolean success;

    public ResponseBase(T data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public static <T> ResponseBase<T> ok(T data) {
        return new ResponseBase<>(data, true);
    }

    public static <T> ResponseBase<T> fail(T data) {
        return new ResponseBase<>(data, false);
    }

}