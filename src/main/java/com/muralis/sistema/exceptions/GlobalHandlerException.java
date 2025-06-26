package com.muralis.sistema.exceptions;

import com.muralis.sistema.controllers.response.ResponseBase;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseBase<Object>> handleBadRequest(RuntimeException ex) {
        return new ResponseEntity<>(ResponseBase.fail(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ResponseBase<Object>> handleDatabaseError(DataAccessResourceFailureException ex) {
        return new ResponseEntity<>(ResponseBase.fail(ex.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase<Object>> handleGeneric(Exception ex) {
        return new ResponseEntity<>(ResponseBase.fail("Internal server error: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
