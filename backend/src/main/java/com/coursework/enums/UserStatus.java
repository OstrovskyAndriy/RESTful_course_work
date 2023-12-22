package com.coursework.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatus {
    ADMIN(1),
    USER(2);

    private final int value;

    UserStatus(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    public static UserStatus fromValue(int value) {
        for (UserStatus status : UserStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }
}
