package com.focus.irs.pv.prototype;

public class Correction<T> {

    private T originalValue;
    private T correctedValue;
    private ProcessingError<T> error;

    public Correction(T originalValue, T correctedValue, ProcessingError<T> error) {
        this.originalValue = originalValue;
        this.correctedValue = correctedValue;
        this.error = error;
    }

    public T getOriginalValue() {
        return originalValue;
    }

    public T getCorrectedValue() {
        return correctedValue;
    }

    public ProcessingError<T> getError() {
        return error;
    }
}
