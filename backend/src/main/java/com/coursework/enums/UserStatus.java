package com.coursework.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatus {
    @JsonProperty("name")
    ADMIN("ADMIN"),
    @JsonProperty("name")
    USER("USER");

    private String name;

    UserStatus(String name) {
        this.name = name;
    }
@JsonValue
    public String getName() {
        return name;
    }
}

