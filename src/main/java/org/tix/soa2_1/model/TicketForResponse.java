package org.tix.soa2_1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@XmlRootElement
public class TicketForResponse implements Serializable {
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private float price;
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