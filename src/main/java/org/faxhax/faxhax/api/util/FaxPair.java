package org.faxhax.faxhax.api.util;

public class FaxPair<T, S> {
    T key;
    S value;

    public FaxPair(T key, S value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public S getValue() {
        return value;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public void setValue(S value) {
        this.value = value;
    }
}
