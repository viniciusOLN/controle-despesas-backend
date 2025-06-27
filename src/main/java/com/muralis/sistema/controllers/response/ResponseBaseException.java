package com.muralis.sistema.controllers.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseBaseException<T> {

    private T error;
    private boolean success;

    public ResponseBaseException(T error, boolean success) {
        this.error = error;
        this.success = success;
    }

    public static <T> ResponseBaseException<T> ok(T error) {
        return new ResponseBaseException<>(error, true);
    }

    public static <T> ResponseBaseException<T> fail(T error) {
        return new ResponseBaseException<>(error, false);
    }

}