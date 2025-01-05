package org.tix.soa2_1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
public class TicketForComplexResponse {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private Double price;
    private String comment;
    private TicketType type;
    private Person person;
    private Long pageNumber;


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
