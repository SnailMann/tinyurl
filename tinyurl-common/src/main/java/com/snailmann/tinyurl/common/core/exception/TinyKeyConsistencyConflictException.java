package com.snailmann.tinyurl.common.core.exception;

/**
 * @author liwenjie
 */
public class TinyKeyConsistencyConflictException extends RuntimeException {

    public TinyKeyConsistencyConflictException() {
    }

    public TinyKeyConsistencyConflictException(String message) {
        super(message);
    }

    public TinyKeyConsistencyConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public TinyKeyConsistencyConflictException(Throwable cause) {
        super(cause);
    }

    public TinyKeyConsistencyConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
