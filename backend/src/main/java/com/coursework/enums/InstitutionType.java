package com.coursework.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum InstitutionType {
    RESTAURANT(1),
    CAFE(2);

    private final int value;

    InstitutionType(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    public static InstitutionType fromValue(int value) {
        for (InstitutionType type : InstitutionType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid type value: " + value);
    }
}
