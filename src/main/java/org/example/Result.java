package org.example;

public class Result <E extends Enum, T> {
    private E error;
    private T value;

    public Result(E error, T value) {
        this.error = error;
        this.value = value;
    }

    public Result(T value) {
        this.value = value;
    }

    public Result(E error) {
        this.error = error;
    }

    public E getError() {
        return error;
    }

    public T getValue() {
        return value;
    }
}
