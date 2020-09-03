package com.smida.test.task.smida.domain;

public enum Status {

    ACTIVE(1),
    DELETED(0);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
