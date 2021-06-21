package com.snailmann.tinyurl.server.controller;

import com.snailmann.tinyurl.common.core.exception.AddressNotFoundException;
import com.snailmann.tinyurl.common.core.exception.TinyKeyConsistencyConflictException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author liwenjie
 */
@Slf4j
@ControllerAdvice
public class ControllerAdvide {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(value = AddressNotFoundException.class)
    public Object addressNotFound(Throwable throwable) {
        return throwable.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(value = TinyKeyConsistencyConflictException.class)
    public Object consistencyConflict(Throwable throwable) {
        return throwable.getMessage();
    }


}
