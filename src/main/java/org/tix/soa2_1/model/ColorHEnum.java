package org.tix.soa2_1.model;

import lombok.Data;
import lombok.Getter;


@Getter
public enum ColorHEnum {
    GREEN("GREEN"),

    RED("RED"),

    YELLOW("YELLOW"),

    WHITE("WHITE");

    private  String value;

    ColorHEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ColorHEnum fromValue(String value) {
        for (ColorHEnum b : ColorHEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
