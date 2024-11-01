package org.tix.soa2_1.model;

public enum ColorEEnum {
    RED("RED"),

    BLUE("BLUE"),

    ORANGE("ORANGE"),

    WHITE("WHITE");

    private String value;

    ColorEEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ColorEEnum fromValue(String value) {
        for (ColorEEnum b : ColorEEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
