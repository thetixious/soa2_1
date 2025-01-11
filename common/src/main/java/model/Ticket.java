package model;

import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
public class Ticket implements Serializable {

    private long id;

    private String name;


    private Coordinates coordinates;

    private ZonedDateTime creationDate;

    private float price;

    private String comment;

    private TicketType type;


    private Person person;

}