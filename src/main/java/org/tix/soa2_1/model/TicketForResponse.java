package org.tix.soa2_1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.time.LocalDate;

@Data
public class TicketForResponse {
    private String name;
    private Coordinates coordinates;
    private LocalDate creationDate;
    private Double price;
    private String comment;
    private TicketType type;
    private Person person;
    private Long id;


    @Data
    public static class Person {
        private Long id;
        private LocalDate birthday;

        @JsonProperty("Color_E")
        private Color_E colorE;

        @JsonProperty("Color_H")
        private Color_H colorH;
    }

}