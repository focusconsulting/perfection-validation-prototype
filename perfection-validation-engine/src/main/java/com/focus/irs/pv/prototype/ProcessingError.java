package com.focus.irs.pv.prototype;

import java.util.Optional;

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
