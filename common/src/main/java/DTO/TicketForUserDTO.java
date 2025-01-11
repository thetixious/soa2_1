package DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import model.Coordinates;
import model.TicketType;


import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Getter
@Setter
public class TicketForUserDTO implements Serializable {

    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private float price;
    private String comment;
    private TicketType type;

    private Long personId;



}
