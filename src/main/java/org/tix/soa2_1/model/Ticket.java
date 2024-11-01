package org.tix.soa2_1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Data
public class Ticket {

    private Long id;

    private String name;


    private Coordinates coordinates;

    private ZonedDateTime creationDate;

    private Integer price;

    private String comment;

    private TicketType type;


    private Person person;

}