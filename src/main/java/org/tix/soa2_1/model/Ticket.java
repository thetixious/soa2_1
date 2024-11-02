package org.tix.soa2_1.model;

import lombok.Data;


import java.time.ZonedDateTime;

@Data
public class Ticket {

    private Long id;

    private String name;


    private Coordinates coordinates;

    private ZonedDateTime creationDate;

    private Double price;

    private String comment;

    private TicketType type;


    private Person person;

}