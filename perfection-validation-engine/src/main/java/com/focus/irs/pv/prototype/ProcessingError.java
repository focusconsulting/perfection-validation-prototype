package com.focus.irs.pv.prototype;

import java.util.Optional;

/**
 * Utility class that is created when a processing error occurs. It contains
 * the value that caused the error and the error code
 */
public class ProcessingError<T> {

    private Optional<T> value;
    private ErrorCode errorCode;

    public Optional<T> getValue() {
        return value;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public ProcessingError(Optional<T> value, ErrorCode errorCode) {
        this.value = value;
        this.errorCode = errorCode;
    }

}
