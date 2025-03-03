package com.focus.irs.pv.prototype;

public class Correction<T> {

    private T originalValue;
    private T correctedValue;
    private ProcessingError error;

    public Correction(T originalValue, T correctedValue, ProcessingError error) {
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

    public ProcessingError getError() {
        return error;
    }
}
