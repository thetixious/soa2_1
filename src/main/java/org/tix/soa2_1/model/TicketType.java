package org.tix.soa2_1.model;

import lombok.Data;
import lombok.Getter;

@Getter
public enum TicketType {

    VIP("VIP"),

    USUAL("USUAL"),

    BUDGETARY("BUDGETARY"),

    CHEAP("CHEAP");

    private final String value;

    TicketType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static TicketType fromValue(String value) {
        for (TicketType b : TicketType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
